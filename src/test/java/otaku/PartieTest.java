package otaku;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PartieTest {
    private final Question qcm = new QuestionQcm("Naruto", "Quel village ?", "Konoha", "Suna", "Kiri", 1);
    private final Question vraiFaux = new QuestionVraiFaux("My Hero Academia", "Izuku est surnommé Deku.", 1);

    @Test
    void cumuleLeBaremeDeChaqueType() throws ReponseInvalideException {
        Partie partie = new Partie(List.of(qcm, vraiFaux));

        assertEquals(2, partie.repondre(1));
        assertEquals(1, partie.repondre(1));
        assertEquals(3, partie.getScore());
        assertEquals(3, partie.scoreMaximum());
        assertTrue(partie.estReussie());
    }

    @Test
    void accepteExactementLaMoitieDesPoints() throws ReponseInvalideException {
        Partie partie = new Partie(List.of(qcm, qcm));

        partie.repondre(1);
        assertFalse(partie.estReussie());
        assertEquals(0, partie.repondre(2));

        assertEquals(2, partie.getScore());
        assertTrue(partie.estReussie());
    }

    @Test
    void echoueJusteSousLaMoitieQuandLeMaximumEstImpair() throws ReponseInvalideException {
        Partie partie = new Partie(List.of(qcm, vraiFaux));

        partie.repondre(2);
        partie.repondre(1);

        assertTrue(partie.estTerminee());
        assertEquals(1, partie.getScore());
        assertFalse(partie.estReussie());
    }

    @Test
    void redemandeLaMemeQuestionApresUneSaisieHorsLimites() throws ReponseInvalideException {
        Partie partie = new Partie(List.of(vraiFaux));

        assertThrows(ReponseInvalideException.class, () -> partie.repondre(3));
        assertFalse(partie.estTerminee());
        assertEquals(0, partie.getScore());
        assertEquals(1, partie.repondre(1));
    }

    @Test
    void refuseUneReponseApresLaFin() throws ReponseInvalideException {
        Partie partie = new Partie(List.of(qcm));
        partie.repondre(1);

        assertThrows(IllegalStateException.class, () -> partie.repondre(1));
        assertEquals(2, partie.getScore());
    }
}
