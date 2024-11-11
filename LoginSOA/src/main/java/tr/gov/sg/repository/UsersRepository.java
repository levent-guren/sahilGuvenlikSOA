package tr.gov.sg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.gov.sg.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	public Optional<Users> findByUsernameAndPassword(String username, String password);
}
