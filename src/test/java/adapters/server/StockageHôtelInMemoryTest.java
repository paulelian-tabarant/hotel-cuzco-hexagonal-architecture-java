package adapters.server;

import core.domain.Hôtel;
import core.domain.PrixEnEuros;
import core.dto.DonnéesCréationChambre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StockageHôtelInMemoryTest {
    StockageHôtelInMemory stockageHôtelInMemory;

    @BeforeEach
    void setUp() {
        stockageHôtelInMemory = new StockageHôtelInMemory();
    }

    @Test
    void doitEnregistrerUnHôtelComplet() {
        Hôtel hôtelÀEnregistrer = new Hôtel(List.of(
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(0, 2),
                new DonnéesCréationChambre(1, 101),
                new DonnéesCréationChambre(2, 201)
        ),
                1000
        );
        stockageHôtelInMemory.enregistrer(hôtelÀEnregistrer);

        assertThat(stockageHôtelInMemory.récupérer()).usingRecursiveComparison().isEqualTo(hôtelÀEnregistrer);
    }

    @Test
    void doitConserverLHotelExistantTantQuIlNEstPasSauvegardéÀNouveau() {
        Hôtel hôtelInitial = new Hôtel(List.of(
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(0, 2),
                new DonnéesCréationChambre(1, 101),
                new DonnéesCréationChambre(2, 201)
        ), 1000);
        stockageHôtelInMemory.enregistrer(hôtelInitial);

        stockageHôtelInMemory.récupérer().définirLePrixDuRezDeChausséeÀ(new PrixEnEuros(1200));

        assertThat(stockageHôtelInMemory.récupérer()).usingRecursiveComparison().isEqualTo(hôtelInitial);
    }
}
