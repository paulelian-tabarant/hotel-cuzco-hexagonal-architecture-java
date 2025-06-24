import adapters.server_side.StockageHôtelInMemory;
import adapters.user_side.ChambresCommandes;
import adapters.user_side.PrixCommandes;
import core.HôtelService;
import core.domain.Chambre;
import core.domain.Hôtel;
import ports.server_side.StockageHôtel;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HôtelService hôtelService = créerHexagone();

        ChambresCommandes commandesDeChambre = new ChambresCommandes(hôtelService);
        PrixCommandes commandesDePrix = new PrixCommandes(hôtelService);

        afficherMessageDeBienvenue();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nEntrez votre commande (ou 'quitter' pour sortir):");

            String commande = scanner.nextLine();

            if (commande.equals("quitter")) {
                continuer = false;
                System.out.println("Au revoir !");
                continue;
            }

            var typeCommande = commande.split(" ")[0];
            var resteCommande = commande.substring(typeCommande.length()).trim();

            var message = switch (typeCommande) {
                case "chambres" -> commandesDeChambre.demander(resteCommande);
                case "prix" -> commandesDePrix.demander(resteCommande);
                default -> "Commande inconnue. Veuillez réessayer.";
            };

            System.out.println(message);
        }

        scanner.close();
    }

    private static HôtelService créerHexagone() {
        Hôtel hôtel = new Hôtel(
                List.of(
                        new Chambre.DonnéesCréation(0, 1),
                        new Chambre.DonnéesCréation(0, 2),
                        new Chambre.DonnéesCréation(1, 101),
                        new Chambre.DonnéesCréation(1, 102),
                        new Chambre.DonnéesCréation(1, 103),
                        new Chambre.DonnéesCréation(2, 201),
                        new Chambre.DonnéesCréation(2, 202),
                        new Chambre.DonnéesCréation(3, 301)
                ),
                100
        );

        StockageHôtel stockageHôtel = new StockageHôtelInMemory();
        stockageHôtel.enregistrer(hôtel);

        return new HôtelService(stockageHôtel);
    }

    private static void afficherMessageDeBienvenue() {
        System.out.println("Bienvenue à l'hôtel Cuzco");
        System.out.println("Cette application permet de gérer les chambres d'un hôtel.");
        System.out.println("Que voulez-vous faire ?");
    }

}
