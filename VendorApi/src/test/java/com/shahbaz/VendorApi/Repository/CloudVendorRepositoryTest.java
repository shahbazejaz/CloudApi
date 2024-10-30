package com.shahbaz.VendorApi.Repository;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import com.shahbaz.VendorApi.VendorApiApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Do not replace with embedded database
@ContextConfiguration(classes = VendorApiApplication.class)
public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor("1", "AWS", "USA", "+123");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendorRepository.deleteAll();
    }

    @Test
    void testFindByVendorName_Found() {
        List<CloudVendor> vendors = cloudVendorRepository.findByVendorName("AWS");
        assertEquals(cloudVendor.getVendorId(), vendors.get(0).getVendorId());
        assertEquals(cloudVendor.getVendorAddress(), vendors.get(0).getVendorAddress());
    }

    @Test
    void testFindByVendorName_NotFound() {
        List<CloudVendor> vendors = cloudVendorRepository.findByVendorName("GCP");
        assertEquals(true, vendors.isEmpty());
    }
}
