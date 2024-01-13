package com.LeadManagementSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.LeadManagementSystem.Entity.Lead;
import com.LeadManagementSystem.Service.LeadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class LeadManagementSystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LeadService leadService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void LeadControllerTest_getLeadByMobileNumber() throws Exception {
		String mobileNumber = "9834751111";

		List<Lead> mockLeadList = new ArrayList<>();
		mockLeadList.add(
				new Lead(10L, "Ghanshyam", "Lunge", "16/10/1999", mobileNumber, "Male", "ghanshamlunge1@gmail.com"));
		Mockito.when(leadService.getLeadByMobileNumber(mobileNumber)).thenReturn(mockLeadList);

		mockMvc.perform(get("/leads/{mobileNumber}", mobileNumber).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		Mockito.verify(leadService, Mockito.times(1)).getLeadByMobileNumber(mobileNumber);
	}

//	Below LeadControllerTest_createLead is getting failed and getting java.lang.NoSuchMethodError due to conflict in JSON versions which we are using
	@Test
	public void LeadControllerTest_createLead() throws Exception {
		Lead lead = new Lead();
		lead.setLeadId(10L);
		lead.setFirstName("Ghanshyam");
		lead.setMiddleName("Gopal");
		lead.setLastName("Lunge");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		lead.setDob(dateFormat.parse("16/10/1999"));
		lead.setMobileNumber("9834751111");
		lead.setGender("Male");
		lead.setEmail("ghanshamlunge1@gmail.com");

		Mockito.when(leadService.createLead(lead)).thenReturn(1);

		mockMvc.perform(post("/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.data", is("Created Leads Successfully")));


		Mockito.verify(leadService, Mockito.times(1)).createLead(Mockito.any(Lead.class));
	}
}
