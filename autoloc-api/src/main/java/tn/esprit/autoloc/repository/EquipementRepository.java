package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
