package com.dayapp.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dayapp.apicontroller.ApiController;
import com.dayapp.domain.BusinessDays;
import com.dayapp.mapper.BusinessDaysMapper;
import com.dayapp.service.BusinessDaysService;


@Controller
@RequestMapping("/app")
@MapperScan(basePackages = {"com.dayapp.mapper"})
public class BusinessDaysController {

	@Autowired
	BusinessDaysMapper businessDaysMapper;
	ApiController apiController;
	BusinessDaysService businessDaysService;

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

	@GetMapping("new")
	public String BusinessDays(Model model) {
		return "app/new";
	}

	@PostMapping
	public String create(@ModelAttribute BusinessDays businessdays) {
		businessDaysMapper.insertSelective(businessdays);
		return "redirect:/app";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		BusinessDays businessday = businessDaysMapper.selectByPrimaryKey(id);
		model.addAttribute("businessday", businessday);
		return "app/edit";
	}

	@PutMapping("{id}")
	public String update(@PathVariable int id, @ModelAttribute BusinessDays businessdays){
		businessdays.setId(id);
		businessDaysMapper.updateByPrimaryKeySelective(businessdays);
		return "redirect:/app";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable int id) {
		businessDaysMapper.deleteByPrimaryKey(id);
		return "redirect:/app";
	}

}
