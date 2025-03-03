package com.greeting.app.Greeting_App.repository;

import com.greeting.app.Greeting_App.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<com.greeting.app.Greeting_App.model.Greeting, Long>{
        }