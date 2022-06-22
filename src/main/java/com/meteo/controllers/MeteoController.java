package com.meteo.controllers;

import com.meteo.model.MeteoEntity;
import com.meteo.model.UserEntity;
import com.meteo.services.meteo.MeteoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meteo")
@Api(value = "gestion de la meteo", description = "Operations pour la gestion de la meteo")
public class MeteoController {

    @Autowired
    MeteoService meteoService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable <MeteoEntity> getAllMeteo() {
        return meteoService.fetchMeteoList();
    }
}
