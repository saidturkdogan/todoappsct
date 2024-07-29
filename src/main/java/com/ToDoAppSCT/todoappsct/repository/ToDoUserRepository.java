package com.ToDoAppSCT.todoappsct.repository;

import com.ToDoAppSCT.todoappsct.model.ToDoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoUserRepository extends JpaRepository<ToDoUser, Long> {
    @Query("select tu from ToDoUser tu ")
    List<ToDoUser>getAllUsers();
}
