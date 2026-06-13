package com.example.SOC_Lab.Controller;

import com.example.SOC_Lab.Model.User;
import com.example.SOC_Lab.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class VulnerableAPIController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public List<Map<String, Object>> searchUser(@RequestParam String username) {
        return userService.searchUsers(username);
    }

    @GetMapping("/profile/{id}")
    public User getUserProfile(@PathVariable String id) {
        return userService.getUserProfile(id);
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, @RequestParam String username) {
        return userService.login(username, password);
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam String file) {
        return userService.readFile(file);
    }

    @GetMapping(value = "/greet", produces = "text/html")
    public String greet(@RequestParam String name) {
        return userService.greetUser(name);
    }

    @GetMapping("/debug/env")
    public Map<String, String> getEnv() {
        return userService.getSystemEnv();
    }
}
