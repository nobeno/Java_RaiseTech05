package com.dayapp.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dayapp.domain.BusinessDays;
import com.dayapp.mapper.BusinessDaysMapper;

@RestController
@RequestMapping("/app")
public class ApiController {

	@Autowired
	BusinessDaysMapper businessDaysMapper;

	@RequestMapping(value = "/api/search*", method = RequestMethod.GET)
	  public List<BusinessDays> getBusinessDay(@RequestParam Integer keyword){
	    List<BusinessDays> list = businessDaysMapper.selectByIdDays(keyword);
	    return list;
	  }
}
