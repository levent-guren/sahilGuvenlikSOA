package tr.gov.sg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.gov.sg.entity.Users;
import tr.gov.sg.repository.UsersRepository;
import tr.gov.sg.util.Security;

@Service
public class LoginService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private Security security;

	public String login(String username, String password) {
		Optional<Users> usersO = usersRepository.findByUsernameAndPassword(username, password);
		Users user = usersO.orElseThrow();
		return security.tokenUret(user);
	}
}
