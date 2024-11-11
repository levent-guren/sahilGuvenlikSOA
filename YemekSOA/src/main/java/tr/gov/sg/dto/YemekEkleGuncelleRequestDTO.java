package tr.gov.sg.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class YemekEkleGuncelleRequestDTO {
	private Long id;
	private String adi;
	private BigDecimal fiyat;
	private int kota;
}