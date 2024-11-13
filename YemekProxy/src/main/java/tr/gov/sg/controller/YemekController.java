package tr.gov.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.gov.sg.feign.YemekFeign;

@RestController
@RequestMapping("/yemek")
public class YemekController {
	@Autowired
	private YemekFeign yemekFeign;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestHeader("Authorization") String authentication) {
		String response = yemekFeign.getTumYemekler(authentication);
		return response;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String yemekEkleGuncelle(@RequestBody String rb,
			@RequestHeader("Authorization") String authentication) {
		return yemekFeign.yemekEkleGuncelle(rb, authentication);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	// @CrossOrigin(origins = "http://localhost:4200")
	public String yemekSil(@RequestBody String rb,
			@RequestHeader("Authorization") String authentication) {
		return yemekFeign.yemekSil(rb, authentication);
	}

}
