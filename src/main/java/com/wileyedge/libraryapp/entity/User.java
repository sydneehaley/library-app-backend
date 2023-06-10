package com.wileyedge.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueFNameAndLName", columnNames = {"fName", "lName"})
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "uid"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Integer uid;

    @Column(name = "fName", nullable = false)
    private String fName;

    @Column(name = "lName", nullable = false)
    private String lName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "max_borrow_count")
    private int maxBorrowCount;

    @Column(name = "ban_status")
    private boolean banStatus;

    @Column(name = "ban_date")
    private LocalDate banDate;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<UserBook> books;

    public User() {}

    public User(Set<UserBook> userBooks) {
        this.books = userBooks;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxBorrowCount() {
        return maxBorrowCount;
    }

    public void setMaxBorrowCount(int maxBorrowCount) {
        this.maxBorrowCount = maxBorrowCount;
    }

    public boolean isBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }

    public LocalDate getBanDate() {
        return banDate;
    }

    public void setBanDate(LocalDate banDate) {
        this.banDate = banDate;
    }

    public Set<UserBook> getBooks() {
        return books;
    }

    public void setBooks(Set<UserBook> books) {
        this.books = books;
    }

    // add book to userBook
    public void addBook(Book book) {
        UserBook userBook = new UserBook(this, book);
        books.add(userBook);
        book.getUserBooks().add(userBook);
    }

    // remove book to userBook
    public void removeBook(Book book) {
        for (Iterator<UserBook> iterator = books.iterator();
             iterator.hasNext(); ) {
            UserBook userBook = iterator.next();

            if (userBook.getUser().equals(this) &&
                    userBook.getBook().equals(book)) {
                iterator.remove();
                userBook.getBook().getUserBooks().remove(userBook);
                userBook.setUser(null);
                userBook.setBook(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return maxBorrowCount == user.maxBorrowCount && banStatus == user.banStatus
                && Objects.equals(uid, user.uid) && Objects.equals(fName, user.fName)
                && Objects.equals(lName, user.lName) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(banDate, user.banDate)
                && Objects.equals(books, user.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fName, lName, email,
                password, maxBorrowCount, banStatus, banDate, books);
    }
}
