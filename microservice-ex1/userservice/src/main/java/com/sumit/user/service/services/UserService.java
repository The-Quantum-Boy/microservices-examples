package com.sumit.user.service.services;

import com.sumit.user.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User saveuser(User user);

    List<User> getAllUser();

    User getUser(String userId);
}
