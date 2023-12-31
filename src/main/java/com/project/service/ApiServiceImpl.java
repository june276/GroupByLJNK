package com.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class ApiServiceImpl implements ApiService {
	
	@Value("${app.api_key}")
	private String API_KEY;
	
	@Override
	public ResponseEntity<Object> getData(String appId){
		
		String url = "https://store.steampowered.com/api/appdetails?appids=" + appId + "&key=" + API_KEY;
		//Spring restTemplate
		HashMap<String, Object> result = new HashMap<String, Object>();
		ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			HttpHeaders header = new HttpHeaders();
			HttpEntity<?> entity = new HttpEntity<>(header);
			
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
			
			resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);
			
			result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
			result.put("header", resultMap.getHeaders()); //헤더 정보 확인
			result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
			
			// 에러처리 해야됨
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body"  , e.getStatusText());
			System.out.println("error");
			System.out.println(e.toString());
			
			return resultMap;
		}
		catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body"  , "excpetion오류");
			System.out.println(e.toString());
			
			return resultMap;
			
		}
		
		return resultMap;
	}
}
