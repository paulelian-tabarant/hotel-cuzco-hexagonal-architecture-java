package ports.server;

import core.domain.Hôtel;

public interface StockageHôtel {
    void enregistrer(Hôtel hotel);

    Hôtel récupérer();
}
