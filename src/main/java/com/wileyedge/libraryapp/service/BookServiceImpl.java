package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.config.AppConfig;
import com.wileyedge.libraryapp.dao.BookRepo;
import com.wileyedge.libraryapp.dto.*;
import com.wileyedge.libraryapp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    @Autowired
    private final BookRepo bookRepo;

    public BookServiceImpl(RestTemplate restTemplate, BookRepo bookRepo, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.bookRepo = bookRepo;
        this.appConfig = appConfig;
    }

    @Override
    public Book addNewBook(Book book) {
        try {
            if (!Objects.equals(book.getTitle(), "")){
                bookRepo.save(book);
            } else {
                book.setTitle("Book NOT added. Title empty.");
            }
        } catch (Exception ex) {
            book.setTitle("Book NOT added. Error: " + ex.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        if (books.isEmpty()) {
            // Perform a fallback action, such as fetching books from an external API
            fetchAndSaveBooks();
            books = bookRepo.findAll(); // Retrieve the books again after saving
        }
        return books;
    }

    @Override
    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    @Override
    public void fetchAndSaveBooks() {
        fetchFromUrl(appConfig.getApiUrl());
    }

    @Override
    public List<Book> searchFromApi(String text) {
        fetchFromUrl(appConfig.getApiUrl(text));
        return findByText(text);
    }

    private void fetchFromUrl(String apiUrl) {
        try {
            // Make API request and retrieve data
            ResponseEntity<ApiResponse> response = restTemplate.getForEntity(apiUrl, ApiResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                ApiResponse apiResponse = response.getBody();

                if (apiResponse != null && apiResponse.getItems() != null) {
                    List<ItemDto> bookDtos = apiResponse.getItems();

                    List<Book> books = bookDtos.stream()
                            .map(bookDto -> convertToEntity(bookDto.getVolumeInfo().getImageLinks(), bookDto.getVolumeInfo().getIndustryIdentifiers(), bookDto.getSearchInfo(), bookDto.getVolumeInfo()))
                            .collect(Collectors.toList());

                    // Save books to the H2 database
                    bookRepo.saveAll(books);
                } else {
                    System.out.println("Response body is null or does not contain books.");
                }
            } else {
                System.out.println("API request failed with status code: " + response.getStatusCodeValue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Book getBookById(Integer bid) {
        return bookRepo.findById(bid).orElse(null);
    }

    @Override
    public Book updateBook(Book updatedBook) {
        Book getBook = bookRepo.findById(updatedBook.getBid()).orElse(null);
        if (getBook==null) {
            updatedBook.setTitle("Book NOT found. Could not update");
        } else {
            bookRepo.save(updatedBook);
        }
        return updatedBook;
    }

    @Override
    public Book deleteBookById(Integer bid) {
        Book book = bookRepo.findById(bid).orElse(null);
        if (book!=null) {
            bookRepo.deleteById(bid);
        } else {
            Book returnedBook = new Book();
            returnedBook.setTitle("Book NOT Found. Could not delete");
            return returnedBook;
        }
        return book;
    }

    @Override
    public List<Book> findByText(String text) {
        List<Book> titles = bookRepo.findByTitleContainingIgnoreCase(text);
        List<Book> authors = bookRepo.findByAuthorsContainingIgnoreCase(text);

        Set<Book> results = Stream.of(titles, authors)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        return results.stream().sorted(Comparator.comparingInt(Book::getBid))
                .collect(Collectors.toList());
    }

    public static Book convertToEntity(ImageLinksDto imageLinksDto, List<IndustryIdentifierDto> industryIdentifierDtos, SearchInfoDto searchInfoDto, VolumeInfoDto volumeInfoDto) {
        Book book = new Book();
        if (volumeInfoDto != null) {
            book.setTitle(volumeInfoDto.getTitle());
            book.setSubtitle(volumeInfoDto.getSubtitle());
            if (volumeInfoDto.getAuthors() != null) {
                book.setAuthors(volumeInfoDto.getAuthors().toString());
            }
            book.setPublishedDate(volumeInfoDto.getPublishedDate());
            book.setDescription(volumeInfoDto.getDescription());
            book.setPageCount(volumeInfoDto.getPageCount());
            book.setPrintType(volumeInfoDto.getPrintType());

            if (volumeInfoDto.getCategories() != null) {
                book.setCategories(volumeInfoDto.getCategories().toString());
            }

            book.setLanguage(volumeInfoDto.getLanguage());
        }
        if (searchInfoDto != null) {
            book.setSearchInfo(searchInfoDto.getTextSnippet());
        }
        if (industryIdentifierDtos != null && imageLinksDto != null) {
            book.setIndustryIdentifiers(volumeInfoDto.getIndustryIdentifiers().toString());
            book.setImageLinks(convertToImageLinks(imageLinksDto));
        }
        return book;
    }

    private static ImageLinksDto convertToImageLinks(ImageLinksDto imageLinksDto) {
        if (imageLinksDto == null) {
            return null;
        }
        ImageLinksDto imageLinks = new ImageLinksDto();
        imageLinks.setThumbnail(imageLinksDto.getThumbnail());
        imageLinks.setSmallThumbnail(imageLinksDto.getSmallThumbnail());

        return imageLinks;
    }
}