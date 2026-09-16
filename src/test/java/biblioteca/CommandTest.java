package biblioteca;

import biblioteca.cli.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Teste de exemplo — serve de modelo caso você escreva os seus (é bônus).
 */
class CommandTest {

    @Test
    @DisplayName("quebra a linha em nome e argumentos")
    void quebraALinha() {
        Command command = Command.parse("emprestar 1 3").orElseThrow();

        assertEquals("emprestar", command.name());
        assertEquals(List.of("1", "3"), command.arguments());
    }

    @Test
    @DisplayName("ignora espaços sobrando e caixa alta")
    void ignoraEspacos() {
        Command command = Command.parse("   LISTAR   ").orElseThrow();

        assertEquals("listar", command.name());
        assertEquals(List.of(), command.arguments());
    }

    @Test
    @DisplayName("devolve vazio quando a linha está em branco")
    void linhaEmBranco() {
        assertTrue(Command.parse("    ").isEmpty());
    }
}
