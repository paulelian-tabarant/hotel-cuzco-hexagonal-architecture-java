package core;

import adapters.server.StockageHôtelInMemory;
import core.domain.Hôtel;
import core.dto.DonnéesChambre;
import core.dto.DonnéesCréationChambre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ports.server.StockageHôtel;

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
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(0, 2),
                new DonnéesCréationChambre(0, 3)
        );
        serverSidePort.enregistrer(new Hôtel(chambres, 100));

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                new DonnéesChambre(0, 1, 100),
                new DonnéesChambre(0, 2, 100),
                new DonnéesChambre(0, 3, 100)
        ));
    }

    @Test
    void doitPrendreEnCompteLaVariationDePrixEntreLesÉtages() {
        var chambresÀEnregistrer = List.of(
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(1, 101),
                new DonnéesCréationChambre(2, 201),
                new DonnéesCréationChambre(3, 301)
        );
        serverSidePort.enregistrer(new Hôtel(chambresÀEnregistrer, 100));

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                new DonnéesChambre(0, 1, 100),
                new DonnéesChambre(1, 101, 107),
                new DonnéesChambre(2, 201, 122),
                new DonnéesChambre(3, 301, 133)
        ));
    }

    @Test
    void doitPrendreEnCompteLaVariationDePrixEntreLesÉtagesQuandLePrixDuRezDeChausséeEstModifié() {
        var chambresAEnregistrer = List.of(
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(1, 101),
                new DonnéesCréationChambre(2, 201),
                new DonnéesCréationChambre(3, 301)
        );
        serverSidePort.enregistrer(new Hôtel(chambresAEnregistrer, 100));

        service.définirLePrixDuRezDeChausséeÀ(1000);

        assertThat(service.récupérerToutesLesChambres()).usingRecursiveComparison().isEqualTo(List.of(
                        new DonnéesChambre(0, 1, 1000),
                        new DonnéesChambre(1, 101, 1070),
                        new DonnéesChambre(2, 201, 1220),
                        new DonnéesChambre(3, 301, 1330)
                )
        );
    }
}
