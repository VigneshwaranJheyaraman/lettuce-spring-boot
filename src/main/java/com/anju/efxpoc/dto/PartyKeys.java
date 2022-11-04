package com.anju.efxpoc.dto;

public class PartyKeys {
    String partyId;
    String partyIdent;

    @Override
    public String toString() {
        return "PartyKey<" + this.partyId + "," + this.partyIdent + ">";
    }

    public String getPartyId() {
        return partyId;
    }
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    public String getPartyIdent() {
        return partyIdent;
    }
    public void setPartyIdent(String partyIdent) {
        this.partyIdent = partyIdent;
    }
    
}
