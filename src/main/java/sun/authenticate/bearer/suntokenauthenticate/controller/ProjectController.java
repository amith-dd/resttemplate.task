package sun.authenticate.bearer.suntokenauthenticate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import sun.authenticate.bearer.suntokenauthenticate.dto.UserDto;

@Controller
public class ProjectController {

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/index")
	public String showLogin() {
		return "login.html";
	}

	@PostMapping("/login")
	public String generateToken(@RequestParam String login_id, @RequestParam String password)
			throws RestClientException {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("login_id", login_id);
			jsonMap.put("password", password);

			Gson gson = new Gson();
			String jsonRequestBody = gson.toJson(jsonMap);

			HttpEntity<String> httpEntity = new HttpEntity<>(jsonRequestBody, headers);

			String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

			String apiResponse = restTemplate.postForObject(apiUrl, httpEntity, String.class);

			return apiResponse;

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}

	@RequestMapping("/create")
	public ModelAndView createUser() {
		ModelAndView view = new ModelAndView();
		view.setViewName("create");
		view.addObject("user", new UserDto());
		return view;

	}

	@RequestMapping("/save")
	public String saveUser(@ModelAttribute UserDto u) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("Authorization", "Bearer " + "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");

		Map<String, Object> jsonMap = new HashMap<>();

		jsonMap.put("first_name", u.getFirst_name());
		jsonMap.put("last_name", u.getLast_name());
		jsonMap.put("street", u.getStreet());
		jsonMap.put("address", u.getAddress());
		jsonMap.put("city", u.getCity());
		jsonMap.put("state", u.getState());
		jsonMap.put("email", u.getEmail());
		jsonMap.put("phone", u.getPhone());

		Gson g = new Gson();
		String jsonBody = g.toJson(jsonMap);

		HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, headers);

		String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";

		String apiResponse = restTemplate.postForObject(apiUrl, httpEntity, String.class);

		return apiResponse;

	}

	@RequestMapping("/getall")
	public ModelAndView getAll() throws JsonMappingException, JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

		ResponseEntity<String> apiResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);

		String out = apiResponse.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		List<UserDto> userDtoList = objectMapper.readValue(out, new TypeReference<List<UserDto>>() {});

		ModelAndView view = new ModelAndView();
		view.addObject("users", userDtoList);
		view.setViewName("display");

		return view;

	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam String id) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");
		
		HttpEntity<String> httpEntity = new HttpEntity<>( headers);
		
		String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+id;
		
		String apiResponse = restTemplate.postForObject(apiUrl, httpEntity, String.class);
		
		ModelAndView view = getAll();
		return view;
	
	}
	
//	@RequestParam("/edit")
//	public ModelAndView edit(@RequestParam String id) {
//		
//	}
	
	

}
