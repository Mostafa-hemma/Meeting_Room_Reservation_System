package com.backend.fullProject.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@GetMapping("/user/hello")
    public String userHello() {
        return "Hello User";
    }
	
	@GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin";
    }
	@GetMapping("/customer/hello")
    public String customerHello() {
        return "Hello customer";
    }

}
