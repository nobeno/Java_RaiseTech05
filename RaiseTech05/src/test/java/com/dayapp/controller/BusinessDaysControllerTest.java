package com.dayapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class BusinessDaysControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private BusinessDaysController target;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	public void indexへのリクエスト結果が正常でViewとしてindexが返る() throws Exception{
		mockMvc.perform(get("/app"))
				.andExpect(status().isOk())
				.andExpect(view().name("app/index"));
	}

	@Test
	public void 新規登録ページへのリクエスト結果が正常でViewとしてnewが返る() throws Exception{
		mockMvc.perform(get("/app/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("app/new"));
	}

	@Test
	public void 更新ページへのリクエスト結果が正常でViewとしてeditが返る() throws Exception{
		mockMvc.perform(get("/app/{id}/edit",1))
			.andExpect(status().isOk())
			.andExpect(view().name("app/edit"));
	}

}
