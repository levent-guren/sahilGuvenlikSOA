package tr.gov.sg.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users_yemek")
@Data
public class UserYemek {
	@Id
	private long id;
	private Date tarih;
	@ManyToOne
	private Users users;
	@ManyToOne
	private Yemek yemek;
}
