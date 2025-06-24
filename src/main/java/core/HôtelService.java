package core;

import core.domain.Chambre;
import core.domain.PrixEnEuros;
import ports.server_side.StockageHôtel;
import ports.user_side.DéfinitionDuPrixDuRezDeChaussée;
import ports.user_side.RécupérationDeToutesLesChambres;

import java.util.List;

public class HôtelService implements RécupérationDeToutesLesChambres, DéfinitionDuPrixDuRezDeChaussée {
    private final StockageHôtel stockageHôtel;

    public HôtelService(StockageHôtel stockageHôtel) {
        this.stockageHôtel = stockageHôtel;
    }

    @Override
    public List<Chambre.Données> récupérerToutesLesChambres() {
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
