package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.*;


/**
 * The type Book repository test.
 */
@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepository;

    private static Long bookId;

    /**
     * Create deployment java archive.
     *
     * @return the java archive
     */
    @Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(BookRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

    /**
     * Dummy test to test the whole class.
     * Ideally this would be broken down into many tests eg create/find/findAll and delete.
     *
     * @throws Exception the exception
     */
    @Ignore
    //@Test
    public void create() throws Exception {
        //test counting books
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        //Create book
        Book book = new Book("isbn", "title", 12F, 123, Language.ENGLISH, new Date(), "imageURL", "description");
        Book createdBook = bookRepository.create(book);
        Long bookId = createdBook.getId();

        //check created book
        assertNotNull(bookId);

        //find created book
        Book bookFound = bookRepository.findById(bookId);

        //check thr found book
        assertEquals("title", bookFound.getTitle());

        //test counting books
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        //Delete the book
        bookRepository.deleteById(bookId);

        //test counting books
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    @InSequence(1)
    public void shouldBeDeployed() {
        assertNotNull(bookRepository);
    }

    @Test
    @InSequence(2)
    public void shouldGetNoBook() {
        // Count all
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        // Find all
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    @InSequence(3)
    public void shouldCreateABook() {
        // Creates a book
        Book book = new Book("isbn", "title", 12F, 123, Language.ENGLISH, new Date(), "imageURL", "description");
        book = bookRepository.create(book);
        // Checks the created book
        assertNotNull(book);
        assertNotNull(book.getId());
        bookId = book.getId();
    }

    @Test
    @InSequence(4)
    public void shouldFindTheCreatedBook() {
        // Finds the book
        Book bookFound = bookRepository.findById(bookId);
        // Checks the found book
        assertNotNull(bookFound.getId());
        assertEquals("title", bookFound.getTitle());
    }

    @Test
    @InSequence(5)
    public void shouldGetOneBook() {
        // Count all
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        // Find all
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    @InSequence(6)
    public void shouldDeleteTheCreatedBook() {
        // Deletes the book
        bookRepository.deleteById(bookId);
        // Checks the deleted book
        Book bookDeleted = bookRepository.findById(bookId);
        assertNull(bookDeleted);
    }

    @Test
    @InSequence(7)
    public void shouldGetNoMoreBook() {
        // Count all
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        // Find all
        assertEquals(0, bookRepository.findAll().size());
    }

}
