package ports.user;

import core.dto.DonnéesChambre;

import java.util.List;

public interface RécupérationDeToutesLesChambres {
    List<DonnéesChambre> récupérerToutesLesChambres();
}
