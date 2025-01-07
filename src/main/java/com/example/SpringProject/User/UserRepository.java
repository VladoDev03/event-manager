package com.example.SpringProject.User;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T,ID> extends JpaRepository<T, ID> {

//    @Query("SELECT u FROM User u WHERE u.id = :id")
//    User getUserById(@Param("id") Long id);
}
