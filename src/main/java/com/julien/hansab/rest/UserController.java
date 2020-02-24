package com.julien.hansab.rest;

import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.dto.UserDTO;
import com.julien.hansab.service.UserService;
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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "find", required = false) String searchStr,
                                                  Pageable pageable) {
        Page<UserDTO> page = userService.getUsers(searchStr, pageable);
        return new ResponseEntity<>(page.getContent(), createHttpHeaders(Long.toString(page.getTotalElements())),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/users/{id}/cars", method = RequestMethod.GET)
    public List<CarDTO> getUserCars(@PathVariable Long id) {
        return userService.getUserCars(id);
    }
}
