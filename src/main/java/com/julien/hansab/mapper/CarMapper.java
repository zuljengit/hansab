package com.julien.hansab.mapper;

import com.julien.hansab.domain.Car;
import com.julien.hansab.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carToCarDto(Car Car);
    List<CarDTO> carsToCarsDto(List<Car> Cars);
}