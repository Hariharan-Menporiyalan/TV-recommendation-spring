package com.assignment.tvapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.tvapp.dao.UserDAO;
import com.assignment.tvapp.entity.NewUser;

@Controller
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/")
	public String index() {
		return "login.jsp";
	}
	
	@RequestMapping("addUser")
	public String addUser(NewUser user) {
		String emailId = user.getEmail();
		String userName = user.getName();
		if (emailId.isEmpty() || userName.isEmpty()) {
			return "invalidUser.jsp";
		}
		Optional<NewUser> email = userDAO.findById(emailId);
		if(email.isPresent()) {
			return "existingUser.jsp";
		}
		userDAO.save(user);
		return "successfulSignup.jsp";
	}
	
	@RequestMapping("loginUser")
	public ModelAndView loginUser(NewUser user) {
		Optional<NewUser> email = userDAO.findById(user.getEmail());
		if(!email.isPresent()) {
			return new ModelAndView("notUser.jsp");
		}
		String userName = userDAO.getUserName(user.getEmail());
		ModelAndView modelAndView = new ModelAndView("homepage.jsp");
		modelAndView.addObject("userName", userName);
		return modelAndView;
	}
}
