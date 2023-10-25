package com.rubbypaper.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@GetMapping("/home") //메인페이지나 다른 페이지에서 "Home" 또는 "Color Pharmacy Photo Conversion Website" 클릭시 index.html로 보내는 요청 
	public String home()
	{
		return "index";
	}
	@GetMapping("/about") // "About" 페이지나 다른 페이지에서 "About" 클릭시 "about.html"로 보내는 코드
	public String about()
	{
		return "about";
	}
}