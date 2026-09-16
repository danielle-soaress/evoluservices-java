package biblioteca.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Uma linha digitada no terminal, já quebrada em nome e argumentos.
 *
 * <p>{@code emprestar 1 3} vira {@code Command("emprestar", ["1", "3"])}.
 */
public record Command(String name, List<String> arguments) {

    public Optional<String> argument(int index) {
        return index < arguments.size() ? Optional.of(arguments.get(index)) : Optional.empty();
    }

    /** Devolve vazio quando a linha está em branco. */
    public static Optional<Command> parse(String line) {
        List<String> parts = Arrays.stream(line.trim().split("\\s+"))
                .filter(part -> !part.isEmpty())
                .toList();

        if (parts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Command(
                parts.get(0).toLowerCase(),
                parts.subList(1, parts.size())));
    }
}
