package com.OTPSender.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.OTPSender.services.OTPService;
import com.OTPSender.services.UserService;

@Controller
public class UserController {

	String otp;
	@Autowired
	private UserService userservice;
	@Autowired
	private OTPService otpservice;
	
	@GetMapping("/login")
	public String getlogin(Model model) {
		return "adminLogin";
	}
	
	@PostMapping("/loginsts")
	public String getOtp(@RequestParam ("username") String Username) {
		String demo="+91"+Username;
		otp=userservice.generateOtp(Username);
		otpservice.sendOtp(demo,otp);
		System.out.println(otp);
		
		return "success";
	}
	
	@GetMapping("/verifyOtp")
    public String showVerifyOtpPage(Model model) {
        return "verifyOtp";
    }
    
	@PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") String enteredOtp, Model model) {
        if (enteredOtp.equals(otp)) {
            model.addAttribute("message", "OTP verified successfully!");
            return "OTPVerifiedSuccess";
        } else {
            model.addAttribute("message", "Invalid OTP, please try again.");
            return "verifyOtp";
        }
	}
}
