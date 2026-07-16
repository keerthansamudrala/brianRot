package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.user.UserRequestDTO;
import com.keerthan.brainRot.dto.user.UserResponseDTO;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.PostRepository;
import com.keerthan.brainRot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    public void addUser(UserRequestDTO dto){
        User user = new User();
        user.setUserName((dto.getUserName()));
        user.setUserEmail((dto.getUserEmail()));
        user.setUserPassword((dto.getUserPassword()));
        userRepository.save(user);
    }
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> users_list = new ArrayList<>();
        for (User user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUserName((user.getUserName()));
            userResponseDTO.setUserEmail((user.getUserEmail()));
            userResponseDTO.setTotalCockroachesSpent((user.getTotalCockroachesSpent()));
            userResponseDTO.setCockroachesLeft((user.getCockroachesLeft()));

            users_list.add(userResponseDTO);
        }
        return users_list;
    }
    @Transactional
    public String deleteUser(int id) {
        postRepository.deleteByUserId(id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        userRepository.delete(user);
        return "User has been deleted";
    }
}
