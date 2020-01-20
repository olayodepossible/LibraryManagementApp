package com.possible.library;

public class Book {
    private String title;
    private String author;
    private int noOfCopies;

    public Book( String title, String author, int num){

        this.title = title;
        this.author = author;
        this.noOfCopies = num;
    }

    public void setNoOfCopies(int num){
        if(num < 0){
            System.out.println("Number of Book cannot be zero");
        }
        else {
            this.noOfCopies  = num;
        }
    }

    public int getnoOfCopies(){
        return noOfCopies;
    }

    public String getTitle(){
        return  this.title;
    }
}
