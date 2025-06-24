package core;

import core.domain.Chambre;
import core.domain.Hôtel;
import core.domain.PrixEnEuros;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HôtelTest {

    @Test
    void neDoitPasPermettreDeModifierIndividuellementLePrixDesChambres() {
        var prixInitial = 1000;
        Hôtel hôtel = new Hôtel(List.of(new Chambre.DonnéesCréation(0, 1)), prixInitial);

        var premièreChambre = hôtel.récupérerToutesLesChambres().getFirst();
        premièreChambre.définirPrixÀ(new PrixEnEuros(1200));

        var premièreChambreDansHotel = hôtel.récupérerToutesLesChambres().getFirst();
        assertThat(premièreChambreDansHotel.state().prix()).isEqualTo(prixInitial);
    }
}