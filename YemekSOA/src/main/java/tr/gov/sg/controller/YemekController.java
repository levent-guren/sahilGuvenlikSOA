package tr.gov.sg.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.gov.sg.dto.GlobalResponseDTO;
import tr.gov.sg.dto.YemekEkleGuncelleRequestDTO;
import tr.gov.sg.dto.YemekResponseDTO;
import tr.gov.sg.entity.Yemek;
import tr.gov.sg.service.YemekService;

@RestController
@RequestMapping("/yemek")
public class YemekController {
	@Autowired
	private YemekService yemekService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<YemekResponseDTO> getTumYemekler() {
		List<Yemek> yemekler = yemekService.getTumYemekler();
		return yemekler.stream().map(yemek -> mapper.map(yemek, YemekResponseDTO.class)).toList();
	}

	@PostMapping
	public YemekResponseDTO yemekEkleGuncelle(@RequestBody YemekEkleGuncelleRequestDTO dto) {
		Yemek yemek = mapper.map(dto, Yemek.class);
		yemek = yemekService.guncelle(yemek);
		return mapper.map(yemek, YemekResponseDTO.class);
	}

	@DeleteMapping
	public ResponseEntity<GlobalResponseDTO> yemekSil(@RequestBody YemekEkleGuncelleRequestDTO dto) {
		if (dto.getId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GlobalResponseDTO("hata"));
		}
		yemekService.sil(dto.getId());
		return ResponseEntity.ok(new GlobalResponseDTO("basarili"));
	}
}
