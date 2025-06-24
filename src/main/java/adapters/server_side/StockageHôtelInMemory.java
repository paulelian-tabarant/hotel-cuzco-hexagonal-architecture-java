package adapters.server_side;

import core.domain.Hôtel;
import ports.server_side.StockageHôtel;

public class StockageHôtelInMemory implements StockageHôtel {
    private Hôtel hôtel;

    @Override
    public void enregistrer(Hôtel hôtel) {
        this.hôtel = hôtel;
    }

    @Override
    // note: absence d'hôtel non supportée dans cet exemple
    public Hôtel récupérer() {
        return new Hôtel(this.hôtel);
    }
}
