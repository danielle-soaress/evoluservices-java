package biblioteca.dto;

import biblioteca.model.Book;

/**
 * DTO que encapsula as informações de exibição de um livro no catálogo,
 * incluindo sua disponibilidade e total de exemplares físicos.
 */
public record BookCatalogDTO(Book book, int availableCopies, int totalCopies) {
}
