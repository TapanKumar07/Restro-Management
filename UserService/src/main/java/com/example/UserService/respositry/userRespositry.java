package com.example.UserService.respositry;

import com.example.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRespositry extends JpaRepository<User, String> {
}
