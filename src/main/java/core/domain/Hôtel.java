package core.domain;

import java.util.List;

public class Hôtel {
    private final List<Chambre> chambres;

    public Hôtel(List<Chambre.DonnéesCréation> donnéesChambres, Integer prixDuRezDeChausséeEnEuros) {
        this.chambres = donnéesChambres.stream()
                .map(donnéesChambre -> créerChambreDepuis(prixDuRezDeChausséeEnEuros, donnéesChambre))
                .toList();
    }

    public Hôtel(Hôtel autre) {
        this.chambres = autre.récupérerToutesLesChambres();
    }

    public List<Chambre> récupérerToutesLesChambres() {
        return this.chambres.stream()
                .map(Chambre::new)
                .toList();
    }

    public void définirLePrixDuRezDeChausséeÀ(PrixEnEuros prixEnEuros) {
        for (Chambre chambre : chambres) {
            var prix = calculerPrixÉtageAPartirDuRezDeChaussée(prixEnEuros, chambre.étage);
            chambre.définirPrixÀ(prix);
        }
    }

    private Chambre créerChambreDepuis(Integer prixDuRezDeChausséeEnEuros, Chambre.DonnéesCréation dto) {
        var prix = calculerPrixÉtageAPartirDuRezDeChaussée(new PrixEnEuros(prixDuRezDeChausséeEnEuros), dto.étage());
        return new Chambre(dto.étage(), dto.numéro(), prix);
    }

    // remarque: pas de règle définie pour les arrondis
    private PrixEnEuros calculerPrixÉtageAPartirDuRezDeChaussée(PrixEnEuros prixDuRezDeChaussée, Integer étage) {
        return switch (étage) {
            case 0 -> prixDuRezDeChaussée;
            case 1 -> prixDuRezDeChaussée.plusPourcentage(7);
            case 2 -> prixDuRezDeChaussée.plusPourcentage(22);
            case 3 -> prixDuRezDeChaussée.plusPourcentage(33);
            default -> throw new IllegalArgumentException("Étage inconnu : " + étage);
        };
    }
}
