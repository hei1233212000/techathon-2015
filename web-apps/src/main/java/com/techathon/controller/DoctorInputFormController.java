package com.techathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("doctor-input-form")
public class DoctorInputFormController {
	@RequestMapping
	public ModelAndView index() {
		return new ModelAndView("/doctor-input-form");
	}
}
