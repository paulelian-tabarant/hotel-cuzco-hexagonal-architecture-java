package adapters.user_side;

import ports.user_side.DéfinitionDuPrixDuRezDeChaussée;

public class PrixCommandes {
    private final DéfinitionDuPrixDuRezDeChaussée définitionPrixDuRezDeChaussée;

    public PrixCommandes(DéfinitionDuPrixDuRezDeChaussée définitionDuPrixDuRezDeChaussée) {
        this.définitionPrixDuRezDeChaussée = définitionDuPrixDuRezDeChaussée;
    }

    public String demander(String commande) {
        var action = commande.split(" ")[0];

        if (!action.equals("set-rdc")) {
            return "prix: action inconnue";
        }

        Integer prixEnEuros = Integer.parseInt(commande.split(" ")[1]);

        définitionPrixDuRezDeChaussée.définirLePrixDuRezDeChausséeÀ(prixEnEuros);

        return "Prix du rez-de-chaussée défini à " + commande.split(" ")[1] + " euros.";

        // FIXME: le cas d'une commande inconnue n'est pas géré
    }
}
