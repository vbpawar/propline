package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
