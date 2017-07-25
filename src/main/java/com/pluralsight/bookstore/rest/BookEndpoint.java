package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Book REST Service endpoints.
 */
@Path("/books")
public class BookEndpoint {

    @Inject
    private BookRepository bookRepository;

    /**
     * Create book response.
     *
     * @param book    the book
     * @param uriInfo the uri info
     * @return the response
     */
    @POST
    @Consumes(APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }


    @PUT
    @Path("/{id : \\d+}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateBook(@PathParam("id") @Min(1) Long id, Book book) {
        Book foundBook = bookRepository.findById(id);

        if (foundBook == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //update the book
        foundBook.updateBook(book);
        Book updatedBook = bookRepository.update(foundBook);

        return Response.ok(updatedBook).build();
    }


    /**
     * Gets book.
     *
     * @param id the id
     * @return the book
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    /**
     * Delete book response.
     *
     * @param id the id
     * @return the response
     */
    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.deleteById(id);
        return Response.noContent().build();
    }

    /**
     * Gets books.
     *
     * @return the books
     */
    @GET
    @Produces(APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();

        //return a JAX response
        if (books.size() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(books).build();
    }

    /**
     * Count books response.
     *
     * @return the response
     */
    @GET
    @Path("/count")
    @Produces(TEXT_PLAIN)
    public Response countBooks() {
        Long nbOfBooks = bookRepository.countAll();

        if (nbOfBooks == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(nbOfBooks).build();
    }
}
