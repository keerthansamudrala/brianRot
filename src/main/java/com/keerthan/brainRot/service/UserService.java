package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.user.UserRequestDTO;
import com.keerthan.brainRot.dto.user.UserResponseDTO;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserRequestDTO dto){
        User user = new User();
        user.setUser_name(dto.getUser_name());
        user.setUser_email(dto.getUser_email());
        user.setUser_password(dto.getUser_password());
        userRepository.save(user);
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> users_list = new ArrayList<>();
        for (User user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUser_name(user.getUser_name());
            userResponseDTO.setUser_email(user.getUser_email());
            userResponseDTO.setTotal_cockroaches_spent(user.getTotal_cockroaches_spent());
            userResponseDTO.setCockroaches_left(user.getCockroaches_left());

            users_list.add(userResponseDTO);
        }
        return users_list;
    }
}
