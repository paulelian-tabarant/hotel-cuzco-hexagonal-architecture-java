import adapters.server.StockageHôtelInMemory;
import adapters.user.ChambresHotelPrompt;
import adapters.user.TarifsHotelPrompt;
import core.HôtelService;
import core.domain.Hôtel;
import core.dto.DonnéesCréationChambre;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class HotelCuzcoAcceptanceTest {

    @Test
    void doitAfficherUnTableauAvecÉtageNuméroEtPrixDeChaqueChambre() {
        // Le server-side adapter répond aux besoins du cœur du métier (ici, stockage en mémoire)
        var serverSideAdapter = new StockageHôtelInMemory();
        var chambres = List.of(
                new DonnéesCréationChambre(0, 1),
                new DonnéesCréationChambre(0, 2),
                new DonnéesCréationChambre(1, 101),
                new DonnéesCréationChambre(1, 102),
                new DonnéesCréationChambre(1, 103),
                new DonnéesCréationChambre(2, 201),
                new DonnéesCréationChambre(2, 202),
                new DonnéesCréationChambre(3, 301)
        );
        serverSideAdapter.enregistrer(new Hôtel(chambres, 1000));

        // Les server-side adapters sont utilisées par le coeur du métier pour dialoguer avec l'infrastructure
        var hexagone = new HôtelService(serverSideAdapter);

        // Les adapters user-side exposent les fonctionnalités du coeur du métier à l'utilisateur
        var tarifsHotelUserSideAdapter = new TarifsHotelPrompt(hexagone);
        tarifsHotelUserSideAdapter.demander("définir prix du rdc en euros - 100");
        // tarifsHotelUserSideAdapter.demander("définir prix du 2ème étage en euros - 59");
        // tarifsHotelUserSideAdapter.demander("définir prix du 1er étage en livres sterling - 134");
        // tarifsHotelUserSideAdapter.demander("définir prix de la taxe de séjour en euros - 1");

        var chambresHotelUserSideAdapter = new ChambresHotelPrompt(hexagone);
        var chambresAffichées = chambresHotelUserSideAdapter.demander("afficher toutes les chambres");
        // chambresHotelUserSideAdapter.demander("afficher toutes les chambres sous la forme 'numéro | étage'");
        // chambresHotelUserSideAdapter.demander("supprimer la chambre 1");

        assertThat(chambresAffichées).isEqualToIgnoringWhitespace(
                """
                        | Étage | Numéro | Prix |
                        |:-----:|:------:|:----:|
                        |   0   | 1      | 100€ |
                        |   0   | 2      | 100€ |
                        |   1   | 101    | 107€ |
                        |   1   | 102    | 107€ |
                        |   1   | 103    | 107€ |
                        |   2   | 201    | 122€ |
                        |   2   | 202    | 122€ |
                        |   3   | 301    | 133€ |
                        """);
    }
}
