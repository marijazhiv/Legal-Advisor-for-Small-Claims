package com.ftn.sbnz.model.models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Apstraktna klasa za polimorfno mapiranje JSON-a
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ZahtevPoslat.class, name = "ZahtevPoslat"),
        @JsonSubTypes.Type(value = OdgovorPrimljen.class, name = "OdgovorPrimljen"),
        @JsonSubTypes.Type(value = KasnjenjeUplate.class, name = "KasnjenjeUplate"),
        @JsonSubTypes.Type(value = RokZastarelosti.class, name = "RokZastarelosti"),
        @JsonSubTypes.Type(value = DokazDodat.class, name = "DokazDodat")
})
public abstract class CepEvent {
}

