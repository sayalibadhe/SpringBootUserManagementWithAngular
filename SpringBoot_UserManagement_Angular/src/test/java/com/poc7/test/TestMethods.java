package com.poc7.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.poc7.model.ResultModel;
import com.poc7.model.User;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TestMethods {
	
private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objm = new ObjectMapper();
	
	@BeforeAll
	public void setUp()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).dispatchOptions(true).build();
	}

	@Test
	public void getAllUsers() throws Exception{
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.get("/api/getAllUserTest")
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		ResultModel response = objm.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}
	
	@Test
	public void addUserTest() throws Exception{
		User user=new User();
		user.setFname("Raju");
		user.setLname("BAdhe");
		user.setContact("9078675643");
		user.setEmail("raju@gmail.com");
		user.setCity("abc");
		 String JsonRequest= objm.writeValueAsString(user);
		 MvcResult result = mockMvc .perform(
				  post("/api/createUser").content(JsonRequest).contentType(
				  MediaType.APPLICATION_JSON_VALUE)) .andExpect(status().isOk()).andReturn();

			
		String resultContext = result.getResponse().getContentAsString();
		ResultModel response = objm.readValue(resultContext, ResultModel.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
	}
	 @Test
	 
		public void getUserByIdTest() throws Exception{
			 int id=3;
			 MvcResult result = mockMvc
						.perform(
								MockMvcRequestBuilders.get("/api/getUserById/" + id)
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
				String resultContent = result.getResponse().getContentAsString();
			
				ResultModel response = objm.readValue(resultContent, ResultModel.class);
				Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
				Assertions.assertEquals("Success",response.getProgressMessage());
		 }
	 
	 
	 @Test
	 
		public void updateUserTest() throws Exception{
		 
			User user=new User();
			int id=4;
			//user.setId(9);
			user.setLname("xyz");
			user.setFname("zbc");
			user.setContact("9930370089");
			user.setCity("thane");
			;
			user.setEmail("dhruv@gmail.com");
		    String JsonRequest= objm.writeValueAsString(user);
			
		    MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.put("/api/updateUser/"+id).content(JsonRequest)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContext = result.getResponse().getContentAsString();
			
			ResultModel response = objm.readValue(resultContext, ResultModel.class);
			Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
			Assertions.assertEquals("Success",response.getProgressMessage());
	 }
	 
	 @Test
	 
	public void deleteUserTest() throws Exception{
		 int id=1;
		 
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.delete("/api/deletUser/"+id)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			ResultModel response = objm.readValue(resultContent, ResultModel.class);
			Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
			Assertions.assertEquals("Success",response.getProgressMessage());
	 }
}