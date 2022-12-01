package com.visionary.crofting.service.Impl;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.repository.ClientRepository;
import com.visionary.crofting.repository.SupplierRepository;
import com.visionary.crofting.requests.ClientRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import com.visionary.crofting.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService implements IService<Client,ClientRequest> {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public ApiResponse<Client> save(ClientRequest request) {
        try {
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            boolean dataIsValid =this.validateClient(request);
            if( !dataIsValid){
                clientApiResponse.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
                clientApiResponse.setResponseMessage("data invalid");
                return clientApiResponse;
            }
            Client client = new Client();
            String uuid = UUID.randomUUID().toString();
            client.setUuid(uuid);
            client.setName(request.getName());
            client.setEmail(request.getEmail());
            client.setPassword(request.getPassword());
            client.setPhone(request.getPhone());
            clientRepository.save(client);
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return clientApiResponse;
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return clientApiResponse;
        }

    }

    @Override
    public ApiResponse<Client> find(String uuid)  {
        try {
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            boolean validUUID = validateUUID(uuid);
            if (!validUUID){
                clientApiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
                clientApiResponse.setResponseMessage("invalid identity client");
                return clientApiResponse;
            }
            Client client = clientRepository.findByUuid(uuid);
            if (!Objects.isNull(client)){
                clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                clientApiResponse.setResponseMessage("Client exist");
                clientApiResponse.setData(client);
                return clientApiResponse;
            }
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            clientApiResponse.setResponseMessage("Client not exist");
            return clientApiResponse;
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return clientApiResponse;
        }
    }

    @Override
    public ApiResponse<List<Client>> findAll()  {
        List<Client> clients = clientRepository.findAll();
        ApiResponse<List<Client>> clientApiResponse = new ApiResponse<>();
        clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        clientApiResponse.setResponseMessage("Client exist");
        clientApiResponse.setData(clients);
        return clientApiResponse;
    }

    @Override
    public ApiResponse<Client> delete(String uuid)  {
        try {
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            ApiResponse<Client> clientResponse = this.find(uuid);
            if (!Objects.isNull(clientResponse.getData())){
                clientRepository.delete(clientResponse.getData());
                clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                clientApiResponse.setResponseMessage("Client deleted");
                clientApiResponse.setData(clientApiResponse.getData());
                return clientApiResponse;
            }
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            clientApiResponse.setResponseMessage("Client not deleted");
            return clientApiResponse;
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return clientApiResponse;
        }
    }


    @Override
    public ApiResponse<Client> update(String uuid, ClientRequest Request) {
        try {
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            ApiResponse<Client> clientResponse = this.find(uuid);
            if (!Objects.isNull(clientResponse.getData())){
                if ( Request.getName() != null) {
                    clientResponse.getData().setName(Request.getName());
                }
                if ( Request.getEmail() != null ) {
                    clientResponse.getData().setEmail(Request.getEmail());
                }
                if ( Request.getPassword() != null) {
                    clientResponse.getData().setPassword(Request.getPassword());
                }
                if ( Request.getPhone() != null) {
                    clientResponse.getData().setPhone(Request.getPhone());
                }
                clientRepository.save(clientResponse.getData());
                clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                clientApiResponse.setResponseMessage("Client updated");
                clientApiResponse.setData(clientApiResponse.getData());
                return clientApiResponse;
            }
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            clientApiResponse.setResponseMessage("Client not updated");
            return clientApiResponse;
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return clientApiResponse;
        }
    }


    public ApiResponse<User> login(ClientRequest Request)  {
        try {
            ApiResponse<User> clientApiResponse = new ApiResponse<>();
            Optional<User> client = clientRepository.findByEmail(Request.getEmail());
            if (client.isPresent()) {
                if (client.get().getPassword().equals(Request.getPassword())) {
                    clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                    return clientApiResponse;
                }
            }else {
                Optional<User> supplier = supplierRepository.findByEmail(Request.getEmail());
                if (supplier.isPresent()) {
                    if (supplier.get().getPassword().equals(Request.getPassword())) {
                        clientApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                        return clientApiResponse;
                    }
                }
            }
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            clientApiResponse.setResponseMessage("user not exists");
            return clientApiResponse;

        }catch (Exception e){
            ApiResponse<User> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return clientApiResponse;
        }

    }

    public boolean validateClient(ClientRequest request){
        Pattern emailPattern =Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!Objects.isNull(request)){
            Matcher emailMatcher = emailPattern.matcher(request.getEmail());
            if(!emailMatcher.matches() || request.getEmail().isEmpty() || request.getEmail().isBlank() ){
                return false;
            }
            if( request.getName().isEmpty() || request.getName().isBlank()){
                return false;
            }
            if(request.getPhone().isEmpty() || request.getPhone().isBlank() ){
                return false;
            }
            if(request.getPassword().isEmpty() || request.getPassword().isBlank() ){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean validateUUID(String uuid){
        Pattern emailPattern =Pattern.compile("[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}");
        Matcher emailMatcher = emailPattern.matcher(uuid);
        if(!emailMatcher.matches() || uuid.isEmpty() || uuid.isBlank() ){
            return false;
        }
        return true;
    }

}
