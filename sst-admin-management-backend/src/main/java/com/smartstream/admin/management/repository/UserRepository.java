package com.smartstream.admin.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartstream.admin.management.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

}