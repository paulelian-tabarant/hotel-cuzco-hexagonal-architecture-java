package core.domain;

import core.dto.DonnéesChambre;

public class Chambre {
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

    public void définirPrixÀ(PrixEnEuros prixEnEuros) {
        this.prix = prixEnEuros;
    }

    public DonnéesChambre données() {
        return new DonnéesChambre(étage, numéro, prix.valeur());
    }
}
