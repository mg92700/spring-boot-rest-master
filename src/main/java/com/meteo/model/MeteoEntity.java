package com.meteo.model;


import com.meteo.dto.meteo.RootDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "meteo")
public class MeteoEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private static final AtomicInteger count = new AtomicInteger(-0);

    @NotEmpty
    @NotNull
    private String probarain;

    @NotEmpty
    @NotNull
    private String probafrost;

    @NotEmpty
    @NotNull
    private String probafog;

    @NotEmpty
    @NotNull
    private String probawind70;


    @NotEmpty
    @NotNull
    private String probawind100;


    @NotEmpty
    @NotNull
    private String tsoil1;


    @NotEmpty
    @NotNull
    private String temp2m;


    private LocalDateTime datesaving;

    public MeteoEntity(RootDto rootDto){
        this.probarain = String.valueOf(rootDto.getForecast().get(0).getProbarain());
        this.probafrost = String.valueOf(rootDto.getForecast().get(0).getProbafrost());
        this.probafog = String.valueOf(rootDto.getForecast().get(0).getProbafog());
        this.probawind70 = String.valueOf(rootDto.getForecast().get(0).getProbawind70());
        this.probawind100 = String.valueOf(rootDto.getForecast().get(0).getProbawind100());
        this.tsoil1 = String.valueOf(rootDto.getForecast().get(0).getTsoil1());
        this.temp2m = String.valueOf(rootDto.getForecast().get(0).getTemp2m());
        this.datesaving = LocalDateTime.now();
    }
    public MeteoEntity(String probarain, String probafrost, String probafog, String probawind70, String probawind100, String tsoil1, String temp2m) {
        this.probarain = probarain;
        this.probafrost = probafrost;
        this.probafog = probafog;
        this.probawind70 = probawind70;
        this.probawind100 = probawind100;
        this.tsoil1 = tsoil1;
        this.temp2m = temp2m;
        this.datesaving = LocalDateTime.now();
        id = count.incrementAndGet();
    }

    public MeteoEntity(long id, String probarain, String probafrost, String probafog, String probawind70, String probawind100, String tsoil1, String temp2m, LocalDateTime datesaving) {
        this.id = id;
        this.probarain = probarain;
        this.probafrost = probafrost;
        this.probafog = probafog;
        this.probawind70 = probawind70;
        this.probawind100 = probawind100;
        this.tsoil1 = tsoil1;
        this.temp2m = temp2m;
        this.datesaving = datesaving;
    }
    public MeteoEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProbarain() {
        return probarain;
    }

    public void setProbarain(String probarain) {
        this.probarain = probarain;
    }

    public String getProbafrost() {
        return probafrost;
    }

    public void setProbafrost(String probafrost) {
        this.probafrost = probafrost;
    }

    public String getProbafog() {
        return probafog;
    }

    public void setProbafog(String probafog) {
        this.probafog = probafog;
    }

    public String getProbawind70() {
        return probawind70;
    }

    public void setProbawind70(String probawind70) {
        this.probawind70 = probawind70;
    }

    public String getProbawind100() {
        return probawind100;
    }

    public void setProbawind100(String probawind100) {
        this.probawind100 = probawind100;
    }

    public String getTsoil1() {
        return tsoil1;
    }

    public void setTsoil1(String tsoil1) {
        this.tsoil1 = tsoil1;
    }

    public String getTemp2m() {
        return temp2m;
    }

    public void setTemp2m(String temp2m) {
        this.temp2m = temp2m;
    }

    public LocalDateTime getDatesaving() {
        return datesaving;
    }

    public void setDatesaving(LocalDateTime datesaving) {
        this.datesaving = datesaving;
    }
}
