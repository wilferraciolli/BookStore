package com.pluralsight.bookstore.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The entity class for mapping Book.
 */
@Entity
@ApiModel(description = "Book resource representation")
public class Book {

    @Id
    @GeneratedValue
    @ApiModelProperty("Identifier")
    private Long id;

    @Column(length = 200)
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("Title of the book")
    private String title;

    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    @ApiModelProperty("Summary describing the book")
    private String description;

    @Column(name = "unit_cost")
    @Min(1)
    @ApiModelProperty("Unit cost")
    private Float unitCost;

    @Column(length = 50)
   // @NotNull
    //@Size(min = 1, max = 50)
    @ApiModelProperty("ISBN number")
    private String isbn;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    @Past
    @ApiModelProperty("Date in which the book has been published")
    private Date publicationDate;

    @Column(name = "nb_of_pages")
    @ApiModelProperty("Number of pages")
    private Integer nbOfPages;

    @Column(name = "image_url")
    @ApiModelProperty("URL of the image cover")
    private String imageURL;

    @Enumerated
    @ApiModelProperty( value = "Language in which the book has been written")
    private Language language;

    /**
     * Instantiates a new Book.
     */
    public Book() {
    }

    /**
     * Instantiates a new Book.
     *
     * @param isbn            the isbn
     * @param title           the title
     * @param unitCost        the unit cost
     * @param nbOfPages       the nb of pages
     * @param language        the language
     * @param publicationDate the publication date
     * @param imageURL        the image url
     * @param description     the description
     */
    public Book(String isbn, String title, Float unitCost, Integer nbOfPages, Language language, Date publicationDate, String imageURL, String description) {
        this.isbn = isbn;
        this.title = title;
        this.unitCost = unitCost;
        this.nbOfPages = nbOfPages;
        this.language = language;
        this.publicationDate = publicationDate;
        this.imageURL = imageURL;
        this.description = description;
    }

    /**
     * Update book.
     *
     * @param book the book
     */
    public void updateBook(Book book) {
        this.title = book.getTitle();
        this.unitCost = book.getUnitCost();
        this.nbOfPages = book.getNbOfPages();
        this.language = book.getLanguage();
        this.publicationDate = book.getPublicationDate();
        this.imageURL = book.imageURL;
        this.description = book.getDescription();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets unit cost.
     *
     * @return the unit cost
     */
    public Float getUnitCost() {
        return unitCost;
    }

    /**
     * Sets unit cost.
     *
     * @param unitCost the unit cost
     */
    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets publication date.
     *
     * @return the publication date
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets publication date.
     *
     * @param publicationDate the publication date
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Gets nb of pages.
     *
     * @return the nb of pages
     */
    public Integer getNbOfPages() {
        return nbOfPages;
    }

    /**
     * Sets nb of pages.
     *
     * @param nbOfPages the nb of pages
     */
    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets image url.
     *
     * @param imageURL the image url
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", nbOfPages=" + nbOfPages +
                ", imageURL='" + imageURL + '\'' +
                ", language=" + language +
                '}';
    }

}
