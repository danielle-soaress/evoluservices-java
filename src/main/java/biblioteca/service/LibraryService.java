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
        long loanedCount = library.loans().stream()
                .filter(loan -> loan.bookId() == book.id())
                .count();
        return Math.max(0, book.copies() - (int) loanedCount);
    }

    /**
     * Quantidade de exemplares disponíveis a partir do ID do livro.
     * Devolve 0 se o livro não for encontrado.
     */
    public int availableCopies(int bookId) {
        return library.findBook(bookId)
                .map(this::availableCopies)
                .orElse(0);
    }

    // TODO (Tarefa 2): busca por título, autor ou gênero.

    // TODO (Tarefa 3): emprestar e devolver, com as regras do enunciado.

    // TODO (Tarefa 4): o que um membro tem em mãos, e o que está atrasado.
}
