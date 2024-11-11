package tr.gov.sg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.gov.sg.entity.Yemek;

public interface YemekRepository extends JpaRepository<Yemek, Long> {

}
