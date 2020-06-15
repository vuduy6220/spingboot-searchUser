package com.example.springboot05.responsitory;

import com.example.springboot05.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public interface UserResponsitory extends CrudRepository<User, Long> {
    List<User> findUserByName(@Param("name") String name);
}
