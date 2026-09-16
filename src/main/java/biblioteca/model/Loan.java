package biblioteca.model;

import java.time.LocalDate;

/**
 * Registro imutável de uma transação de empréstimo.
 *
 * <p>Vincula o exemplar físico emprestado (copyId), o membro que o pegou
 * (memberId) e a data em que ocorreu o empréstimo (borrowedAt). A obra (Book)
 * à qual o exemplar pertence pode ser obtida a partir do próprio {@link Copy}.
 */
public record Loan(int copyId, int memberId, LocalDate borrowedAt) {
}
