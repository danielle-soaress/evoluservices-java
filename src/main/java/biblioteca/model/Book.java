package biblioteca.model;

/**
 * Obra / título cadastrado no acervo da biblioteca.
 *
 * <p>Representa os metadados imutáveis da obra. Os exemplares físicos individuais
 * são gerenciados pela entidade {@link Copy}.
 */
public record Book(int id, String title, String author, String genre) {}
