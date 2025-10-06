package com.ftn.sbnz.service;


import java.util.List;

import com.ftn.sbnz.model.models.CepEvent;
import com.ftn.sbnz.model.models.SporDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ftn.sbnz.model.models.Preporuka;
import com.ftn.sbnz.model.models.Spor;
import com.ftn.sbnz.service.DroolsService;

@RestController
@RequestMapping("/api/drools")
public class DroolsController {

    private final DroolsService droolsService;

    public DroolsController(DroolsService droolsService) {
        this.droolsService = droolsService;
    }

    @PostMapping("/process")
    public ResponseEntity<List<Preporuka>> processSpor(@RequestBody SporDTO sporDTO) {
        Spor spor = new Spor(
                sporDTO.getTip(),
                sporDTO.getIznos(),
                sporDTO.isRokIstekao(),
                sporDTO.isDokaziDostupni(),
                sporDTO.isFormalniZahtevPoslat()
        );

        List<Preporuka> preporuke = droolsService.processSpor(spor);
        return ResponseEntity.ok(preporuke);
    }

    // Novi endpoint za CEP
    @PostMapping("/cep")
    public ResponseEntity<List<Preporuka>> processCep(@RequestBody List<CepEvent> events) {
        List<Preporuka> preporuke = droolsService.processCepEvents(events);
        return ResponseEntity.ok(preporuke);
    }
}
