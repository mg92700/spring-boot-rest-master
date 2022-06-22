package com.meteo.services.meteo;

import com.meteo.model.MeteoEntity;

import java.util.List;

public interface MeteoService {
    MeteoEntity saveDepartment(MeteoEntity meteoEntity);

    // Read operation
    List<MeteoEntity> fetchMeteoList();

    // Update operation
    MeteoEntity updateMeteo(MeteoEntity meteoEntity,
                                Long id);

    // Delete operation
    void deleteMeteoById(Long id);
}
