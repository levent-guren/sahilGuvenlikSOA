package tr.gov.sg.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	private long id;
	private String username;
	private String password;
	@ManyToMany
	private List<Role> roles;
}
