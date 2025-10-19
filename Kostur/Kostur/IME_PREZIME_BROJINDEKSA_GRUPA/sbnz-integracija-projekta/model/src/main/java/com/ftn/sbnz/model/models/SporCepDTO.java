package com.ftn.sbnz.model.models;


import java.util.List;

public class SporCepDTO {
    private SporDTO spor;
    private List<CepEvent> events;

    public SporCepDTO() {}

    public SporDTO getSpor() {
        return spor;
    }

    public void setSpor(SporDTO spor) {
        this.spor = spor;
    }

    public List<CepEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CepEvent> events) {
        this.events = events;
    }
}
