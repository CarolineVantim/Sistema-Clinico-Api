package com.pipa.PipaAPI.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pipa.PipaAPI.domain.entity.Family;
import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private Family family;

    private Professional professional;

    private String username;

    private String password;

    private UserType typeUser;

    private LocalDateTime creationDate;

    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFamily(user.getFamily());
        dto.setProfessional(user.getProfessional());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setTypeUser(user.getTypeUser());
        dto.setCreationDate(user.getCreationDate());

        return dto;
    }

    public static User toOBJ(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setFamily(userDTO.getFamily());
        user.setProfessional(userDTO.getProfessional());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setTypeUser(userDTO.getTypeUser());
        user.setCreationDate(userDTO.getCreationDate());

        return user;
    }
}
