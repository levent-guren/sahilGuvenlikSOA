package tr.gov.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.gov.sg.feign.LoginFeign;

@RestController
public class LoginController {
	@Autowired
	private LoginFeign loginFeign;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody String requestBody) {
		String response = loginFeign.login(requestBody);
		// System.out.println("response:" + response);

		return response;
	}

}
