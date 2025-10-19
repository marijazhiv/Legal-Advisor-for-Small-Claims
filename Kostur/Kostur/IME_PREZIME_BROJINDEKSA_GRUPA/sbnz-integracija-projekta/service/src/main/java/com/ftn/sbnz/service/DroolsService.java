package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.CepEvent;
import com.ftn.sbnz.model.models.Preporuka;
import com.ftn.sbnz.model.models.Spor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroolsService {

    private final KieContainer kieContainer;

    public DroolsService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    /**
     * Procesiranje BW pravila za Spor
     */
    public List<Preporuka> processSpor(Spor spor) {
        KieSession kieSession = kieContainer.newKieSession("bwKsession");
        kieSession.insert(spor);
        kieSession.fireAllRules();

        List<Preporuka> preporuke = new ArrayList<>();
        kieSession.getObjects(o -> o instanceof Preporuka)
                .forEach(o -> preporuke.add((Preporuka) o));

        kieSession.dispose();
        return preporuke;
    }

    /**
     * Procesiranje CEP pravila za događaje
     */
    public List<Preporuka> processCepEvents(List<CepEvent> events) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession"); // odvojen session za CEP
        kieSession.getAgenda().getAgendaGroup("cep").setFocus(); // fokus na CEP pravila

        for (CepEvent event : events) {
            kieSession.insert(event);
        }

        kieSession.fireAllRules();

        List<Preporuka> preporuke = new ArrayList<>();
        kieSession.getObjects(o -> o instanceof Preporuka)
                .forEach(o -> preporuke.add((Preporuka) o));

        kieSession.dispose();
        return preporuke;
    }
}

