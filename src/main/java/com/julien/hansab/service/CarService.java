package com.julien.hansab.service;

import com.julien.hansab.domain.Car;
import com.julien.hansab.domain.QCar;
import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.mapper.CarMapper;
import com.julien.hansab.repository.CarRepository;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = true)
    public Page<CarDTO> getCars(String find, Pageable pageable) {
        return carRepository.findAll(getCarSearchCriteria(find), pageable)
                .map(CarMapper.INSTANCE::toCarDto);
    }

    @Transactional(readOnly = true)
    public CarDTO getCar(Long id) {
        Car car = carRepository.getOne(id);
        return CarMapper.INSTANCE.toCarDto(car);
    }

    private BooleanBuilder getCarSearchCriteria(String find) {
        QCar qCar = QCar.car;
        return StringUtils.isBlank(find) ? new BooleanBuilder() : new BooleanBuilder()
                .or(qCar.make.likeIgnoreCase("%" + find + "%"))
                .or(qCar.model.likeIgnoreCase("%" + find + "%"))
                .or(qCar.numberplate.likeIgnoreCase("%" + find + "%"));
    }
}
