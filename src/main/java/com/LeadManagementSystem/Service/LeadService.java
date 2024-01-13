package com.LeadManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import com.LeadManagementSystem.Entity.Lead;

public interface LeadService {
    List<Lead> getLeadByMobileNumber(String mobileNumber);
	int createLead(Lead lead);
	List<Lead> getLeadIdsList();
	Optional<Lead> getLeadById(Lead lead);
}
