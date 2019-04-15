package com.lunarllc.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	//create a mapping for /hello
	@GetMapping("/hello")
	public String sayHello(Model theModel) {
		theModel.addAttribute("theDate", new java.util.Date());
		return "helloworld";
		//spring boot will auto-configure to use Thymeleaf
		//src/main/resource/templates/helloworld
	}
	

}
