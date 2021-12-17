package com.example.bookrecyclerview.Model;

public class BookItem {
    int imgBookImg;
    String idBookName;
    String AuthorName;
    int AuthorImg;
    String Category;
    float idBookPrice;


    public BookItem(int imgBookImg, String idBookName, String AuthorName, int AuthorImg, String Category, float idBookPrice){
        this.imgBookImg = imgBookImg;
        this.idBookName = idBookName;
        this.AuthorName = AuthorName;
        this.AuthorImg = AuthorImg;
        this.Category = Category;
        this.idBookPrice = idBookPrice;
    }

    public int getImgBookImg() {
        return imgBookImg;
    }

    public String getIdBookName() {
        return idBookName;
    }

    public String getAuthorName() {return AuthorName;}

    public int getAuthorImg() { return AuthorImg; }

    public String getCategory() { return Category; }

    public float getIdBookPrice() { return idBookPrice; }
}
