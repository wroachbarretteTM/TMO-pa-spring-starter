package com.galvanize.tmo.paspringstarter;





public class Book{
    private long id;
    private String author;
    private String title;
    private String yearPublished;

    public Book() {}



    public Book(String author, String title, String yearPublished){
        super();
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    Book(String author, String title, String yearPublished, long id){
        super();
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
        this.id = id;

    }

    public String getAuthor(){
        return this.author;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String yearPublished(){
        return this.yearPublished;
    }
    
    public void setYearPublished(String yearPublished){
        this.yearPublished = yearPublished;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book {id: " + id + ", author: " + author + ", title: " + title + "yearPublished: " + yearPublished + "}";
    }
}