package adapters.user_side;

import core.domain.Chambre;
import org.junit.jupiter.api.Test;
import ports.user_side.RécupérationDeToutesLesChambres;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChambresCommandesTest {

    @Test
    void doitAfficherUnTableauAvecÉtageNuméroEtPrixDeChaqueChambre() {
        var port = mock(RécupérationDeToutesLesChambres.class);
        when(port.récupérerToutesLesChambres()).thenReturn(
                List.of(
                        new Chambre.Données(0, 1, 100),
                        new Chambre.Données(1, 101, 107),
                        new Chambre.Données(2, 201, 122)
                )
        );

        var chambresAffichées = new ChambresCommandes(port).demander("afficher");

        assertThat(chambresAffichées).isEqualToIgnoringWhitespace(
                """
                            | Étage | Numéro | Prix |
                            |:-----:|:------:|:----:|
                            |   0   | 1      | 100€ |
                            |   1   | 101    | 107€ |
                            |   2   | 201    | 122€ |
                        """);
    }

    @Test
    void doitRetournerUnMessageDErreurSiLActionDemandéeEstInconnue() {
        var port = mock(RécupérationDeToutesLesChambres.class);
        var prompt = new ChambresCommandes(port);

        var message = prompt.demander("action-inconnue");

        assertThat(message).isEqualTo("chambres: action inconnue");
    }
}