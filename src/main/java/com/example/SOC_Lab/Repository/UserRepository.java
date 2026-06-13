package com.example.SOC_Lab.Repository;

import com.example.SOC_Lab.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findById(String id);
    public User findByUsernameAndPassword(String username, String password);
}
