package com.assignment.tvapp.service;

import java.util.Random;


public class ShowRecommendationService {
	
	public Integer randomIntegerGenerator() {
		Random rand = new Random();
		Integer upperbound = 55000;
		Integer int_random = rand.nextInt(upperbound);
		while (int_random == 0) {
			int_random = rand.nextInt(upperbound);
		}
		return int_random;
	}
}
