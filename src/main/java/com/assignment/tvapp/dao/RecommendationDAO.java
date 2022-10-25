package com.assignment.tvapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.tvapp.entity.Recommendation;

public interface RecommendationDAO extends JpaRepository<Recommendation, Integer> {

}
