
package sun.authenticate.bearer.suntokenauthenticate.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/getweather")
	public ResponseEntity<String> getWeather(@RequestParam String city,@RequestParam String access_key ) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String apiUrl = "http://api.weatherstack.com/current?access_key="+access_key+"&query="+city;

		HttpEntity<String> httpEntity = new HttpEntity<>( headers);
		
		ResponseEntity<String> apiResponse = restTemplate.exchange(apiUrl,HttpMethod.GET,httpEntity,String.class);
		
		
		return apiResponse;
		
		
	}
	
	
	
}
