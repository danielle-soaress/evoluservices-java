package biblioteca;

import biblioteca.cli.Command;
import biblioteca.cli.Console;
import biblioteca.data.Library;
import biblioteca.model.Book;
import biblioteca.dto.BookCatalogDTO;
import biblioteca.service.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        LibraryService service = new LibraryService(new Library());
        Scanner scanner = new Scanner(System.in);

        Console.title("Biblioteca");
        Console.info("Digite 'ajuda' para ver os comandos, 'sair' para encerrar.");

        while (true) {
            System.out.print("\n> ");
            if (!scanner.hasNextLine()) {
                return;
            }

            Optional<Command> parsed = Command.parse(scanner.nextLine());
            if (parsed.isEmpty()) {
                continue;
            }
            Command command = parsed.get();

            switch (command.name()) {
                case "ajuda" -> showHelp();
                case "listar" -> showCatalog(service);
                case "sair" -> {
                    Console.info("Até mais.");
                    return;
                }
                // TODO (Tarefas 2 a 4): implementar os comandos novos aqui.
                case "buscar", "emprestar", "devolver", "membro" ->
                        Console.error("comando '" + command.name() + "' ainda não implementado");
                default ->
                        Console.error("não conheço o comando '" + command.name() + "'. Tente 'ajuda'.");
            }
        }
    }

    private static void showHelp() {
        Console.title("Comandos");
        Console.table(
                List.of("comando", "o que faz"),
                List.of(
                        List.of("listar", "mostra o acervo"),
                        List.of("buscar <termo>", "procura por título, autor ou gênero"),
                        List.of("emprestar <livro> <membro>", "empresta um exemplar a um membro"),
                        List.of("devolver <livro> <membro>", "devolve um exemplar"),
                        List.of("membro <id>", "mostra os empréstimos de um membro"),
                        List.of("ajuda", "mostra esta lista"),
                        List.of("sair", "encerra o programa")));
    }

    /**
     * Comando de referência: se ficar em dúvida sobre estilo, copie o que está
     * aqui.
     */
    private static void showCatalog(LibraryService service) {
        Console.title("Acervo");

        List<List<String>> rows = new ArrayList<>();
        for (BookCatalogDTO item : service.getCatalog()) {
            rows.add(List.of(
                    String.valueOf(item.book().id()),
                    item.book().title(),
                    item.book().author(),
                    item.book().genre(),
                    String.valueOf(item.availableCopies())));
        }

        Console.table(List.of("id", "título", "autor", "gênero", "disponíveis"), rows);
    }
}
