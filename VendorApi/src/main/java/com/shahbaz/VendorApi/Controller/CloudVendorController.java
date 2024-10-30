package com.shahbaz.VendorApi.Controller;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import com.shahbaz.VendorApi.Response.ResponseHandler;
import com.shahbaz.VendorApi.Service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

    public CloudVendorController(CloudVendorService cloudVendorService){
        this.cloudVendorService=cloudVendorService;
    }

    @Autowired
    private CloudVendorService cloudVendorService;

    @GetMapping("/{vendorId}")
    public ResponseEntity<CloudVendor> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        CloudVendor vendor = cloudVendorService.getCloudVendorById(vendorId);
        return new ResponseEntity<>(vendor,HttpStatus.OK);
    }
    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
         cloudVendorService.createCloudService(cloudVendor);
         return "success";
    }
    @PutMapping
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "cloud vendor updated successfully";
    }
    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendor(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "success";
    }
    @GetMapping
    public List<CloudVendor> getAllCloudVendor(){
      return  cloudVendorService.getAllCloudVendor();
    }




}
