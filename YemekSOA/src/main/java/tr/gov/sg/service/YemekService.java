package tr.gov.sg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.gov.sg.entity.Yemek;
import tr.gov.sg.repository.YemekRepository;

@Service
public class YemekService {
	@Autowired
	private YemekRepository yemekRepository;

	public List<Yemek> getTumYemekler() {
		return yemekRepository.findAll();
	}

	public Yemek guncelle(Yemek yemek) {
		return yemekRepository.save(yemek);
	}

	public void sil(long id) {
		yemekRepository.deleteById(id);
	}
}
