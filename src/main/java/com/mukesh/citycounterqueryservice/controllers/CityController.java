package com.mukesh.citycounterqueryservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mukesh.citycounterqueryservice.service.CityCounterservice;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	CityCounterservice service;
	
	@GetMapping("/city-count")
	public ResponseEntity<Integer> getCityCount(@RequestParam String letter) {
		
		if(letter.length() != 1) return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		
		long count = service.getCityCount(letter);
		return ResponseEntity.ok((int) count);
		 
	}
}
