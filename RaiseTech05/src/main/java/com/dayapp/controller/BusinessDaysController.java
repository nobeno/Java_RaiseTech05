package com.dayapp.controller;

import java.util.Calendar;
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

	@RequestMapping(value = "/calculation*", method = RequestMethod.POST)
	  public ModelAndView calculation(
			  @RequestParam Integer inputYear,
			  @RequestParam Integer inputMonth,
			  @RequestParam Integer inputDay,
			  @RequestParam Integer addMonth,
			  @RequestParam Integer selectDay
			  ){
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("app/index");

	    int year = inputYear;
	    int month = inputMonth + addMonth;

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year,month-1,inputDay);

	    mv.addObject("year",year);
	    mv.addObject("month",month);

	    String[] week = new String[7];
	    	week[0]= "日";
	    	week[1]= "月";
	    	week[2]= "火";
	    	week[3]= "水";
	    	week[4]= "木";
	    	week[5]= "金";
	    	week[6]= "土";

	    if(selectDay == 0) {
	    	int Day = calendar.getActualMaximum(Calendar.DATE);
	    	calendar.set(year,month-1,Day);
	    	int week_int = calendar.get(Calendar.DAY_OF_WEEK);
	    	mv.addObject("Day",Day);
	    	mv.addObject("week",week[week_int-1]);
	    }else {
	    	int Day = selectDay;
	    	calendar.set(year,month-1,Day);
	    	int week_int = calendar.get(Calendar.DAY_OF_WEEK);
	    	mv.addObject("Day",Day);
	    	mv.addObject("week",week[week_int-1]);
	    }
	    return mv;
	  }
}
