package tr.gov.sg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("LoginSOA")
public interface LoginFeign {
	// @formatter:off
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	// @formatter:on
	public String login(@RequestBody String requestBody);
}
