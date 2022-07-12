package com.meteo.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.dto.meteo.RootDto;
import com.meteo.model.MeteoEntity;
import com.meteo.services.meteo.MeteoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MeteocheckApi {
    private static final Logger log = LoggerFactory.getLogger(MeteocheckApi.class);

    @Autowired
    MeteoService meteoService;

    @Autowired
    private Environment env;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("Début traitement le {}", dateFormat.format(new Date()));
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
    private  void sendGET(MeteoEntity meteoEntity) throws IOException {
        String urlApi=env.getProperty("api.url")+env.getProperty("api.token")+"&insee=93031";
        URL obj = new URL(urlApi);
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
            MeteoEntity meteo =new MeteoEntity(root);
            meteoService.saveDepartment(meteo);
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

}



