package com.example.bookrecyclerview.Model;

public class CartItem {
    int BookImage;
    String BookName;
    String AuthorName;
    int Quantity;
    float Price;


    public CartItem(int BookImage, String BookName, String AuthorName, int Quantity, float Price){
        this.BookImage = BookImage;
        this.BookName = BookName;
        this.AuthorName = AuthorName;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public int getBookImage() {
        return BookImage;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void addToCart(){this.Quantity +=1; }

    public void removeFromCart() {
        if (this.Quantity >= 1) {
            this.Quantity -= 1;
        }
    }

    public float getPrice() {
        return Price;
    }
}
