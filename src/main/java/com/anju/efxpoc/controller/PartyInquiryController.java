package com.anju.efxpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.anju.efxpoc.dto.PartyInquiryRequest;
import com.anju.efxpoc.dto.SamplePartyInquiryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/efx")
public class PartyInquiryController {

    private ObjectMapper objMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    static String URL = "http://localhost:8080/api/efx/efx-internal";

    @GetMapping("/sample")
    public ResponseEntity<String> sample(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }


    @PostMapping("/inquiry-party")
    public ResponseEntity<SamplePartyInquiryResponse> getEfxResponseByPartyRequest(@RequestBody PartyInquiryRequest data) {
        //calling efx API for data
        System.out.println("Trying to call EFX api for " + data.toString());
        SamplePartyInquiryResponse responseFromEFX = this.restTemplate.postForObject(URL, data, SamplePartyInquiryResponse.class);
        return (ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseFromEFX));
    }

    @PostMapping("/efx-internal")
    public ResponseEntity<SamplePartyInquiryResponse> fetchAccountDetailsByPartyId(@RequestBody String jsonString) {
        System.out.println("Request for fethcing account details: " + jsonString);
        PartyInquiryRequest javaObject = null;
        try {
            javaObject = this.objMapper.readValue(jsonString, PartyInquiryRequest.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SamplePartyInquiryResponse resp = new SamplePartyInquiryResponse();
        resp.setJavaObjectName(javaObject != null ? javaObject.toString() : "Empty");
        resp.setRequestSent(javaObject);
        return (ResponseEntity
                    .status(HttpStatus.OK)
                    .body(resp));
    }
}
