package com.example.bookrecyclerview.Model;

public class AuthorItem {
    int AuthorImg;
    String AuthorName;



    public AuthorItem(int AuthorImg, String AuthorName){
        this.AuthorImg = AuthorImg;
        this.AuthorName = AuthorName;
    }
    public int getAuthorImg() { return AuthorImg; }

    public String getAuthorName() {return AuthorName;}
}

