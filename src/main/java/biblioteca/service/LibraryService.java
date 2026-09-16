package biblioteca.service;

import biblioteca.data.Library;
import biblioteca.dto.BookCatalogDTO;
import biblioteca.model.Book;
import biblioteca.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Onde moram as regras e orquestrações da biblioteca.
 *
 * <p>Nada aqui dentro imprime na tela nem lê do teclado.
 * Esta classe recebe comandos/perguntas e devolve dados ou DTOs.
 * Quem conversa com o usuário é a camada de {@code cli}.
 */
public class LibraryService {

    private final Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    /**
     * [Tarefa 1] 
     * Retorna o catálogo completo enriquecido com os exemplares livres agora
     * e o total de cópias físicas cadastradas para cada obra.
     */
    public List<BookCatalogDTO> getCatalog() {
        List<BookCatalogDTO> items = new ArrayList<>();
        for (Book book : library.findAllBooks()) {
            int available = getAvailableCopies(book.id());
            int total = getTotalCopies(book.id());
            items.add(new BookCatalogDTO(book, available, total));
        }
        return items;
    }

    /**
     * [Tarefa 1] 
     * Quantidade de exemplares de um livro disponíveis para empréstimo no momento.
     * Consulta os exemplares físicos vinculados ao livro com status {@code AVAILABLE}.
     */
    public int getAvailableCopies(int bookId) {
        return (int) library.findCopiesByBookId(bookId).stream()
                .filter(copy -> copy.isAvailable())
                .count();
    }

    /**
     * [Tarefa 1] 
     * Quantidade de exemplares de um livro disponíveis para empréstimo no momento.
     */
    public int getAvailableCopies(Book book) {
        return getAvailableCopies(book.id());
    }

    /**
     * [Tarefa 1] 
     * Quantidade total de exemplares físicos que a biblioteca possui da obra.
     */
    public int getTotalCopies(int bookId) {
        return library.findCopiesByBookId(bookId).size();
    }

    public List<Book> catalog() {
        return library.findAllBooks();
    }

    public int availableCopies(Book book) {
        return getAvailableCopies(book);
    }

    public int availableCopies(int bookId) {
        return getAvailableCopies(bookId);
    }

    public int totalCopies(int bookId) {
        return getTotalCopies(bookId);
    }

    /**
     * Busca uma obra pelo seu identificador único.
     */
    public Optional<Book> findBookById(int id) {
        return library.findBookById(id);
    }

    /**
     * Busca um membro pelo seu identificador único.
     */
    public Optional<Member> findMemberById(int id) {
        return library.findMemberById(id);
    }

    // TODO (Tarefa 2): busca por título, autor ou gênero.

    // TODO (Tarefa 3): emprestar e devolver, com as regras do enunciado.

    // TODO (Tarefa 4): o que um membro tem em mãos, e o que está atrasado.
}
