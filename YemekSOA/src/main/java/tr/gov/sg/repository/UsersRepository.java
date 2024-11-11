package tr.gov.sg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.gov.sg.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
