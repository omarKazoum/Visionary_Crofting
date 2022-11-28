package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.requests.ClientRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ClientController {

    @Qualifier("clientService")
    @Autowired
    IService service ;

    @PostMapping("/client")
    public ResponseEntity<ApiResponse<Client>> saveClient(@RequestHeader Map<String, String> headers , @RequestBody ClientRequest clientRequest ) throws Exception{
        try {
            ApiResponse<Client> response = service.save(clientRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }

 /*   @GetMapping("/client/{uuid}")
    public ResponseEntity<ApiResponse<Client>> getClientById(@PathVariable String uuid , @RequestHeader Map<String, String> headers )throws Exception{
        try {
            ApiResponse<Client> response = service.find(uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Client> clientApiResponse = new ApiResponse<>() ;
            clientApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(clientApiResponse, HttpStatus.OK);
        }
    }*/
}
