package adapters.user;

import core.dto.DonnéesChambre;
import org.junit.jupiter.api.Test;
import ports.user.RécupérationDeToutesLesChambres;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChambresHotelPromptTest {

    @Test
    void doitAfficherUnTableauAvecÉtageNuméroEtPrixDeChaqueChambre() {
        var port = mock(RécupérationDeToutesLesChambres.class);
        when(port.récupérerToutesLesChambres()).thenReturn(
                List.of(
                        new DonnéesChambre(0, 1, 100),
                        new DonnéesChambre(1, 101, 107),
                        new DonnéesChambre(2, 201, 122)
                )
        );

        var chambresAffichées = new ChambresHotelPrompt(port).demander("afficher toutes les chambres");

        assertThat(chambresAffichées).isEqualToIgnoringWhitespace(
                """
                            | Étage | Numéro | Prix |
                            |:-----:|:------:|:----:|
                            |   0   | 1      | 100€ |
                            |   1   | 101    | 107€ |
                            |   2   | 201    | 122€ |
                        """);
    }
}