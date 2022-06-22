package com.meteo.services.meteo;

import com.meteo.model.MeteoEntity;
import com.meteo.repository.MeteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class MeteoServiceImpl implements MeteoService{
    @Autowired
    MeteoRepository meteoRepository;

    @Override
    @Transactional
    public MeteoEntity saveDepartment(MeteoEntity meteoEntity) {
        return meteoRepository.save(meteoEntity);
    }

    @Override
    @Transactional
    public List<MeteoEntity> fetchMeteoList() {
        return meteoRepository.findAll();
    }

    @Override
    @Transactional
    public MeteoEntity updateMeteo(MeteoEntity meteoEntity, Long id) {
        Optional<MeteoEntity> meteo =meteoRepository.findById(id);
        if(meteo!=null){

        }
        return meteoEntity;
    }

    @Override
    @Transactional
    public void deleteMeteoById(Long id) {
        meteoRepository.deleteById(id);
    }
}
