package com.shahbaz.VendorApi.Service;

import com.shahbaz.VendorApi.Entity.CloudVendor;

import java.util.List;

public interface CloudVendorService {

    public String createCloudService(CloudVendor cloudVendor);

    public List<CloudVendor> getAllCloudVendor();

    public CloudVendor getCloudVendorById(String vendorId);

    public String updateCloudVendor(CloudVendor cloudVendor);

    public String deleteCloudVendor(String vendorId);
}
