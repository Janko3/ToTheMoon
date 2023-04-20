package com.example.tothemoon.repository;

import com.example.tothemoon.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
