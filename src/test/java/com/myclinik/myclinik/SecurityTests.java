package com.myclinik.myclinik;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTests {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	public void loginAvailableForAll() throws Exception {
		mockMvc
				.perform(get("/login"))
				.andExpect(status().isOk());
	}

	@Test
	public void adminCanLog() throws Exception {
		canLog("admin", "admin");
	}

	@Test
	public void opsCanLog() throws Exception {
		canLog("ops", "ops");
	}

	@Test
	public void contCanLog() throws Exception {
		canLog("cont", "cont");
	}

	@WithMockUser(username = "admin", authorities = { "ADMIN" })
	@Test
	public void adminAccess() throws Exception {
		mockMvc
				.perform(get("/admin"))
				.andExpect(status().isOk());
		mockMvc
				.perform(get("/clients"))
				.andExpect(status().isOk());
		mockMvc
				.perform(get("/statistics"))
				.andExpect(status().isOk());
	}

	@WithMockUser(username = "ops", authorities = { "OPS" })
	@Test
	public void opsAccess() throws Exception {
		mockMvc
				.perform(get("/admin"))
				.andExpect(status().isForbidden());
		mockMvc
				.perform(get("/clients"))
				.andExpect(status().isOk());
		mockMvc
				.perform(get("/statistics"))
				.andExpect(status().isForbidden());
	}

	@WithMockUser(username = "cont", authorities = { "CONT" })
	@Test
	public void contAccess() throws Exception {
		mockMvc
				.perform(get("/admin"))
				.andExpect(status().isForbidden());
		mockMvc
				.perform(get("/clients"))
				.andExpect(status().isForbidden());
		mockMvc
				.perform(get("/statistics"))
				.andExpect(status().isOk());
	}

	@Test
	public void invalidLoginDenied() throws Exception {
		String loginErrorUrl = "/login?error";
		String errorMessage = "Usuario o contraseña no válidos";
		mockMvc
				.perform(formLogin().password("invalid"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl(loginErrorUrl))
				.andExpect(unauthenticated());

		mockMvc
				.perform(get(loginErrorUrl))
				.andExpect(content().string(containsString(errorMessage)));
	}

	private void canLog(String username, String password) throws Exception {
		mockMvc
				.perform(formLogin().user(username).password(password))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/"))
				.andExpect(authenticated().withUsername(username));

		mockMvc
				.perform(logout())
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/login?logout"));
	}

}
