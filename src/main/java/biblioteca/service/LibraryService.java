package biblioteca.service;

import biblioteca.data.Library;
import biblioteca.model.Book;

import java.util.List;

/**
 * Onde moram as regras da biblioteca.
 *
 * <p>Nada aqui dentro imprime na tela nem lê do teclado.
 * Esta classe recebe perguntas e devolve dados. Quem conversa com o usuário é a
 * camada de {@code cli}.
 */
public class LibraryService {

    private final Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    /**
     * O acervo inteiro, na ordem em que está cadastrado.
     */
    public List<Book> catalog() {
        return library.books();
    }

    /**
     * Quantidade de exemplares de um livro disponíveis para empréstimo no momento.
     */
    public int availableCopies(Book book) {
        return availableCopies(book.id());
    }

    /**
     * Quantidade de exemplares disponíveis a partir do ID do livro.
     */
    public int availableCopies(int bookId) {
        return (int) library.copies().stream()
                .filter(copy -> copy.getBookId() == bookId && copy.isAvailable())
                .count();
    }

    /**
     * Quantidade total de exemplares físicos que a biblioteca possui da obra.
     */
    public int totalCopies(int bookId) {
        return (int) library.copies().stream()
                .filter(copy -> copy.getBookId() == bookId)
                .count();
    }

    // TODO (Tarefa 2): busca por título, autor ou gênero.

    // TODO (Tarefa 3): emprestar e devolver, com as regras do enunciado.

    // TODO (Tarefa 4): o que um membro tem em mãos, e o que está atrasado.
}
