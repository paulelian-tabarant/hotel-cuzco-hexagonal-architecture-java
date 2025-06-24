package adapters.user_side;

import core.domain.Chambre;
import ports.user_side.RécupérationDeToutesLesChambres;

public class ChambresCommandes {
    private final RécupérationDeToutesLesChambres récupérationDeToutesLesChambres;

    public ChambresCommandes(RécupérationDeToutesLesChambres récupérationDeToutesLesChambres) {
        this.récupérationDeToutesLesChambres = récupérationDeToutesLesChambres;
    }

    public String demander(String action) {
        if (!action.equals("afficher")) {
            return "chambres: action inconnue";
        }

        var chambres = récupérationDeToutesLesChambres.récupérerToutesLesChambres();

        var tableau = new StringBuilder();

        tableau.append("| Étage | Numéro | Prix |\n");
        tableau.append("|:-----:|:------:|:----:|\n");

        for (var chambre : chambres) {
            tableau.append(convertirEnLigneTableau(chambre));
        }

        return tableau.toString();
    }

    private static String convertirEnLigneTableau(Chambre.Données dto) {
        return String.format("|   %d   | %d      | %d€ |\n",
                dto.étage(), dto.numéro(), dto.prix());
    }
}
