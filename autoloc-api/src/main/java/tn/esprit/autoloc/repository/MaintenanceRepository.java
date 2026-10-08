package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}




