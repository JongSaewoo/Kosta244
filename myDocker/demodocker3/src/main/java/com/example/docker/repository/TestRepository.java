package com.example.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.docker.entity.Test;

public interface TestRepository extends JpaRepository<Test, String> {

}
