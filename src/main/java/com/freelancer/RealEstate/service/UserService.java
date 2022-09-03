package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.User;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     *
     * @return
     */
    ResponseDto getUsers();

    ResponseDto addUser(User user, Integer role_id);

    ResponseDto getRoles();

    ResponseDto userLogin(String username, String password);

    ResponseDto deleteUser(Integer userId);
}
