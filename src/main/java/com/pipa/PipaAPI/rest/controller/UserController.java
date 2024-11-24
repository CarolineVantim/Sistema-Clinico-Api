package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.rest.dto.UserDTO;
import com.pipa.PipaAPI.service.UserService;
import com.pipa.PipaAPI.utils.swagger.UserSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController implements UserSwaggerOperations {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/view_users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll(){
        return this.userService.findAll();
    }

    @GetMapping("/find_by")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByParam(User user){
        return this.userService.findUserByParam(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody UserDTO dto){
        return this.userService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        this.userService.delete(id);
    }
}
