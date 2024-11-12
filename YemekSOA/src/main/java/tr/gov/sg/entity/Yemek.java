package tr.gov.sg.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Yemek {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yemek_sequence")
	@SequenceGenerator(name = "yemek_sequence", sequenceName = "yemek_seq", allocationSize = 1)
	private long id;
	private String adi;
	private BigDecimal fiyat;
	private int kota;

	@OneToMany(mappedBy = "yemek")
	private List<UserYemek> userYemeks;
}
