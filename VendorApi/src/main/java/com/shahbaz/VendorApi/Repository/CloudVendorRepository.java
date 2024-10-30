package com.shahbaz.VendorApi.Repository;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor,String> {
    List<CloudVendor> findByVendorName(String vendorName);


}
