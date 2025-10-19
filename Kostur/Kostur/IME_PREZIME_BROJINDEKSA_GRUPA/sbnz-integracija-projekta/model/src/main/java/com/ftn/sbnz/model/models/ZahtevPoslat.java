package com.ftn.sbnz.model.models;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ZahtevPoslat extends CepEvent {
    private LocalDateTime timestamp;

    public ZahtevPoslat() {
    }

    public ZahtevPoslat(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestampMillis() {
        return timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public String toString() {
        return "ZahtevPoslat{" +
                "timestamp=" + timestamp +
                '}';
    }
}
