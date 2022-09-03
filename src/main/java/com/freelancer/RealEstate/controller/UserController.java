package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.User;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseDto getUsers() {
        return userService.getUsers();
    }

    /**
     *
     * @param user
     * @param role_id
     * @return
     */
    @PostMapping
    public ResponseDto addUser(@RequestBody User user, @RequestParam(PropertyConstant.ROLE_ID) Integer role_id) {
        return userService.addUser(user,role_id);
    }

    @GetMapping("/roles")
    public ResponseDto getRoles() {
        return userService.getRoles();
    }

    @PostMapping("/login")
    public ResponseDto userLogin(@RequestParam(PropertyConstant.USERNAME) String username,
                                 @RequestParam(PropertyConstant.PASSWORD) String password) {
        return userService.userLogin(username,password);

    }

    @DeleteMapping
    public ResponseDto deleteUser(@RequestHeader(value = "userId") Integer userId) {
        return userService.deleteUser(userId);
    }
}
