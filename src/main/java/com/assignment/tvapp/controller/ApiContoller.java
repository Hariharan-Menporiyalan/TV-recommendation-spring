package com.assignment.tvapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.tvapp.entity.SearchShow;
import com.assignment.tvapp.service.ShowRecommendationService;

@RestController
public class ApiContoller {
	
	ShowRecommendationService service = new ShowRecommendationService();
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("todayStreaming")
	public ModelAndView getTodayShows(Model model){
		ModelAndView modelAndView = new ModelAndView("todayStreaming.jsp");
		String url = "https://api.tvmaze.com/schedule";		
		Object[] todayShows = restTemplate.getForObject(url, Object[].class);
		model.addAttribute("todayShows", todayShows);
		return modelAndView;
	}
	
	@RequestMapping("searchShow")
	public ModelAndView getShowByName(SearchShow searchShow, Model model){
		ModelAndView modelAndView = new ModelAndView("searchResults.jsp");
		String url = null;
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
		ModelAndView modelAndView = new ModelAndView("homepage.jsp");
		String url = null;
		List<Integer> randomRecommendedNumberList = service.getRandomIntegerList();
		//url = "https://api.tvmaze.com/shows/" + 33;
		//Object recommendedShow = restTemplate.getForObject(url, Object.class);
		Object[] recommendedShowArray = {};
		ArrayList<Object> recommendedShowsList = new ArrayList<Object>(Arrays.asList(recommendedShowArray));
		//recommendedShowsList.add(recommendedShow);
		for(Integer randomNumber: randomRecommendedNumberList) {
			url = "https://api.tvmaze.com/shows/" + randomNumber;
			Object recommendedShow = restTemplate.getForObject(url, Object.class);
			recommendedShowsList.add(recommendedShow);
		}
		model.addAttribute("recommendedShows", recommendedShowsList);
		return modelAndView;
	}

}
