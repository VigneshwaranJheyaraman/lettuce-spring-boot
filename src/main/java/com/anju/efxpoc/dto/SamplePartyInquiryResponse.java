package com.anju.efxpoc.dto;

public class SamplePartyInquiryResponse {
    // This class is completely for POC purpose
    // This doesn't reciprocate the exact structure for
    // PartyInqResType.
    String javaObjectName;
    PartyInquiryRequest requestSent;
    
    public String getJavaObjectName() {
        return javaObjectName;
    }
    public void setJavaObjectName(String javaObjectName) {
        this.javaObjectName = javaObjectName;
    }
    public PartyInquiryRequest getRequestSent() {
        return requestSent;
    }
    public void setRequestSent(PartyInquiryRequest requestSent) {
        this.requestSent = requestSent;
    }
    @Override
    public String toString() {
        return "InquiryResponse<" + this.requestSent.toString() + ">";
    }
}
