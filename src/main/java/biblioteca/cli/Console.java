package biblioteca.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * Tudo que o programa escreve na tela passa por aqui.
 *
 * <p>Ao implementar os comandos novos, chame estes métodos em vez de usar
 * {@code System.out.println} direto. Dois motivos: o {@code buscar} sai com a
 * mesma cara do {@code listar}, e mudar o formato da tabela num lugar só muda em
 * todos os comandos de uma vez.
 */
public final class Console {

    private Console() {
    }

    public static void title(String text) {
        System.out.println();
        System.out.println(text.toUpperCase());
        System.out.println("-".repeat(text.length()));
    }

    public static void info(String text) {
        System.out.println(text);
    }

    public static void error(String text) {
        System.out.println("erro: " + text);
    }

    /**
     * Imprime uma tabela alinhando as colunas pela maior célula de cada uma.
     */
    public static void table(List<String> headers, List<List<String>> rows) {
        if (rows.isEmpty()) {
            info("(nada para mostrar)");
            return;
        }

        List<Integer> widths = new ArrayList<>();
        for (int column = 0; column < headers.size(); column++) {
            int width = headers.get(column).length();
            for (List<String> row : rows) {
                width = Math.max(width, row.get(column).length());
            }
            widths.add(width);
        }

        System.out.println(pad(headers, widths));
        List<String> separators = new ArrayList<>();
        for (int width : widths) {
            separators.add("-".repeat(width));
        }
        System.out.println(String.join("  ", separators));

        for (List<String> row : rows) {
            System.out.println(pad(row, widths));
        }
    }

    private static String pad(List<String> cells, List<Integer> widths) {
        List<String> padded = new ArrayList<>();
        for (int index = 0; index < cells.size(); index++) {
            padded.add(String.format("%-" + widths.get(index) + "s", cells.get(index)));
        }
        return String.join("  ", padded).stripTrailing();
    }
}
