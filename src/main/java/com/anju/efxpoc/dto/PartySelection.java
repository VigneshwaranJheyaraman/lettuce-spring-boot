package com.anju.efxpoc.dto;

public class PartySelection {
    PartyKeys partyKeys;

    public PartyKeys getPartyKeys() {
        return partyKeys;
    }

    public void setPartyKeys(PartyKeys partyKeys) {
        this.partyKeys = partyKeys;
    }

    @Override
    public String toString(){
        return "PartySelection<" + this.partyKeys.toString() + ">";
    }

    
}
