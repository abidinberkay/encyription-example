package com.icefire.icefirebackend.repository;

import com.icefire.icefirebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    User findByEmailAndPassword(String email, String password);

}