package com.dayapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dayapp.domain.BusinessDays;

@Service
public class BusinessDaysService {

	@Autowired
	@Qualifier("businessDaysSearchRestTemplate")
	RestTemplate restTemplate;

	private static final String URL ="http://localhost:8080/app/api/search?keyword={keyword}";

	public BusinessDays service(Integer keyword) {
		return restTemplate.getForObject(URL, BusinessDays.class, keyword);
	}
}
