package com.LeadManagementSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.LeadManagementSystem.Entity.Lead;
import com.LeadManagementSystem.Repository.LeadRepository;


@Service
public class LeadServiceImpl implements LeadService {

	@Autowired
	private LeadRepository leadRepository;

	//	@Override
	//    public Lead createLead(Lead lead) {
	//		System.out.println("Inside Create Lead Service Impl");
	//		return leadRepository.save(lead);
	//        // Perform validations and create lead in the database
	//    }

	@Override
	@Transactional
	public int createLead(Lead lead) {
		System.out.println("Inside Create Lead Service Impl");
		try {
			this.leadRepository.save(lead);
			return 1;
		} catch (Exception e) {
			System.out.println("Exception occurred while creating Lead: " + e.getMessage());
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        throw e;
		}
		//return -1;
	}
	@Override
	public List<Lead> getLeadByMobileNumber(String mobileNumber) {
		return leadRepository.findByMobileNumber(mobileNumber);
	}
	
	@Override
	public List<Lead> getLeadIdsList() {
		List<Lead> leadIdsList = new ArrayList<Lead>();
		leadIdsList = leadRepository.findAll();
		return leadIdsList;
	}
	
	@Override
	public Optional<Lead> getLeadById(Lead lead) {
		return leadRepository.findById(lead.getLeadId());
		
	}
}