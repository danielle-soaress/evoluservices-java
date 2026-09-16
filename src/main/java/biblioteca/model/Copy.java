package biblioteca.model;

import java.util.Objects;

/**
 * Entidade que representa um exemplar físico de um livro no acervo da biblioteca.
 */
public class Copy {
    private int id;
    private int bookId;
    private CopyStatus status;

    public Copy(int id, int bookId, CopyStatus status) {
        this.id = id;
        this.bookId = bookId;
        this.status = Objects.requireNonNull(status, "status não pode ser nulo");
    }

    public Copy(int id, int bookId) {
        this(id, bookId, CopyStatus.AVAILABLE);
    }

    public int getId() {
        return id;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public int bookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = Objects.requireNonNull(status, "status não pode ser nulo");
    }

    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
    }

    public void markBorrowed() {
        this.status = CopyStatus.BORROWED;
    }

    public void markAvailable() {
        this.status = CopyStatus.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Copy copy)) return false;
        return id == copy.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", status=" + status +
                '}';
    }
}
