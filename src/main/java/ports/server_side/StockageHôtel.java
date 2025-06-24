package ports.server_side;

import core.domain.Hôtel;

public interface StockageHôtel {
    void enregistrer(Hôtel hotel);

    Hôtel récupérer();
}
