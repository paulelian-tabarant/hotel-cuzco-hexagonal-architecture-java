package core.domain;

public record PrixEnEuros(Integer valeur) {
    public PrixEnEuros {
        if (valeur < 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur ou égal à 0.");
        }
    }

    public PrixEnEuros plusPourcentage(Integer pourcentage) {
        return new PrixEnEuros(valeur + (valeur * pourcentage / 100));
    }
}
