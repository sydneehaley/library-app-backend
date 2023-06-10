package com.wileyedge.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wileyedge.libraryapp.dto.ImageLinksDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bid"
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid", nullable = false)
    private Integer bid;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "authors", unique = true)
    private String authors;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "industry_identifiers")
    private String industryIdentifiers;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "print_type")
    private String printType;

    @Column(name = "category")
    private String categories;

    @Embedded
    private ImageLinksDto imageLinks;

    @Column(name = "language")
    private String language;

    @Column(name = "search_info", length = 500)
    private String searchInfo;

    @Column(name = "copy_count")
    private int copyCount;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<UserBook> users;

    public Book() {}

    public Book(Set<UserBook> users) {
        this.users = users;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(String industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public ImageLinksDto getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinksDto imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(String searchInfo) {
        this.searchInfo = searchInfo;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    public Set<UserBook> getUserBooks() {
        return users;
    }

    public void setUserBooks(Set<UserBook> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors='" + authors + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", industryIdentifiers='" + industryIdentifiers + '\'' +
                ", pageCount=" + pageCount +
                ", printType='" + printType + '\'' +
                ", categories='" + categories + '\'' +
                ", imageLinks=" + imageLinks +
                ", language='" + language + '\'' +
                ", searchInfo='" + searchInfo + '\'' +
                ", copyCount=" + copyCount +
                ", users=" + users +
                '}';
    }
}
