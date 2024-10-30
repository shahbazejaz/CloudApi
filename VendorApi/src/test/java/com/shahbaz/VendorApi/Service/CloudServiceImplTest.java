package com.shahbaz.VendorApi.Service;

import com.shahbaz.VendorApi.Entity.CloudVendor;
import com.shahbaz.VendorApi.Exception.ResourceNotFoundException;
import com.shahbaz.VendorApi.Repository.CloudVendorRepository;
import com.shahbaz.VendorApi.Service.Impl.CloudServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CloudServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    @InjectMocks
    private CloudServiceImpl cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        autoCloseable= MockitoAnnotations.openMocks(this);
        cloudVendorService=new CloudServiceImpl(cloudVendorRepository);
        cloudVendor =new CloudVendor("1","AWS","USA","+123");
    }

    @AfterEach
    void tearDown() throws  Exception{
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor(){
            Mockito.when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        String result = cloudVendorService.createCloudService(cloudVendor);

        assertEquals("success", result);
        Mockito.verify(cloudVendorRepository,Mockito.times(1)).save(cloudVendor);
    }

    @Test
    void testGetAllVendors(){
        Mockito.when(cloudVendorRepository.findAll()).thenReturn((List.of(cloudVendor)));
        List<CloudVendor> result=cloudVendorService.getAllCloudVendor();
        assertEquals(List.of(cloudVendor),result);
    }
    @Test
    void testGetCloudVendorById(){
        Mockito.when(cloudVendorRepository.findById("1")).thenReturn(Optional.of(cloudVendor));
        Optional<CloudVendor> id = Optional.ofNullable(cloudVendorService.getCloudVendorById("1"));
        assertEquals(Optional.of(cloudVendor),id);
        assertNotNull(id);
        Mockito.verify(cloudVendorRepository,Mockito.times(1)).findById("1");
    }

    @Test
    void testGetCloudVendorByIdNotFound(){
        Mockito.when(cloudVendorRepository.findById("2")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> cloudVendorService.getCloudVendorById("2"));
    }

    @Test
    void testUpdateCalendarId(){
        Mockito.when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        String result = cloudVendorService.createCloudService(cloudVendor);
        assertEquals("success", result);
    }
    @Test
    void testDeleteCalenderId(){
       Mockito.doNothing().when(cloudVendorRepository).deleteById("1");
        String result = cloudVendorService.deleteCloudVendor("1");
        assertEquals("success",result);
        Mockito.verify(cloudVendorRepository,Mockito.times(1)).deleteById("1");
    }
}
