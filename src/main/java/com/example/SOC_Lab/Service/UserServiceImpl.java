package com.example.SOC_Lab.Service;

import com.example.SOC_Lab.Model.User;
import com.example.SOC_Lab.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Map<String, Object>> searchUsers(String username) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public User getUserProfile(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return "Success! Welcome, " + user.getRoles();
        }
        return "Invalid credentials";
    }

    @Override
    public String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }

    @Override
    public String greetUser(String name) {
        return "<html><body><h1>Hello, " + name + "!</h1></body></html>";
    }

    @Override
    public Map<String, String> getSystemEnv() {
        return System.getenv();
    }
}
