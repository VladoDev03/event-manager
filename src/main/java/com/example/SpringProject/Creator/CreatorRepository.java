package com.example.SpringProject.Creator;

import com.example.SpringProject.User.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends UserRepository<Creator,Long> {

}
