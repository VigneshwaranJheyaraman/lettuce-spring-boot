package com.anju.efxpoc.dto;

public class PartyInquiryRequest {
    PartySelection sel;

    public PartySelection getSel() {
        return sel;
    }

    public void setSel(PartySelection sel) {
        this.sel = sel;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "PartyInquiryRequest<" + this.sel.toString() + ">";
    }
    
}
