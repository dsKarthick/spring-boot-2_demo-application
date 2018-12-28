package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserDetailController {
    @Autowired
    UserRepository userRepository;

    UserDetailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/register/user", consumes = "application/json", method = RequestMethod.POST)
    public GreetOutput registerUser(@RequestBody UserDetailEntity userDetailEntity) {
        String userId = userDetailEntity.getUserId();
        String userName = userDetailEntity.getUserName();

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserId(userId);
        userDetailDTO.setUserName(userName);
        userRepository.save(userDetailDTO);

        GreetOutput greetOutput = new GreetOutput();
        greetOutput.setMessage("Registered user " + userId);
        return greetOutput;
    }

    @RequestMapping(value = "/greet/user", method = RequestMethod.GET)
    public GreetOutput greetUser(@RequestParam("userid") String userId) {
        GreetOutput greetOutput = new GreetOutput();
        greetOutput.setMessage("User Not Found");

        Optional<UserDetailDTO> userDetailDTO = userRepository.findById(userId);
        if (userDetailDTO.isPresent()) {
            greetOutput.setMessage("Hello " + userDetailDTO.get().getUserName());
        }

        return greetOutput;
    }
}