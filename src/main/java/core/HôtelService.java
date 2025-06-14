package core;

import core.domain.Chambre;
import core.domain.PrixEnEuros;
import core.dto.DonnéesChambre;
import ports.server.StockageHôtel;
import ports.user.DéfinitionDuPrixDuRezDeChaussée;
import ports.user.RécupérationDeToutesLesChambres;

import java.util.List;

public class HôtelService implements RécupérationDeToutesLesChambres, DéfinitionDuPrixDuRezDeChaussée {
    private final StockageHôtel stockageHôtel;

    public HôtelService(StockageHôtel stockageHôtel) {
        this.stockageHôtel = stockageHôtel;
    }

    @Override
    public List<DonnéesChambre> récupérerToutesLesChambres() {
        var hôtel = stockageHôtel.récupérer();

        return hôtel.récupérerToutesLesChambres()
                .stream()
                .map(Chambre::state)
                .toList();
    }

    @Override
    public void définirLePrixDuRezDeChausséeÀ(Integer prixEnEuros) {
        var hôtel = stockageHôtel.récupérer();

        hôtel.définirLePrixDuRezDeChausséeÀ(new PrixEnEuros(prixEnEuros));

        stockageHôtel.enregistrer(hôtel);
    }
}
