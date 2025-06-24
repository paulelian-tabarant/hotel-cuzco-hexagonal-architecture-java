package adapters.user_side;

import org.junit.jupiter.api.Test;
import ports.user_side.DéfinitionDuPrixDuRezDeChaussée;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PrixCommandesTest {

    @Test
    void doitAppelerLaFonctionnalitéDeDéfinitionDuPrixDuRezDeChausséeAvecLaValeurDePrix() {
        var port = mock(DéfinitionDuPrixDuRezDeChaussée.class);

        var adapter = new PrixCommandes(port);
        adapter.demander("set-rdc 100");

        verify(port).définirLePrixDuRezDeChausséeÀ(100);
    }

    @Test
    void doitAfficherUnMessageDeConfirmationQuandLePrixEstMisÀJourAvecSuccès() {
        var port = mock(DéfinitionDuPrixDuRezDeChaussée.class);

        var adapter = new PrixCommandes(port);
        var message = adapter.demander("set-rdc 100");

        assertThat(message).isEqualTo("Prix du rez-de-chaussée défini à 100 euros.");
    }

    @Test
    void doitRetournerUnMessageDErreurSiLActionDemandéeEstInconnue() {
        var port = mock(DéfinitionDuPrixDuRezDeChaussée.class);
        var prompt = new PrixCommandes(port);

        var message = prompt.demander("action-inconnue");

        assertThat(message).isEqualTo("prix: action inconnue");
    }
}