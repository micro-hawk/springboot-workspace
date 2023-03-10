package com.poc.pdfgenerator.service.impl;

import com.poc.pdfgenerator.model.dto.CityDto;
import com.poc.pdfgenerator.model.entity.City;
import com.poc.pdfgenerator.repository.CityRepository;
import com.poc.pdfgenerator.service.api.CityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<CityDto> getAllCities() {
        List<City> cities = (List<City>) repository.findAll();
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city : cities) {
            cityDtoList.add(entityToDto(city));
        }
        return cityDtoList;
    }

    private CityDto entityToDto(City city) {
        return CityDto.builder()
            .cityId(city.getId())
            .name(city.getName())
            .population(city.getPopulation())
            .build();
    }
}
