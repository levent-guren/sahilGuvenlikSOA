package tr.gov.sg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("YemekSOA")
public interface YemekFeign {
	// @formatter:off
	@GetMapping(value = "/yemek", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	// @formatter:on
	public String getTumYemekler(@RequestHeader("Authorization") String authentication);

	// @formatter:off
	@PostMapping(value = "/yemek", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	// @formatter:on
	public String yemekEkleGuncelle(@RequestBody String requestBody,
			@RequestHeader("Authorization") String authentication);

	// @formatter:off
	@DeleteMapping(value = "/yemek", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	// @formatter:on
	public String yemekSil(@RequestBody String requestBody,
			@RequestHeader("Authorization") String authentication);
}
