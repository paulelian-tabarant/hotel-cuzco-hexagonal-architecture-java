import adapters.server.StockageHôtelInMemory;
import adapters.user.ChambresCommandes;
import adapters.user.PrixCommandes;
import core.HôtelService;
import core.domain.Hôtel;
import core.dto.DonnéesCréationChambre;
import ports.server.StockageHôtel;

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
                        new DonnéesCréationChambre(0, 1),
                        new DonnéesCréationChambre(0, 2),
                        new DonnéesCréationChambre(1, 101),
                        new DonnéesCréationChambre(1, 102),
                        new DonnéesCréationChambre(1, 103),
                        new DonnéesCréationChambre(2, 201),
                        new DonnéesCréationChambre(2, 202),
                        new DonnéesCréationChambre(3, 301)
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
