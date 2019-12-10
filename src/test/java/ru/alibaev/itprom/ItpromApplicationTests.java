package ru.alibaev.itprom;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItpromApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	ItpromApplicationTests() {
	}

	@Test
	void testProfessionCreate (){
		String url = "http://localhost:" + port + "/profession/create";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Svarschik");
		jsonObject.put("note", "Some profession");
		HttpEntity<String> request =
				new HttpEntity<>(jsonObject.toString(), headers);
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, request, Void.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testDepartmentCreate (){
		String url = "http://localhost:" + port + "/department/create";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "RZA");
		jsonObject.put("note", "Some dep");
		HttpEntity<String> request =
				new HttpEntity<>(jsonObject.toString(), headers);
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, request, Void.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
