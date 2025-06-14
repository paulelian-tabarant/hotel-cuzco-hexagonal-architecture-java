import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GestionnaireDeChambresAcceptanceTest {

    @Test
    void doitAfficherUnTableauAvecEtageNumeroEtPrixDeChaqueChambre() {
        var gestionnaireDeChambres = new GestionnaireDeChambres();
        var plainTextAdapter = new GestionnaireDeChambresPlainTextAdapter(gestionnaireDeChambres);

        plainTextAdapter.demander("fixer le prix des chambres au rez de chaussée à 100€");

        assertThat(plainTextAdapter.demander("afficher toutes les chambres")).isEqualTo(
    """
            | Étage | numéro  | Prix
            |:-----:|:-------:|-------
            |   0   | 1       | 100€
            |   0   | 2       | 100€
            |   1   | 101     | 107€
            |   1   | 102     | 107€
            |   1   | 103     | 107€
            |   2   | 201     | 122€
            |   2   | 202     | 122€
            |   3   | 301     | 133€
            """);
    }
}
