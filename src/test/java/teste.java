import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class teste {

    public boolean ehValidoFEN(String fen) {

        if (fen == null || fen.isEmpty()) {
            return false;
        }

        if (!fen.matches("[rnbqkpRNBQKP10-8/ wbKQkq-]+")) {
            return false;
        }

            if (fen.length() < 15 || fen.length() > 100) {
                return false;
            }

        return true;
    }

    @Test
    public void testeFenCerto() {
        teste teste = new teste();
        String validFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        assertTrue(teste.ehValidoFEN(validFEN));

    }

    @Test
    public void testeFenErrado() {
        teste teste = new teste();
        String invalidFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ;";
        assertFalse(teste.ehValidoFEN(invalidFEN));
    }
}

