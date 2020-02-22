package com.julien.hansab.service;

import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.mapper.CarMapper;
import com.julien.hansab.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getCars() {
        return CarMapper.INSTANCE.carsToCarsDto(carRepository.findAll());
    }
}
