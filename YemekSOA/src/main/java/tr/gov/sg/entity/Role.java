package tr.gov.sg.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	private long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private List<Users> users;
}
