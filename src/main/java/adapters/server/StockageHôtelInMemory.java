package adapters.server;

import core.domain.Hôtel;
import ports.server.StockageHôtel;

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
