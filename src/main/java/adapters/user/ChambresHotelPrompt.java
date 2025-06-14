package adapters.user;

import core.dto.DonnéesChambre;
import ports.user.RécupérationDeToutesLesChambres;

public class ChambresHotelPrompt {
    private final RécupérationDeToutesLesChambres récupérationDeToutesLesChambres;

    public ChambresHotelPrompt(RécupérationDeToutesLesChambres récupérationDeToutesLesChambres) {
        this.récupérationDeToutesLesChambres = récupérationDeToutesLesChambres;
    }

    public String demander(String afficherToutesLesChambres) {
        // FIXME: protéger des commandes inconnues

        var chambres = récupérationDeToutesLesChambres.récupérerToutesLesChambres();

        var tableau = new StringBuilder();

        tableau.append("| Étage | Numéro | Prix |\n");
        tableau.append("|:-----:|:------:|:----:|\n");

        for (var chambre : chambres) {
            tableau.append(convertirEnLigneTableau(chambre));
        }

        return tableau.toString();
    }

    private static String convertirEnLigneTableau(DonnéesChambre dto) {
        return String.format("|   %d   | %d      | %d€ |\n",
                dto.étage(), dto.numéro(), dto.prix());
    }
}
