package com.meteo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MeteocheckApi {
    private static final Logger log = LoggerFactory.getLogger(MeteocheckApi.class);

    private static final String key="741820df3923c728e121957b268a43b3b270b90785ae28a4725ff671bd65a3c0";
    private static final String api="https://api.meteo-concept.com/api/forecast/nextHours?token=741820df3923c728e121957b268a43b3b270b90785ae28a4725ff671bd65a3c0&insee=93031";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        log.info("Début traitement le {}", dateFormat.format(new Date()));
        System.out.println("salut c'est moi");
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("Fin traitement le {}", dateFormat.format(new Date()));

    }


}
