package ports.user_side;

import core.domain.Chambre;

import java.util.List;

public interface RécupérationDeToutesLesChambres {
    List<Chambre.Données> récupérerToutesLesChambres();
}
