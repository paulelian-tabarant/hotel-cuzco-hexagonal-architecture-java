package core;

import adapters.server_side.StockageHôtelInMemory;
import core.domain.Chambre;
import core.domain.Hôtel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ports.server_side.StockageHôtel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HôtelServiceTest {
    private StockageHôtel serverSidePort;
    private HôtelService service;

    @BeforeEach()
    void setUp() {
        serverSidePort = new StockageHôtelInMemory();
        service = new HôtelService(serverSidePort);
    }

    @Test
    void doitFournirToutesLesChambresEnregistrées() {
        var chambres = List.of(
                new Chambre.DonnéesCréation(0, 1),
                new Chambre.DonnéesCréation(0, 2),
                new Chambre.DonnéesCréation(0, 3)
        );
        serverSidePort.enregistrer(new Hôtel(chambres, 100));

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                new Chambre.Données(0, 1, 100),
                new Chambre.Données(0, 2, 100),
                new Chambre.Données(0, 3, 100)
        ));
    }

    @Test
    void doitPrendreEnCompteLaVariationDePrixEntreLesÉtages() {
        var chambresÀEnregistrer = List.of(
                new Chambre.DonnéesCréation(0, 1),
                new Chambre.DonnéesCréation(1, 101),
                new Chambre.DonnéesCréation(2, 201),
                new Chambre.DonnéesCréation(3, 301)
        );
        serverSidePort.enregistrer(new Hôtel(chambresÀEnregistrer, 100));

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                new Chambre.Données(0, 1, 100),
                new Chambre.Données(1, 101, 107),
                new Chambre.Données(2, 201, 122),
                new Chambre.Données(3, 301, 133)
        ));
    }

    @Test
    void doitPrendreEnCompteLaVariationDePrixEntreLesÉtagesQuandLePrixDuRezDeChausséeEstModifié() {
        var chambresAEnregistrer = List.of(
                new Chambre.DonnéesCréation(0, 1),
                new Chambre.DonnéesCréation(1, 101),
                new Chambre.DonnéesCréation(2, 201),
                new Chambre.DonnéesCréation(3, 301)
        );
        serverSidePort.enregistrer(new Hôtel(chambresAEnregistrer, 100));

        service.définirLePrixDuRezDeChausséeÀ(1000);

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                        new Chambre.Données(0, 1, 1000),
                        new Chambre.Données(1, 101, 1070),
                        new Chambre.Données(2, 201, 1220),
                        new Chambre.Données(3, 301, 1330)
                )
        );
    }
}
