package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Property;
import com.freelancer.RealEstate.entity.User;
import com.freelancer.RealEstate.entity.UserRoles;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.RoleRepository;
import com.freelancer.RealEstate.repository.UserRepository;
import com.freelancer.RealEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @return
     */
    @Override
    public ResponseDto getUsers() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        try {
            List<User> userList = userRepository.findAll();
            responseDto.setData(userList);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param user
     * @param role_id
     * @return
     */
    @Override
    public ResponseDto addUser(User user, Integer role_id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            User user1 = new User();
            Optional<UserRoles> userRoles = Optional.ofNullable(roleRepository.findById(role_id).orElseThrow(() -> new Exception("Role Not found")));
            if (user.getUser_id() != null) {
                if (userRepository.findById(user.getUser_id()).isPresent()) {
                    user1 = userRepository.findById(user.getUser_id()).get();
                }
            }
            user1.setUser_address(user.getUser_address());
            user1.setUser_email(user.getUser_email());
            user1.setUser_type(user.getUser_type());
            user1.setUserRoles(userRoles.get());
            user1.setFirst_name(user.getFirst_name());
            user1.setLast_name(user.getLast_name());
            user1 = userRepository.save(user1);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setData(user1);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto getRoles() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        try {
            List<UserRoles> rolesList = roleRepository.findAll();
            responseDto.setData(rolesList);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResponseDto userLogin(String username, String password) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<User> user = userRepository.findUserByUsernameAndPassword(username, password);
            if (user.isPresent()) {
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setData(user.get());
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.FAILED);
                responseDto.setData(user.get());
                responseDto.setStatusCode(PropertyConstant.NOT_FOUND);
            }
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteUser(Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userRepository.deleteById(userId);
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.NO_DATA);
                responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            }
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }
}
