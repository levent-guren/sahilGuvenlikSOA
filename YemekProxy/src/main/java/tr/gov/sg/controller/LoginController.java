package tr.gov.sg.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import tr.gov.sg.feign.LoginFeign;

@RestController
public class LoginController {
	@Autowired
	private LoginFeign loginFeign;
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestClient.Builder restBuilder;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	// @CrossOrigin(origins = { "http://localhost:4200" })
	public String login(@RequestBody String requestBody) {
		String response = loginFeign.login(requestBody);
		return response;
	}

	@PostMapping(value = "/login2", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login2(@RequestBody String requestBody) {
		List<InstanceInfo> instances = eurekaClient.getApplication("LoginSOA")
				.getInstances();
		InstanceInfo instance = instances.get(new Random().nextInt(instances.size()));

		String hostname = instance.getHostName();
		int port = instance.getPort();
//		System.out.println(hostname);
//		System.out.println(port);

		RestClient restClient = restBuilder.baseUrl("http://" + hostname + ":" + port)
				.build();
		// @formatter:off
		String response = restClient.post().uri("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(requestBody).retrieve().body(String.class);
		// @formatter:on
		return response;
	}

}
