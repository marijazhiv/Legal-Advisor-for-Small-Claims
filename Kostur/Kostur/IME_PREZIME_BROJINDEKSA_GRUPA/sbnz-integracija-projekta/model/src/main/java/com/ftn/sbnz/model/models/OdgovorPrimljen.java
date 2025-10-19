package com.ftn.sbnz.model.models;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class OdgovorPrimljen extends CepEvent  {
    private LocalDateTime timestamp;

    public OdgovorPrimljen() {
    }

    public OdgovorPrimljen(LocalDateTime timestamp) {
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
        return "OdgovorPrimljen{" +
                "timestamp=" + timestamp +
                '}';
    }
}
