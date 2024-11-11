package tr.gov.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.gov.sg.dto.LoginRequestDTO;
import tr.gov.sg.dto.LoginResponseDTO;
import tr.gov.sg.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
		return new LoginResponseDTO(loginService.login(dto.getUsername(), dto.getPassword()));
	}

}
