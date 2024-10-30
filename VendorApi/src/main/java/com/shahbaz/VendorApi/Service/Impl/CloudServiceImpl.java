package com.shahbaz.VendorApi.Service.Impl;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import com.shahbaz.VendorApi.Exception.ResourceNotFoundException;
import com.shahbaz.VendorApi.Repository.CloudVendorRepository;
import com.shahbaz.VendorApi.Service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CloudServiceImpl implements CloudVendorService {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    public CloudServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository=cloudVendorRepository;
    }

    @Override
    public String createCloudService(CloudVendor cloudVendor) {
         cloudVendorRepository.save(cloudVendor);
         return "success";
    }

    @Override
    public List<CloudVendor> getAllCloudVendor() {

        return cloudVendorRepository.findAll();
    }

    @Override
    public CloudVendor getCloudVendorById(String vendorId) {
        return cloudVendorRepository.findById(vendorId).orElseThrow(()-> new ResourceNotFoundException("CloudvendorId not found"));
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "success";
    }

    @Override
    public String deleteCloudVendor(String vendorId) {
      cloudVendorRepository.deleteById(vendorId);
      return "success";
    }
}
