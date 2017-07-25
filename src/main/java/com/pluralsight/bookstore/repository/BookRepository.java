package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * The type Book repository. This is the repository to persist mapped Book entity to the database. The class is
 * annotated with transactional to allow JTA to manage the transaction.
 */
@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager em;

    /**
     * Find by id book.
     *
     * @param id the id
     * @return the book
     */
    public Book findById(@NotNull Long id){
        return em.find(Book.class, id);
    }

    /**
     * Find all list of all books.
     *
     * @return the list
     */
    public List<Book> findAll(){
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    /**
     * Count all books on the database.
     *
     * @return the long
     */
    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    /**
     * Create book. Annotated with @transactional-Required because it will write to the database.
     *
     * @param book the book
     * @return the book
     */
    @Transactional(REQUIRED)
    public Book create(@NotNull Book book){
        em.persist(book);
        return book;
    }

    /**
     * Update book. Annotated with @transactional-Required because it will write to the databas
     *
     * @param book the book
     * @return the book
     */
    @Transactional(REQUIRED)
    public Book update(@NotNull Book book){
        em.merge(book);
        return book;
    }

    /**
     * Delete by id. Annotated with @transactional-Required because it will write to the databas
     *
     * @param id the id
     */
    @Transactional(REQUIRED)
    public void deleteById(@NotNull Long id){
        em.remove(em.getReference(Book.class, id));
    }
}
