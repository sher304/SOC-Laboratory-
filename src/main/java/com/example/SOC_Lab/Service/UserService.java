package com.example.SOC_Lab.Service;


import com.example.SOC_Lab.Model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> searchUsers(String username);
    User getUserProfile(String id);
    String login(String username, String password);
    String readFile(String filename);
    String greetUser(String name);
    Map<String, String> getSystemEnv();
}