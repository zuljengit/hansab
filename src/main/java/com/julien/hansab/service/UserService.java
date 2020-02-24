package com.julien.hansab.service;

import com.julien.hansab.domain.QUser;
import com.julien.hansab.domain.User;
import com.julien.hansab.dto.CarDTO;
import com.julien.hansab.dto.UserDTO;
import com.julien.hansab.mapper.CarMapper;
import com.julien.hansab.mapper.UserMapper;
import com.julien.hansab.repository.UsersRepository;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getUsers(String searchStr, Pageable pageable) {
        return usersRepository.findAll(getUserSearchCriteria(searchStr), pageable)
                        .map(UserMapper.INSTANCE::toUserDto);
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Long id) {
        User user = usersRepository.getOne(id);
        return UserMapper.INSTANCE.toUserDto(user);
    }

    @Transactional(readOnly = true)
    public List<CarDTO> getUserCars(Long id) {
        User user = usersRepository.getOne(id);
        return CarMapper.INSTANCE.toCarsDto(user.getCars());
    }

    private BooleanBuilder getUserSearchCriteria(String find) {
        QUser qUser = QUser.user;
        return StringUtils.isBlank(find) ? new BooleanBuilder() :
                new BooleanBuilder().and(qUser.name.likeIgnoreCase("%" + find + "%"));
    }
}
