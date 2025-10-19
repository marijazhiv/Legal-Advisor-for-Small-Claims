package com.ftn.sbnz.model.models;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class RokZastarelosti extends CepEvent {
    private LocalDateTime deadline;

    public RokZastarelosti() {
    }

    public RokZastarelosti(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
    public long getDeadlineMillis() {
        return deadline.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return "RokZastarelosti{" +
                "deadline=" + deadline +
                '}';
    }
}

