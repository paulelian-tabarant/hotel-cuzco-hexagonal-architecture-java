package adapters.user;

import org.junit.jupiter.api.Test;
import ports.user.DéfinitionDuPrixDuRezDeChaussée;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TarifsHotelPromptTest {

    @Test
    void doitAppelerLaFonctionnalitéDeDéfinitionDuPrixDuRezDeChausséeAvecLaValeurDePrix() {
        var port = mock(DéfinitionDuPrixDuRezDeChaussée.class);

        var adapter = new TarifsHotelPrompt(port);
        adapter.demander("définir prix du rdc en euros - 100");

        verify(port).définirLePrixDuRezDeChausséeÀ(100);
    }
}