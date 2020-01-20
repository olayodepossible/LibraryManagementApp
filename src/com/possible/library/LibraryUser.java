package com.possible.library;

import java.time.LocalDate;

public abstract class LibraryUser implements Comparable {

    private String name;
    private int codeNo;
    private String level;
    private LocalDate date;


    public LibraryUser(){
        date =  LocalDate.now();
    }
    public LibraryUser(int code,String name, String level){
        this.codeNo = code;
        this.name = name;
        this.level = level;
        date =  LocalDate.now();
    }

    public String getLevel(){
        return this.level;
    };

    public String getName(){
        return this.name;
    };

    public  int getCodeNo(){
        return  this.codeNo;
    };

    public LocalDate getDate(){
        return this.date;
    };

    public abstract String displayUserDetails();
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
