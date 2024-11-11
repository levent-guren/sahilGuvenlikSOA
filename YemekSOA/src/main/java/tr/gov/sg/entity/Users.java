package tr.gov.sg.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	private long id;
	private String username;
	private String password;
	@ManyToMany
	@JoinTable(name = "users_role", joinColumns = {
			@JoinColumn(name = "users_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	@OneToMany(mappedBy = "users")
	private List<UserYemek> userYemeks;
}
