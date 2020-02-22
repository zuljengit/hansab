package com.julien.hansab.service;

import com.julien.hansab.dto.UserDTO;
import com.julien.hansab.mapper.UserMapper;
import com.julien.hansab.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UserDTO> getUsers() {
        return UserMapper.INSTANCE.usersToUsersDto(usersRepository.findAll());
    }
}
