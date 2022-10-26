package com.assignment.tvapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.tvapp.dao.UserDAO;
import com.assignment.tvapp.entity.NewUser;
import com.assignment.tvapp.entity.SearchShow;
import com.assignment.tvapp.service.ShowRecommendationService;


@Controller
public class UserController {
	
	ShowRecommendationService service = new ShowRecommendationService();
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	UserDAO userDAO;
	
	String emailName = null;
	String url = null;
	
	@RequestMapping("/")
	public String index() {
		return "login.jsp";
	}
	
	@RequestMapping("addUser")
	public String addUser(NewUser user) {
		String email = user.getEmail();
		String userName = user.getName();
		if (email.isEmpty() || userName.isEmpty()) {
			return "invalidUser.jsp";
		}
		Long index = userDAO.getUserIndex(email);
		if(index != null) {
			return "existingUser.jsp";
		}
		userDAO.save(user);
		return "successfulSignup.jsp";
	}
	
	@RequestMapping("loginUser")
	public ModelAndView loginUser(NewUser user) {
		emailName = user.getEmail();
		Long index = userDAO.getUserIndex(emailName);
		if(index == null) {
			return new ModelAndView("notUser.jsp");
		}
		String userName = userDAO.getUserName(emailName);
		ModelAndView modelAndView = new ModelAndView("homepage.jsp");
		modelAndView.addObject("userName", userName);
		return modelAndView;
	}
	
	@RequestMapping("logout")
	public ModelAndView logoutUser() {
		ModelAndView modelAndView = new ModelAndView("login.jsp");
		return modelAndView;
	}
	
	@RequestMapping("todayStreaming")
	public ModelAndView getTodayShows(Model model){
		ModelAndView modelAndView = new ModelAndView("todayStreaming.jsp");
		url = "https://api.tvmaze.com/schedule";		
		Object[] todayShows = restTemplate.getForObject(url, Object[].class);
		model.addAttribute("todayShows", todayShows);
		return modelAndView;
	}
	
	@RequestMapping("searchShow")
	public ModelAndView getShowByName(SearchShow searchShow, Model model){
		ModelAndView modelAndView = new ModelAndView("searchResults.jsp");
		String showName = searchShow.getShowName();
		String peopleName = searchShow.getPeopleName();
		if (!showName.isEmpty()) {
			url = "https://api.tvmaze.com/search/shows?q=" + showName;
		} else if (!peopleName.isEmpty()) {
			url = "https://api.tvmaze.com/search/people?q=" + peopleName;
		}
				
		Object[] searchResults = restTemplate.getForObject(url, Object[].class);
		model.addAttribute("searchResults", searchResults);
		return modelAndView;
	}
	
	@RequestMapping("showRecommendations")
	public ModelAndView getShowRecommendation(Model model){
		NewUser user = new NewUser();
		String userName = userDAO.getUserName(emailName);
		ModelAndView modelAndView = new ModelAndView("homepage.jsp");
		Integer randomRecommendedNumber = service.randomIntegerGenerator();
		List<Integer> showIdList = userDAO.getshowIdList(emailName);
		List<Integer> randomRecommendedNumberList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			while (showIdList.contains(randomRecommendedNumber) || randomRecommendedNumberList.contains(randomRecommendedNumber)) {
				randomRecommendedNumber = service.randomIntegerGenerator();	
			}
			randomRecommendedNumberList.add(randomRecommendedNumber);
		}
		Object[] recommendedShowArray = {};
		ArrayList<Object> recommendedShowsList = new ArrayList<Object>(Arrays.asList(recommendedShowArray));
		
		for(Integer randomNumber: randomRecommendedNumberList) {
			url = "https://api.tvmaze.com/shows/" + randomNumber;
			Object recommendedShow = restTemplate.getForObject(url, Object.class);
			recommendedShowsList.add(recommendedShow);
			user.setEmail(emailName);
			user.setName(userName);
			user.setShowId(randomNumber);
			userDAO.save(user);
		}
		modelAndView.addObject("userName", userName);
		model.addAttribute("recommendedShows", recommendedShowsList);
		return modelAndView;
	}
	
	@RequestMapping("recommendationHistory")
	public ModelAndView getRecommendationHistory(Model model) {
		ModelAndView modelAndView = new ModelAndView("recommendationHistory.jsp");
		String userName = userDAO.getUserName(emailName);
		List<Integer> showIdList = userDAO.getshowIdList(emailName);
		Object[] recommendationHistoryArray = {};
		ArrayList<Object> recommendationHistoryList = new ArrayList<Object>(Arrays.asList(recommendationHistoryArray));
		for (Integer showIdInteger : showIdList) {
			url = "https://api.tvmaze.com/shows/" + showIdInteger;
			Object recommendationHistoryShow = restTemplate.getForObject(url, Object.class);
			recommendationHistoryList.add(recommendationHistoryShow);
		}
		modelAndView.addObject("userName", userName);
		model.addAttribute("recommendationHistory", recommendationHistoryList);
		return modelAndView;
	}
	
	@RequestMapping("deleteHistory")
	@Transactional
	public ModelAndView deleteRecommendationHistory() {
		ModelAndView modelAndView = new ModelAndView("recommendationHistory.jsp");
		userDAO.deleteRecommendationHistory(emailName);
		return modelAndView;
	}
}
