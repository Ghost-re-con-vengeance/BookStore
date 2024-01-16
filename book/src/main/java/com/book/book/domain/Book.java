package com.book.book.domain;

import jakarta.persistence.*;

@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private double price;
    @Column(length = 4000)
    private String details;
    private String imageUrl;

    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public Book()
    {

    }

    public Book(Long id,String title,String author,double price,String details,String imageUrl)
    {
        this.id = id;
        this.title = title;
        this.author  =author;
        this.price = price;
        this.details = details;
        this.imageUrl = imageUrl;
    }


}
