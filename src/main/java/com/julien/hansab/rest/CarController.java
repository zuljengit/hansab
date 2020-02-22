package com.julien.hansab.rest;

import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.service.CarService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<CarDTO> getCars() {
        return carService.getCars();
    }
}
