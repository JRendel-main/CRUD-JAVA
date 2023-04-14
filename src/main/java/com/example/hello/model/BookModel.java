package com.example.hello.model;

public class BookModel {
    private Integer id;
    private String title;
    private String author;
    private Integer year;
    private Integer pages;

    private String genre;

    private String status;

    private String state;

    public BookModel(int id, String title, String author, int year, int pages, String genre, String status, String state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.genre = genre;
        this.status = status;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", genre='" + genre + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return id.equals(bookModel.id) &&
                title.equals(bookModel.title) &&
                author.equals(bookModel.author) &&
                year.equals(bookModel.year) &&
                pages.equals(bookModel.pages) &&
                genre.equals(bookModel.genre) &&
                status.equals(bookModel.status) &&
                state.equals(bookModel.state);
    }
}
