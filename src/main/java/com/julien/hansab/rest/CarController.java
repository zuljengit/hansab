package com.julien.hansab.rest;

import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.julien.hansab.rest.util.RestUtils.createHttpHeaders;

@Validated
@RestController
@RequestMapping("/")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getCars(@RequestParam(value = "find", required = false) String searchStr,
                                                Pageable pageable) {
        Page<CarDTO> page = carService.getCars(searchStr, pageable);
        return new ResponseEntity<>(page.getContent(), createHttpHeaders(Long.toString(page.getTotalElements())),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }
}
