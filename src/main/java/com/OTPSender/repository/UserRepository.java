package com.OTPSender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OTPSender.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}

