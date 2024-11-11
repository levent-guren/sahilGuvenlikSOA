package tr.gov.sg.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Yemek {
	@Id
	private long id;
	private String adi;
	private BigDecimal fiyat;
	private int kota;

	@OneToMany(mappedBy = "yemek")
	private List<UserYemek> userYemeks;
}
