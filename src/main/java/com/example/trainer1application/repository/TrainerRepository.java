package com.example.trainer1application.repository;

import com.example.trainer1application.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> { }