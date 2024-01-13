package com.LeadManagementSystem.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "lead")
public class Lead {
	
	@Id
	@Column(nullable = false, unique = true)
	@JsonProperty("leadId")
    private Long leadId;
	
	@Column(nullable = false)
	@JsonProperty("firstName")
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabets")
    private String firstName;
    
    @Column(nullable = true)
    @JsonProperty("middleName")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Middle name must contain only alphabets")
    private String middleName;
    
    @Column(nullable = false)
    @JsonProperty("lastName")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabets")
    private String lastName;
    
    @Column(nullable = false)
    @JsonProperty("mobileNumber")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid mobile number")
    private String mobileNumber;
    
    @Column(nullable = false)
    @JsonProperty("gender")
    @Pattern(regexp = "^(Male|Female|Others)$", message = "Gender must be Male, Female, or Others")
    private String gender;
    
    @Column(nullable = false)
    @JsonProperty("DOB")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dob;
    
    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    @Email
    private String email;

	public Lead(long l, String string, String string2, String string3, String mobileNumber2, String string4,
			String string5) {
		// TODO Auto-generated constructor stub
	}

	public Lead() {
		// TODO Auto-generated constructor stub
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
