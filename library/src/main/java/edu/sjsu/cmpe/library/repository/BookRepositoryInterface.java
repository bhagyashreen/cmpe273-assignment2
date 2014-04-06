package edu.sjsu.cmpe.library.repository;

import java.util.List;

import javax.jms.JMSException;

import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;
import edu.sjsu.cmpe.library.domain.Book;

/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);

    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     */
    Book getBookByISBN(Long isbn);

    /**
     * Retrieve all books
     * 
     * @return a list of books
     */
    List<Book> getAllBooks();

    /**
     * Delete an existing book
     * 
     * @param isbn
     *            an ISBN of the book to be deleted
     */
    void delete(Long isbn);
    void producer(Long isbnValue,Book book) throws JMSException;
    void putConfiguration(LibraryServiceConfiguration configuration);
    void updateLibraryAfterResponse(Book newBook);
}