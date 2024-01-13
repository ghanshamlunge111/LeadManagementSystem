package com.LeadManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LeadManagementSystem.Entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByMobileNumber(String mobileNumber);
    Lead save(Lead lead);
	List<Lead> findAll();
}
