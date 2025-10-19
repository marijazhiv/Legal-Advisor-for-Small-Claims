//package com.ftn.sbnz.service;
//
//import java.util.Arrays;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieScanner;
//import org.kie.api.runtime.KieContainer;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@SpringBootApplication
//public class ServiceApplication  {
//
//	private static Logger log = LoggerFactory.getLogger(ServiceApplication.class);
//	public static void main(String[] args) {
//		ApplicationContext ctx = SpringApplication.run(ServiceApplication.class, args);
//
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//
//		StringBuilder sb = new StringBuilder("Application beans:\n");
//		for (String beanName : beanNames) {
//			sb.append(beanName + "\n");
//		}
//		log.info(sb.toString());
//	}
//
//	@Bean
//	public KieContainer kieContainer() {
//		KieServices ks = KieServices.Factory.get();
//		KieContainer kContainer = ks
//				.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
//		KieScanner kScanner = ks.newKieScanner(kContainer);
//		kScanner.start(1000);
//		return kContainer;
//	}
//
//	/*
//	 * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
//	 * ks.newKieContainer(ks.newReleaseId("drools-spring-v2",
//	 * "drools-spring-v2-kjar", "0.0.1-SNAPSHOT")); KieScanner kScanner =
//	 * ks.newKieScanner(kContainer); kScanner.start(10_000); KieSession kSession =
//	 * kContainer.newKieSession();
//	 */
//}
package com.ftn.sbnz.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import com.ftn.sbnz.model.models.*;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.ftn.sbnz.model.models.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class ServiceApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);
        return kContainer;
    }

    @Override
    public void run(String... args) throws Exception {

        KieContainer kieContainer = kieContainer();

        // =====================================
        // 1. BW pravila (klasična poslovna pravila)
        // =====================================
        KieSession bwSession = kieContainer.newKieSession("bwKsession");

        //Spor spor1 = new Spor("faktura", 800, true, true, false);
        //Spor spor2 = new Spor("ugovor", 5000, true, true, false);
        //Spor spor3 = new Spor("neplacena potrazivanja", 2000, false, false, false);

        //bwSession.insert(spor1);
        //bwSession.insert(spor2);
        ///bwSession.insert(spor3);

        int firedBW = bwSession.fireAllRules();
        log.info("👉 Broj aktiviranih BW pravila: {}", firedBW);

        List<Preporuka> preporukeBW = bwSession.getObjects(o -> o instanceof Preporuka)
                .stream().map(o -> (Preporuka) o).collect(Collectors.toList());

        if (preporukeBW.isEmpty()) {
            log.info("📭 Nema aktiviranih BW preporuka.");
        } else {
            log.info("✅ Aktivirane BW preporuke:");
            preporukeBW.forEach(p -> log.info("   - {}", p.getAkcija()));
        }

        bwSession.dispose();


        // =====================================
        // 2. CEP pravila (kompleksni događaji)
        // =====================================
        KieSession cepSession = kieContainer.newKieSession("cepKsession");
        cepSession.getAgenda().getAgendaGroup("cep").setFocus();

        ZahtevPoslat z1 = new ZahtevPoslat(LocalDateTime.now().minusDays(16));
        OdgovorPrimljen o1 = new OdgovorPrimljen(LocalDateTime.now().minusDays(1));
        KasnjenjeUplate k1 = new KasnjenjeUplate(LocalDateTime.now().minusDays(10));
        RokZastarelosti r1 = new RokZastarelosti(LocalDateTime.now().plusDays(5));
        DokazDodat d1 = new DokazDodat(LocalDateTime.now().minusHours(23));
        DokazDodat d2 = new DokazDodat(LocalDateTime.now());

        cepSession.insert(z1);
        cepSession.insert(o1);
        cepSession.insert(k1);
        cepSession.insert(r1);
        cepSession.insert(d1);
        cepSession.insert(d2);

        // 🆕 dodatni test događaji za nova pravila:
        ZahtevPoslat z2 = new ZahtevPoslat(LocalDateTime.now().minusDays(30));
        KasnjenjeUplate k2 = new KasnjenjeUplate(LocalDateTime.now().minusDays(25));
        DokazDodat d3 = new DokazDodat(LocalDateTime.now().minusHours(1));
        cepSession.insert(z2);
        cepSession.insert(k2);
        cepSession.insert(d3);

        int firedCEP = cepSession.fireAllRules();
        log.info("👉 Broj aktiviranih CEP pravila: {}", firedCEP);

        List<Preporuka> preporukeCEP = cepSession.getObjects(o -> o instanceof Preporuka)
                .stream().map(o -> (Preporuka) o).collect(Collectors.toList());

        if (preporukeCEP.isEmpty()) {
            log.info("📭 Nema aktiviranih CEP preporuka.");
        } else {
            log.info("✅ Aktivirane CEP preporuke:");
            preporukeCEP.forEach(p -> log.info("   - {}", p.getAkcija()));
        }

        cepSession.dispose();
    }
}
