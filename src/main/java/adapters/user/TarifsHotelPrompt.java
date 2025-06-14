package adapters.user;

import ports.user.DéfinitionDuPrixDuRezDeChaussée;

public class TarifsHotelPrompt {
    private final DéfinitionDuPrixDuRezDeChaussée définitionPrixDuRezDeChaussée;

    public TarifsHotelPrompt(DéfinitionDuPrixDuRezDeChaussée définitionDuPrixDuRezDeChaussée) {
        this.définitionPrixDuRezDeChaussée = définitionDuPrixDuRezDeChaussée;
    }

    public void demander(String requête) {
        String[] commandeEtValeur = requête.split("-");
        String prixEnEurosString = commandeEtValeur[1].trim();

        définitionPrixDuRezDeChaussée.définirLePrixDuRezDeChausséeÀ(Integer.parseInt(prixEnEurosString));

        // FIXME: le cas d'une commande inconnue n'est pas géré
    }
}
