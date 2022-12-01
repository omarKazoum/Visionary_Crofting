package com.visionary.crofting.service.Impl;

import com.visionary.crofting.entity.Supplier;
import com.visionary.crofting.repository.SupplierRepository;
import com.visionary.crofting.requests.SupplierRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SupplierService implements IService<Supplier,SupplierRequest> {
    @Autowired
    SupplierRepository supplierRepository;


    @Override
    public ApiResponse<Supplier> save(SupplierRequest request) throws Exception {
        try {
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            boolean dataIsValid =this.validateSupplier(request);
            if( !dataIsValid){
                supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
                supplierApiResponse.setResponseMessage("data invalid");
                return supplierApiResponse;
            }
            Supplier supplier = new Supplier();
            String uuid = UUID.randomUUID().toString();
            supplier.setUuid(uuid);
            supplier.setName(request.getName());
            supplier.setEmail(request.getEmail());
            supplier.setPassword(request.getPassword());
            supplier.setPhone(request.getPhone());
            supplierRepository.save(supplier);
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return supplierApiResponse;
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return supplierApiResponse;
        }

    }

    @Override
    public ApiResponse<Supplier> find(String uuid) throws Exception {
        try {
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            boolean validUUID = validateUUID(uuid);
            if (!validUUID){
                supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
                supplierApiResponse.setResponseMessage("invalid identity supplier");
                return supplierApiResponse;
            }
            Supplier supplier = supplierRepository.findByUuid(uuid);
            if (!Objects.isNull(supplier)){
                supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                supplierApiResponse.setResponseMessage("Supplier exist");
                supplierApiResponse.setData(supplier);
                return supplierApiResponse;
            }
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            supplierApiResponse.setResponseMessage("Supplier not exist");
            return supplierApiResponse;
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return supplierApiResponse;
        }
    }

    @Override
    public ApiResponse<List<Supplier>> findAll() throws Exception {
        List<Supplier> suppliers = supplierRepository.findAll();
        ApiResponse<List<Supplier>> supplierApiResponse = new ApiResponse<>();
        supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        supplierApiResponse.setResponseMessage("Supplier exist");
        supplierApiResponse.setData(suppliers);
        return supplierApiResponse;
    }

    @Override
    public ApiResponse<Supplier> delete(String uuid) throws Exception {
        try {
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            ApiResponse<Supplier> supplierResponse = this.find(uuid);
            if (!Objects.isNull(supplierResponse.getData())){
                supplierRepository.delete(supplierResponse.getData());
                supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                supplierApiResponse.setResponseMessage("Supplier deleted");
                supplierApiResponse.setData(supplierApiResponse.getData());
                return supplierApiResponse;
            }
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            supplierApiResponse.setResponseMessage("Supplier not deleted");
            return supplierApiResponse;
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return supplierApiResponse;
        }
    }


    @Override
    public ApiResponse<Supplier> update(String uuid, SupplierRequest Request) throws Exception {
        try {
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            ApiResponse<Supplier> supplierResponse = this.find(uuid);
            if (!Objects.isNull(supplierResponse.getData())){
                if ( Request.getName() != null) {
                    supplierResponse.getData().setName(Request.getName());
                }
                if ( Request.getEmail() != null ) {
                    supplierResponse.getData().setEmail(Request.getEmail());
                }
                if ( Request.getPassword() != null) {
                    supplierResponse.getData().setPassword(Request.getPassword());
                }
                if ( Request.getPhone() != null) {
                    supplierResponse.getData().setPhone(Request.getPhone());
                }
                supplierRepository.save(supplierResponse.getData());
                supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                supplierApiResponse.setResponseMessage("Supplier updated");
                supplierApiResponse.setData(supplierApiResponse.getData());
                return supplierApiResponse;
            }
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            supplierApiResponse.setResponseMessage("Supplier not updated");
            return supplierApiResponse;
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>();
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return supplierApiResponse;
        }
    }

    public boolean validateSupplier(SupplierRequest request){
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
