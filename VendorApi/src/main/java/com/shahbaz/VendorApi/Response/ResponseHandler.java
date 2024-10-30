package com.shahbaz.VendorApi.Response;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus,@RequestBody CloudVendor data){
        Map<String, Object> response=new HashMap<>();
        response.put("message",message);
        response.put("HttpStatus",httpStatus);
        response.put("data",data);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
