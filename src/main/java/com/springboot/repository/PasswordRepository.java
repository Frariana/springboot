package com.springboot.repository;

import java.util.List;
import com.springboot.model.Password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long>{
}
