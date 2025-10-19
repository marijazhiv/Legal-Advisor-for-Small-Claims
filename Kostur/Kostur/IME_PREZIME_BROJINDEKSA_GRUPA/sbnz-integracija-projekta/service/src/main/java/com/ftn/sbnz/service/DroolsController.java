package com.ftn.sbnz.service;


import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.DroolsService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/drools")
public class DroolsController {

    private final DroolsService droolsService;
    private final KieContainer kieContainer;


//    public DroolsController(DroolsService droolsService) {
//        this.droolsService = droolsService;
//    }
    public DroolsController(DroolsService droolsService, KieContainer kieContainer) {
        this.droolsService = droolsService;
        this.kieContainer = kieContainer;
    }

    @PostMapping("/process")
    public ResponseEntity<List<Preporuka>> processSpor(@RequestBody SporDTO sporDTO) {
        Spor spor = new Spor(
                sporDTO.getTip(),
                sporDTO.getIznos(),
                sporDTO.isRokIstekao(),
                sporDTO.isDokaziDostupni(),
                sporDTO.isFormalniZahtevPoslat(),
                sporDTO.isSvedokPrisutan(),
                sporDTO.isPozitivanPresedan()
        );

        List<Preporuka> preporuke = droolsService.processSpor(spor);
        return ResponseEntity.ok(preporuke);
    }

    @PostMapping("/process1")
    public ResponseEntity<Preporuka> processSpor1(@RequestBody Spor spor) {
        List<Preporuka> preporuke = droolsService.processSpor(spor);

        if (preporuke == null || preporuke.isEmpty()) {
            return ResponseEntity.noContent().build(); // nema preporuka
        }

        Preporuka poslednja = preporuke.get(preporuke.size() - 1);
        return ResponseEntity.ok(poslednja);
    }

    // Novi endpoint za CEP
    @PostMapping("/cep")
    public ResponseEntity<List<Preporuka>> processCep(@RequestBody List<CepEvent> events) {
        List<Preporuka> preporuke = droolsService.processCepEvents(events);
        return ResponseEntity.ok(preporuke);
    }

    @PostMapping("/cep/single")
    public ResponseEntity<List<Preporuka>> processSingleCep(@RequestBody CepEvent event) {
        List<Preporuka> preporuke = droolsService.processCepEvents(List.of(event));
        return ResponseEntity.ok(preporuke);
    }

    @PostMapping("/spor-cep")
    public ResponseEntity<List<Preporuka>> processSporWithCep(@RequestBody SporCepDTO dto) {
        // 1. Kreiramo Spor objekat iz DTO
        Spor spor = new Spor(
                dto.getSpor().getTip(),
                dto.getSpor().getIznos(),
                dto.getSpor().isRokIstekao(),
                dto.getSpor().isDokaziDostupni(),
                dto.getSpor().isFormalniZahtevPoslat(),
                dto.getSpor().isSvedokPrisutan(),
                dto.getSpor().isPozitivanPresedan()
        );

        // 2. Kreiramo KieSession za forward pravila
        KieSession kieSessionForward = kieContainer.newKieSession("forwardKsession");
        kieSessionForward.insert(spor);
        kieSessionForward.fireAllRules();

        List<Preporuka> forwardResult = new ArrayList<>();
        kieSessionForward.getObjects(o -> o instanceof Preporuka)
                .forEach(o -> forwardResult.add((Preporuka) o));
        kieSessionForward.dispose();

        // 3. Kreiramo KieSession za CEP pravila
        KieSession kieSessionCep = kieContainer.newKieSession("cepKsession");
        kieSessionCep.getAgenda().getAgendaGroup("cep").setFocus();

        for (CepEvent event : dto.getEvents()) {
            kieSessionCep.insert(event);
        }
        kieSessionCep.fireAllRules();

        List<Preporuka> cepResult = new ArrayList<>();
        kieSessionCep.getObjects(o -> o instanceof Preporuka)
                .forEach(o -> cepResult.add((Preporuka) o));
        kieSessionCep.dispose();

        // 4. Kombinovanje verovatnoća: forward + CEP
        for (Preporuka p : forwardResult) {
            double dodatak = cepResult.stream().mapToDouble(Preporuka::getProcenatUspeha).sum();
            p.setProcenatUspeha((int) Math.min(100, p.getProcenatUspeha() + dodatak));
        }

        return ResponseEntity.ok(forwardResult);
    }

}
