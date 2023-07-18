package com.project.controller;

import com.project.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ApiService apiService;
	
	private final String API_KEY = "FB50E2191E8E06A7CA8BCC63648DEB93";
	
	@GetMapping("/gameList")
	public String callApi() throws IOException {
		StringBuilder result = new StringBuilder();
		
		String urlStr = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/" +
				"?key=" + API_KEY +
				"&format=json"
				;
		
		URL url = new URL(urlStr);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		
		
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		
		String returnLine;
		while((returnLine = br.readLine()) != null){
			result.append(returnLine+"\n\r");
		}
		urlConnection.disconnect();
		
		return result.toString();
	} // end callApi() gameList
	
	@PostMapping("/getAppData/{appId}")
	public String getAppData(@PathVariable String appId) throws IOException {
		// LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> temp
		// 		= (LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>) apiService
		// 		.getData("https://store.steampowered.com/api/appdetails?appids=" + appId + "&key=" + API_KEY)
		// 		.getBody()
		// 		;
		// System.out.print("body : ");
		// // System.out.println(temp.getClass());
		// // System.out.println(temp);
		// System.out.println("-".repeat(20));
		// try{
		// 	if(temp.get(appId).get("success").equals("true")){
		// 		System.out.println(temp.get(appId).get("data"));
		// 	}
		// } catch (NullPointerException e){
		// 	System.out.println("temp is null");
		// } catch (Exception e){
		// 	System.out.println("error");
		// 	e.printStackTrace();
		// }
		
		return "";
	} // end getAppData
}
