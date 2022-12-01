package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.entity.User;
import com.visionary.crofting.requests.ClientRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import com.visionary.crofting.service.Impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ClientController {

    @Qualifier("clientService")
    @Autowired
    IService service ;

    @Autowired
    ClientService clientService ;

    @PostMapping("/client")
    public ResponseEntity<ApiResponse<Client>> saveClient( @RequestBody ClientRequest clientRequest ){
        try {
            ApiResponse<Client> response = service.save(clientRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/client/{uuid}")
    public ResponseEntity<ApiResponse<Client>> getClientById(@PathVariable String uuid ){
        try {
            ApiResponse<Client> response = service.find(uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/clients")
    ResponseEntity<ApiResponse<List<Client>>> getListClients(){
        try {
            ApiResponse<List<Client>> response = service.findAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<Client>> clientApiResponse = new ApiResponse<>();
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }


    @DeleteMapping("/client/{uuid}")
    public ResponseEntity<ApiResponse<Client>> deleteClientById(@PathVariable String uuid){
        try {
            ApiResponse<Client> response = service.delete(uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }

    @PutMapping("/client/{uuid}")
    public ResponseEntity<ApiResponse<Client>> updateClient(@PathVariable String uuid,@RequestBody ClientRequest clientRequest){
        try {
            ApiResponse<Client> response = service.update(uuid,clientRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody ClientRequest clientRequest){
        try {
            ApiResponse<User> response = clientService.login(clientRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<User> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }



}
