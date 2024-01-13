package com.LeadManagementSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LeadManagementSystem.Entity.Lead;
import com.LeadManagementSystem.Service.LeadService;

@RestController
@RequestMapping("/leads")
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	JSONObject response = new JSONObject();
	JSONObject errorResponse = new JSONObject();
	List<String> messages = new ArrayList<>();
	
	@PostMapping(consumes = "application/json" , produces = "application/json")
	public ResponseEntity<String> createLead(@RequestBody Lead lead){
		try {
			System.out.println("Inside Create");
			
			if(leadService.getLeadById(lead) != null) {
	        	System.out.println("Inside leadIdExists");
	            response.put("status", "error");
	            errorResponse.put("code", "E10010");
	            messages.add("Lead Already Exists in the database with the lead id");
	            errorResponse.put("messages", messages);
	            response.put("errorResponse", errorResponse);
	            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
	        }else {
	        	int createdLead = leadService.createLead(lead);
				if (createdLead > 0) {
		            response.put("status", "success");
		            response.put("data", "Created Leads Successfully");
		            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>("Error occurred while creating Lead", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
	        }
        } catch(Exception e) {
            System.out.println("Exception Occurred while creating Lead..");
            List<String> errorMessages = null;
	            Throwable rootCause = ExceptionUtils.getRootCause(e);
	            if (rootCause instanceof ConstraintViolationException) {

	            	 ConstraintViolationException constraintViolationException = (ConstraintViolationException) rootCause;
	                 errorMessages = constraintViolationException.getConstraintViolations()
	                         .stream()
	                         .map((Function<ConstraintViolation<?>, String>) ConstraintViolation::getMessage)
	                         .collect(Collectors.toList());
	                 
	            }
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping(value = "/{mobileNumber}", produces = "application/json")
	public ResponseEntity<?> getLeadByMobileNumber(@PathVariable String mobileNumber){
	    List<Lead> leadListByMobNo = leadService.getLeadByMobileNumber(mobileNumber);

	    if (leadListByMobNo.isEmpty() || leadListByMobNo == null) {
	        try {
	            response.put("status", "error");
	            errorResponse.put("code", "E10011");
	            messages.add("No Lead found with the Mobile Number ");
	            errorResponse.put("messages", messages);
	            response.put("errorResponse", errorResponse);
	            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
	        } catch (JSONException e) {
	            //e.printStackTrace();
	            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
	        }
	    } else {
	        return ResponseEntity.ok(leadListByMobNo);
	    }
	}
}
