package core.domain;

public class Chambre {
    public record Données(Integer étage, Integer numéro, Integer prix) {
    }

    public record DonnéesCréation(Integer étage, Integer numéro) {
    }

    public final Integer étage;
    private final Integer numéro;
    private PrixEnEuros prix;

    public Chambre(Integer étage, Integer numéro, PrixEnEuros prix) {
        if (étage < 0) {
            throw new IllegalArgumentException("L'étage doit être supérieur ou égal à 0.");
        }

        if (numéro < 0) {
            throw new IllegalArgumentException("Le numéro de chambre doit être supérieur ou égal à 0.");
        }

        this.étage = étage;
        this.numéro = numéro;
        this.prix = prix;
    }

    public Chambre(Chambre autre) {
        this.étage = autre.étage;
        this.numéro = autre.numéro;
        this.prix = autre.prix;
    }

    // note: on pourrait créer un value object pour le prix

    public void définirPrixÀ(PrixEnEuros prixEnEuros) {
        this.prix = prixEnEuros;
    }

    public Données state() {
        return new Données(étage, numéro, prix.valeur());
    }
}
