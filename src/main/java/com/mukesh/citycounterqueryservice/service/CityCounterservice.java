package com.mukesh.citycounterqueryservice.service;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityCounterservice {
	
	private static final String API = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";

	
	@Autowired
	RestTemplate rest;
	
	public Long getCityCount(String letter) {
		// fetch data as String
		String response = rest.getForObject(API, String.class);
		
		// Convert JSON to Object
		JSONObject responseObject = new JSONObject(response);
		
		
		// get Array List from Object
		JSONArray cityList = responseObject.getJSONArray("list");
		
		// Filter data based on First letter
 		long count = cityList.toList().stream()
 				.map(item -> new JSONObject((Map<?,?>) item).getString("name"))
				.filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
				.count();
 		
		return count;
	}
}
