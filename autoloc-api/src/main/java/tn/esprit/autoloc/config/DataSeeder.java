package tn.esprit.autoloc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public void run(String... args) {
        if (vehiculeRepository.count() > 0) {
            return;
        }

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("TUN-1234");
        v1.setMarque("Peugeot");
        v1.setModele("208");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("60.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("TUN-5678");
        v2.setMarque("Volkswagen");
        v2.setModele("Golf");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("90.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule v3 = new Vehicule();
        v3.setImmatriculation("TUN-9012");
        v3.setMarque("Toyota");
        v3.setModele("RAV4");
        v3.setCategorie(CategorieVehicule.SUV);
        v3.setTarifJournalier(new BigDecimal("150.00"));
        v3.setStatut(StatutVehicule.MAINTENANCE);

        vehiculeRepository.saveAll(List.of(v1, v2, v3));

        log.info("3 véhicules de démonstration insérés avec succès.");
    }
}
