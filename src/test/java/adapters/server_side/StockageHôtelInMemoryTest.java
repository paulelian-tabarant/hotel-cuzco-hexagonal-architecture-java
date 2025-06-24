package adapters.server_side;

import core.domain.Chambre;
import core.domain.Hôtel;
import core.domain.PrixEnEuros;
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
                new Chambre.DonnéesCréation(0, 1),
                new Chambre.DonnéesCréation(0, 2),
                new Chambre.DonnéesCréation(1, 101),
                new Chambre.DonnéesCréation(2, 201)
        ),
                1000
        );
        stockageHôtelInMemory.enregistrer(hôtelÀEnregistrer);

        assertThat(stockageHôtelInMemory.récupérer()).usingRecursiveComparison().isEqualTo(hôtelÀEnregistrer);
    }

    @Test
    void doitConserverLHotelExistantTantQuIlNEstPasSauvegardéÀNouveau() {
        Hôtel hôtelInitial = new Hôtel(List.of(
                new Chambre.DonnéesCréation(0, 1),
                new Chambre.DonnéesCréation(0, 2),
                new Chambre.DonnéesCréation(1, 101),
                new Chambre.DonnéesCréation(2, 201)
        ), 1000);
        stockageHôtelInMemory.enregistrer(hôtelInitial);

        stockageHôtelInMemory.récupérer().définirLePrixDuRezDeChausséeÀ(new PrixEnEuros(1200));

        assertThat(stockageHôtelInMemory.récupérer()).usingRecursiveComparison().isEqualTo(hôtelInitial);
    }
}
