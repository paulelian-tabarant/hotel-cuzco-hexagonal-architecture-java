# Hotel Cuzco - Exemple d'Architecture Hexagonale

Ce projet démontre un système de gestion hôtelière implémenté en utilisant l'architecture hexagonale (ports et adaptateurs) en Java. Il illustre comment séparer la logique métier des préoccupations externes comme les interfaces utilisateur et le stockage de données.

## Aperçu

Hotel Cuzco est une application simple qui gère les chambres d'hôtel et leur tarification. Le système implémente actuellement deux fonctionnalités principales :

1. **Définir le prix du rez-de-chaussée (étage 0)** : Définir un prix de base pour les chambres du rez-de-chaussée, qui ajuste automatiquement les prix des chambres des autres étages selon les règles métier.
2. **Afficher toutes les chambres avec leurs prix** : Présenter un tableau formaté de toutes les chambres, incluant leur étage, numéro et prix calculé.

## Fonctionnalités et Comportements

L'application offre une interface en ligne de commande pour interagir avec le système. Voici comment utiliser les fonctionnalités et les comportements attendus :

### Afficher toutes les chambres

**Commande** :
```
chambres afficher
```

**Comportement attendu** :
L'application affichera un tableau formaté avec toutes les chambres, comme ceci :

```
| Étage | Numéro | Prix |
|:-----:|:------:|:----:|
|   0   | 1      | 100€ |
|   0   | 2      | 100€ |
|   1   | 101    | 107€ |
|   1   | 102    | 107€ |
|   1   | 103    | 107€ |
|   2   | 201    | 122€ |
|   2   | 202    | 122€ |
|   3   | 301    | 133€ |
```

Ce tableau montre clairement :
- L'étage de chaque chambre
- Le numéro de chaque chambre
- Le prix calculé selon les règles de tarification basées sur l'étage

### Définir le prix du rez-de-chaussée

**Commande** :
```
prix set-rdc 1000
chambres afficher
```

**Comportement attendu** :
```
| Étage | Numéro | Prix  |
|:-----:|:------:|:-----:|
|   0   | 1      | 1000€ |
|   0   | 2      | 1000€ |
|   1   | 101    | 1070€ |
|   1   | 102    | 1070€ |
|   1   | 103    | 1070€ |
|   2   | 201    | 1220€ |
|   2   | 202    | 1220€ |
|   3   | 301    | 1330€ |
```


## Extensions possibles

1. Isoler les dépendances à l'entrée/sortie standard pour pouvoir tester l'interpréteur de commandes
2. Nouvelle contrainte métier : le prix des chambres est plafonné à 200€

## Solution proposée

L'application suit le modèle d'architecture hexagonale (également connu sous le nom de ports et adaptateurs) :

```
                  ┌─────────────────────────────────────┐
                  │           Domaine Métier            │
                  │                                     │
                  │  ┌─────────────┐   ┌─────────────┐  │
                  │  │    Hôtel    │   │   Chambre   │  │
                  │  └─────────────┘   └─────────────┘  │
                  │                                     │
                  │  ┌─────────────────────────────────┐│
                  │  │        HôtelService             ││
                  │  └─────────────────────────────────┘│
                  └───────────┬─────────────────┬───────┘
                              │                 │
                              │                 │
                  ┌───────────▼─────┐   ┌───────▼───────────┐
                  │  Ports côté     │   │  Ports côté       │
                  │  utilisateur    │   │  serveur          │
                  │                 │   │                   │
                  │  - Définir prix │   │  - Stocker hôtel  │
                  │  - Obtenir      │   │                   │
                  │    chambres     │   │                   │
                  └─────┬───────────┘   └─────────┬─────────┘
                        │                         │
                        │                         │
          ┌─────────────▼───────────┐   ┌─────────▼─────────────┐
          │  Adaptateurs côté       │   │  Adaptateurs côté     │
          │  utilisateur            │   │  serveur              │
          │                         │   │                       │
          │  - PrixCommandes        │   │  - StockageHôtelInMemory│
          │  - ChambresCommandes    │   │                       │
          └─────────────────────────┘   └───────────────────────┘
```

