package com.possible.library;

import java.time.LocalDate;
import java.util.Scanner;

public class Teacher extends  LibraryUser{
  /*  private String name;
    private int codeNo;
    private String level;*/
    private Scanner scan = new Scanner(System.in);

    public Teacher(){}
    public Teacher(int codeNo, String name, String level){
        super(codeNo, name, level);
    }

    @Override
    public String getLevel() {
        return super.getLevel();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getCodeNo() {
        return super.getCodeNo();
    }

    @Override
    public LocalDate getDate() {
        return super.getDate();
    }



    @Override
    public String displayUserDetails() {
        System.out.println(this.getName()+" your registration is successfull...!"+ " You registered on "+ this.getDate() + " as a "+this.getLevel()+" with code number: "+ this.getCodeNo());
        return "";
    }

}
