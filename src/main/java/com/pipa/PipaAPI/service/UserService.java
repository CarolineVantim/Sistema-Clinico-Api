package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.UserType;
import com.pipa.PipaAPI.domain.repository.FamilyRepository;
import com.pipa.PipaAPI.domain.repository.ProfessionalRepository;
import com.pipa.PipaAPI.domain.repository.UserRepository;
import com.pipa.PipaAPI.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final ProfessionalRepository professionalRepository;

    @Autowired
    public UserService(UserRepository userRepository, FamilyRepository familyRepository, ProfessionalRepository professionalRepository){
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
        this.professionalRepository = professionalRepository;
    }

    public List<UserDTO> findAll(){
        return this.userRepository.findAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<User> findUserByParam(User user){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<User> example = Example.of(user, matcher);
        List<User> usersFind = this.userRepository.findAll(example);

        if (usersFind.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this params has not found");
        }

        return usersFind;
    }

    public UserDTO save(UserDTO dto){
        User user = UserDTO.toOBJ(dto);

        if (dto.getFamily() != null){
            this.familyRepository.save(dto.getFamily());
        }

        if (dto.getProfessional() != null){
            this.professionalRepository.save(dto.getProfessional());
        }

        try {
            return UserDTO.toDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            // Tratamento para tentativa de registrar usuário que já existe no banco
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is invalid", e);
        }
    }

    public UserDTO update(UserDTO dto){
        User user = UserDTO.toOBJ(dto);

        if (dto.getFamily() != null){
            this.familyRepository.save(dto.getFamily());
        }

        if (dto.getProfessional() != null){
            this.professionalRepository.save(dto.getProfessional());
        }

        try {
            return UserDTO.toDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            // Tratamento para tentativa de registrar usuário que já existe no banco
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is invalid", e);
        }
    }

    public void delete(Long id){
        User user = this.findById(id);
        this.userRepository.delete(user);
    }

    public User findById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id '%s' not found".formatted(id)));
    }

    public UserType getUserType(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with username '%s' has not found".formatted(username)));

        return user.getTypeUser();
    }
}
