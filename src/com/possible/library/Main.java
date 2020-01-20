package com.possible.library;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Library Management Application
 * created by @olayodePossible
 * 14/01/2020
 */

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static  Library library1 = new Library();

    public static void main(String[] args) throws Exception  {

        boolean quit = false;
        int choice = 0;
        library1.displayInstruction();

        try{
            while (!quit){
                System.out.println("Welcome, Enter your choice: ");
                choice = scan.nextInt();
                scan.nextLine();

                switch (choice){
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        library1.displayInstruction();
                        break;
                    case 2:
                        newUser();
                        break;
                    case 3:
                        registeredUser();
                        break;


                }
            }
        }catch (InputMismatchException e){
            System.out.println("Kindly input correct information");
        }
    }


    public static void newUser() throws InputMismatchException{

            System.out.println("Kindly register to enjoy with us...");

            System.out.println("Enter your code number: ");
            int codeNum  = scan.nextInt();
            scan.nextLine();

            System.out.println("Enter your name: ");
            String name = scan.nextLine();
            System.out.println("Enter your rank: ");
            String rank = scan.nextLine();
            library1.userRegistration(codeNum, name, rank);
            library1.displayInstruction();

    }

    public static void registeredUser(){
        System.out.println("Enter your code number: ");
        int codeNum = scan.nextInt();
        scan.nextLine();
        LibraryUser existingUser = library1.getMember(codeNum);
        if(existingUser!= null){

            if(existingUser.getLevel().equalsIgnoreCase("librarian")){
                library1.libarianInstruction();
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice){
                    case 0:
                        library1.displayInstruction();
                        break;
                    case 1:
                        addBook();
                        break;
                    case 2:
                        library1.removeBook(getBookTitle());
                        break;
                    case 3:
                        librarianBorrowBook(codeNum);
                        break;
                    case 4:
                        librarianReturnBook(codeNum);
                        break;
                }

            }
            else if(existingUser.getLevel().equalsIgnoreCase("teacher") || existingUser.getLevel().equalsIgnoreCase("student")){
                System.out.println("\n 1 - To Borrow books or 2 - To Return borrowed book");
                int choice = scan.nextInt();
                scan.nextLine();
                if(choice == 1){
                    System.out.println("Enter the book title");
                    String bookTitle = scan.nextLine().toUpperCase();
                    library1.borrowBook(bookTitle,codeNum);
                }
                else if(choice == 2){
                    System.out.println("Enter the book title");
                    String bookTitle = scan.nextLine().toUpperCase();
                    library1.returnBook(bookTitle, codeNum);
                }
            }
        }
        else {
            System.out.println("Sorry, kindly take your time to register");
        }
        library1.displayInstruction();
    }

    private static void addBook() throws InputMismatchException{
        System.out.println("\nEnter Title: ");
        String bookTitle = scan.nextLine();
        System.out.println("\nEnter the Author:");
        String bookAuthor = scan.nextLine();
        System.out.println("\nEnter the number of copies: ");
        int copies = scan.nextInt();
        scan.nextLine();
        library1.addBook(bookTitle,bookAuthor,copies);
        System.out.println("\nPress 1 - To Add another book or 0 - To return to main menu");
        int choice = scan.nextInt();
        scan.nextLine();
        if(choice == 1){
            addBook();
        }
    }


    private static void librarianBorrowBook(int code){
        System.out.println("Enter the book title: ");
        String bookTitle = scan.nextLine();
        library1.borrowBook(bookTitle, code);
    }

    private static void librarianReturnBook(int code){
        System.out.println("Enter the book title: ");
        String bookTitle = scan.nextLine();
        library1.returnBook(bookTitle, code);
    }

    private static String getBookTitle(){
        System.out.println("Enter the Title: ");
        String bookTitle = scan.nextLine();
        return bookTitle;
    }


}
