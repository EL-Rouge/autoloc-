package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}






