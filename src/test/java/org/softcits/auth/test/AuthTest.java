package org.softcits.auth.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenAddUser() throws UnsupportedEncodingException, Exception {
		String result = mockMvc.perform(
				(post("/user/add"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "juno")
				.param("passwd", "123456")
				.param("repasswd", "123456")
				).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenLogin() throws UnsupportedEncodingException, Exception {
		String result = mockMvc.perform(
				(post("/user/login"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "admin")
				.param("passwd", "123456")
				).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.username").value("admin"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
}
