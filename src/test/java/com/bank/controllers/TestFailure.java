package com.bank.controllers;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestFailure {

	private MockMvc mockMvc;
	
	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/"))
        	.andExpect(status().isOk());
	}

}
