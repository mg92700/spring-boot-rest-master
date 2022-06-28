package com.meteo.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.dto.meteo.RootDto;
import com.meteo.model.MeteoEntity;
import net.minidev.json.JSONObject;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
            MeteoEntity val=getValueByApi();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Fin traitement le {}", dateFormat.format(new Date()));

    }
    public MeteoEntity getValueByApi() throws IOException {
        MeteoEntity meteoEntity = new MeteoEntity();
        sendGET(meteoEntity);
        return meteoEntity;
    }
    private static final String USER_AGENT = "Mozilla/5.0";
    private static void sendGET(MeteoEntity meteoEntity) throws IOException {
        URL obj = new URL(api);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            ObjectMapper om = new ObjectMapper();
            RootDto root = om.readValue(response.toString(), RootDto.class);
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

}



