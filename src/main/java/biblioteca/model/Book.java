package biblioteca.model;

/**
 * Um título do acervo.
 *
 * @param copies é a quantidade de exemplares físicos que a biblioteca possui.
 */
public record Book(int id, String title, String author, String genre, int copies) {
}
