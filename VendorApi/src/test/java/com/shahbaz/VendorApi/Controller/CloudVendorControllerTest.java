package com.shahbaz.VendorApi.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahbaz.VendorApi.Entity.CloudVendor;
import com.shahbaz.VendorApi.Repository.CloudVendorRepository;
import com.shahbaz.VendorApi.Service.CloudVendorService;
import com.shahbaz.VendorApi.Service.Impl.CloudServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(CloudVendorController.class)
    public class CloudVendorControllerTest {


        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CloudVendorService cloudService;

        private CloudVendor cloudVendor;

        @BeforeEach
        void setUp(){
            MockitoAnnotations.openMocks(this);
            cloudVendor =new CloudVendor("1","AWS","USA","+123");

        }

        @Test
        void getCloudVendorDetails() throws Exception {
            when(cloudService.getCloudVendorById("1")).thenReturn(cloudVendor);
           mockMvc.perform(get("/cloudvendor/1")
                           .accept(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                   .andExpect(jsonPath("$.vendorId").value("1")) // Adjust as per your field names
                  .andExpect(jsonPath("$.vendorName").value("AWS"))
                  .andExpect(jsonPath("$.vendorAddress").value("USA")) // Check vendorAddress
                    .andExpect(jsonPath("$.vendorPhoneNumber").value("+123")); // Check vendorPhoneNumber
             verify(cloudService, times(1)).getCloudVendorById("1");
        }

        @Test
        void createCloudServiceTest() throws Exception{
            when(cloudService.createCloudService(any(CloudVendor.class))).thenReturn("success");

            String cloudVendorJson = """
                {
                    "vendorId": "1",
                    "vendorName": "AWS",
                    "vendorAddress": "USA",
                    "vendorPhoneNumber": "+123"
                }
                """;

            mockMvc.perform(post("/cloudvendor")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(cloudVendorJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string("success"));
            verify(cloudService, times(1)).createCloudService(any(CloudVendor.class));
        }
        @Test
        void updateCloudVendorTest() throws Exception{
            when(cloudService.updateCloudVendor(any(CloudVendor.class))).thenReturn("cloud vendor updated successfully");
            String cloudVendorJson = """
                {
                    "vendorId": "1",
                    "vendorName": "AWS",
                    "vendorAddress": "USA",
                    "vendorPhoneNumber": "+123"
                }
                """;
            mockMvc.perform(put("/cloudvendor")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(cloudVendorJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string("cloud vendor updated successfully"));
            verify(cloudService, times(1)).updateCloudVendor(any(CloudVendor.class));
        }
        @Test
        void deleteCloudVendorDetails() throws Exception{
            when(cloudService.deleteCloudVendor("1")).thenReturn("success");
            mockMvc.perform(delete("/cloudvendor/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("success"));

            verify(cloudService, times(1)).deleteCloudVendor("1");
        }

        @Test
        void getAllCloudVendor() throws Exception{
            when(cloudService.getAllCloudVendor()).thenReturn(List.of(cloudVendor));
            mockMvc.perform(get("/cloudvendor")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].vendorId").value("1"))
                    .andExpect(jsonPath("$[0].vendorName").value("AWS"))
                    .andExpect(jsonPath("$[0].vendorAddress").value("USA"))
                    .andExpect(jsonPath("$[0].vendorPhoneNumber").value("+123"));

            verify(cloudService, times(1)).getAllCloudVendor();
        }
    }
