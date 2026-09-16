package biblioteca;

import biblioteca.data.Library;
import biblioteca.model.Book;
import biblioteca.service.LibraryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryServiceTest {

    @Test
    @DisplayName("calcula exemplares disponíveis quando há empréstimos em aberto")
    void calculaDisponibilidadeComEmprestimos() {
        LibraryService service = new LibraryService(new Library());
        Book duna = service.catalog().stream()
                .filter(b -> b.id() == 1)
                .findFirst()
                .orElseThrow();

        // Duna tem 3 cópias no total e 2 empréstimos em aberto nos dados iniciais
        assertEquals(1, service.availableCopies(duna));
        assertEquals(1, service.availableCopies(1));
    }

    @Test
    @DisplayName("calcula exemplares disponíveis quando não há empréstimos para o livro")
    void calculaDisponibilidadeSemEmprestimos() {
        LibraryService service = new LibraryService(new Library());
        Book ensaio = service.catalog().stream()
                .filter(b -> b.id() == 2)
                .findFirst()
                .orElseThrow();

        // Ensaio sobre a Cegueira tem 2 cópias e nenhum empréstimo
        assertEquals(2, service.availableCopies(ensaio));
        assertEquals(2, service.availableCopies(2));
    }

    @Test
    @DisplayName("calcula exemplares disponíveis quando todos os exemplares estão emprestados")
    void calculaDisponibilidadeTodosEmprestados() {
        LibraryService service = new LibraryService(new Library());
        Book neuromancer = service.catalog().stream()
                .filter(b -> b.id() == 6)
                .findFirst()
                .orElseThrow();

        // Neuromancer tem 1 cópia e 1 empréstimo em aberto
        assertEquals(0, service.availableCopies(neuromancer));
        assertEquals(0, service.availableCopies(6));
    }

    @Test
    @DisplayName("retorna 0 para livro inexistente ao consultar por id")
    void calculaDisponibilidadeLivroInexistente() {
        LibraryService service = new LibraryService(new Library());
        assertEquals(0, service.availableCopies(9999));
    }
}
