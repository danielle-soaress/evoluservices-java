package biblioteca.data;

import biblioteca.model.Book;
import biblioteca.model.Copy;
import biblioteca.model.CopyStatus;
import biblioteca.model.Loan;
import biblioteca.model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * O acervo da biblioteca, em memória.
 *
 * <p>Faz o papel de repositório / banco de dados: guarda os dados e não conhece
 * regras de negócio. Os empréstimos já vêm com alguns registros em aberto e os
 * exemplares com seus respectivos status.
 */
public class Library {

    private final List<Book> books = List.of(
            new Book(1, "Duna", "Frank Herbert", "Ficção Científica"),
            new Book(2, "Ensaio sobre a Cegueira", "José Saramago", "Romance"),
            new Book(3, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance"),
            new Book(4, "Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico"),
            new Book(5, "O Hobbit", "J. R. R. Tolkien", "Fantasia"),
            new Book(6, "Neuromancer", "William Gibson", "Ficção Científica"),
            new Book(7, "A Revolução dos Bichos", "George Orwell", "Fábula"),
            new Book(8, "O Cortiço", "Aluísio Azevedo", "Naturalismo"),
            new Book(9, "Vidas Secas", "Graciliano Ramos", "Romance"),
            new Book(10, "Fahrenheit 451", "Ray Bradbury", "Ficção Científica"),
            new Book(11, "O Nome do Vento", "Patrick Rothfuss", "Fantasia"),
            new Book(12, "Torto Arado", "Itamar Vieira Junior", "Romance"));

    private final List<Copy> copies = new ArrayList<>(List.of(
            new Copy(1, 1, CopyStatus.BORROWED),
            new Copy(2, 1, CopyStatus.BORROWED),
            new Copy(3, 1, CopyStatus.AVAILABLE),
            new Copy(4, 2, CopyStatus.AVAILABLE),
            new Copy(5, 2, CopyStatus.AVAILABLE),
            new Copy(6, 3, CopyStatus.AVAILABLE),
            new Copy(7, 3, CopyStatus.AVAILABLE),
            new Copy(8, 3, CopyStatus.AVAILABLE),
            new Copy(9, 3, CopyStatus.AVAILABLE),
            new Copy(10, 4, CopyStatus.AVAILABLE),
            new Copy(11, 4, CopyStatus.AVAILABLE),
            new Copy(12, 5, CopyStatus.BORROWED),
            new Copy(13, 5, CopyStatus.AVAILABLE),
            new Copy(14, 5, CopyStatus.AVAILABLE),
            new Copy(15, 6, CopyStatus.BORROWED),
            new Copy(16, 7, CopyStatus.AVAILABLE),
            new Copy(17, 7, CopyStatus.AVAILABLE),
            new Copy(18, 8, CopyStatus.AVAILABLE),
            new Copy(19, 9, CopyStatus.AVAILABLE),
            new Copy(20, 9, CopyStatus.AVAILABLE),
            new Copy(21, 10, CopyStatus.AVAILABLE),
            new Copy(22, 11, CopyStatus.AVAILABLE),
            new Copy(23, 11, CopyStatus.AVAILABLE),
            new Copy(24, 12, CopyStatus.AVAILABLE),
            new Copy(25, 12, CopyStatus.AVAILABLE),
            new Copy(26, 12, CopyStatus.AVAILABLE)));

    private final List<Member> members = List.of(
            new Member(1, "Ana Prado"),
            new Member(2, "Bruno Sales"),
            new Member(3, "Carla Nunes"),
            new Member(4, "Diego Farias"));

    private final List<Loan> loans = new ArrayList<>(List.of(
            new Loan(1, 1, LocalDate.now().minusDays(3)),
            new Loan(12, 1, LocalDate.now().minusDays(20)),
            new Loan(15, 2, LocalDate.now().minusDays(5)),
            new Loan(2, 3, LocalDate.now().minusDays(10))));

    public List<Book> books() {
        return books;
    }

    public List<Copy> copies() {
        return copies;
    }

    public List<Member> members() {
        return members;
    }

    public List<Loan> loans() {
        return loans;
    }

    public Optional<Book> findBook(int id) {
        return books.stream().filter(book -> book.id() == id).findFirst();
    }

    public Optional<Member> findMember(int id) {
        return members.stream().filter(member -> member.id() == id).findFirst();
    }

    public Optional<Copy> findCopy(int id) {
        return copies.stream().filter(copy -> copy.getId() == id).findFirst();
    }

    public Optional<Copy> findAvailableCopy(int bookId) {
        return copies.stream()
                .filter(copy -> copy.getBookId() == bookId && copy.isAvailable())
                .findFirst();
    }
}
