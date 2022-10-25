package com.assignment.tvapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class ShowRecommendationService {
	
	public static Integer randomIntegerGenerator() {
		Random rand = new Random();
		Integer upperbound = 55000;
		Integer int_random = rand.nextInt(upperbound);
		while (int_random == 0) {
			int_random = rand.nextInt(upperbound);
		}
		return int_random;
	}
	
	public List<Integer> getRandomIntegerList() {
		
		List<Integer> newList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
				Integer nonRepeat = randomIntegerGenerator();
				while (newList.contains(nonRepeat)) {
					nonRepeat = randomIntegerGenerator();
				} 
				newList.add(nonRepeat);
			}
		System.out.println(newList);
		return newList;
		}
}
