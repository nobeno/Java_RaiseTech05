package com.dayapp.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dayapp.domain.BusinessDays;
import com.dayapp.mapper.BusinessDaysMapper;


@Controller
@RequestMapping("/app")
@MapperScan(basePackages = {"com.dayapp.mapper"})
public class BusinessDaysController {

	@Autowired
	BusinessDaysMapper businessDaysMapper;

	@GetMapping
	public String index(Model model){
		List<BusinessDays> list = businessDaysMapper.selectAll();
		model.addAttribute("businessdays",list);
		return "app/index";
	}

	@RequestMapping(value = "/search*", method = RequestMethod.GET)
	  public ModelAndView search(@RequestParam Integer keyword){
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("app/index");
	    List<BusinessDays> list = businessDaysMapper.selectByIdDays(keyword);
	    mv.addObject("businessdays", list);
	    return mv;
	  }
}
