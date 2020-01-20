package com.possible.library;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Library {
    private Book books;
    private LibraryUser newUser;
    private LocalDate date = LocalDate.now();
    private final int daysToReturn = 7;
    private final int numberOfBorrowedBook = 1;
    Scanner scan = new Scanner(System.in);

    private HashSet<LibraryUser> libraryUsers = new HashSet<>();   //Stores User details
    private CustomHashMap <String, Book> libraryBooks = new CustomHashMap<>();  //Stores book

    private PriorityQueue<LibraryUser> listOfBorrower = new PriorityQueue<>(); // list of borrower

    public  void userRegistration(int codeNo,String name, String level){
        if(!isCodeExist(codeNo)) {

            switch (level.toLowerCase()) {
                case "teacher":
                    newUser = new Teacher(codeNo, name, level);
                    libraryUsers.add(newUser);
                    newUser.displayUserDetails();
                    borrowBook();
                    break;
                case "librarian":
                    newUser = new Librarian(codeNo, name, level);
                    libraryUsers.add(newUser);
                    System.out.println("Welcome librarian " + newUser.getName() + ", your registration is successful...!");
                    break;
                case "student":
                    newUser = new Student(codeNo, name, level);
                    libraryUsers.add(newUser);
                    newUser.displayUserDetails();
                    borrowBook();
                    break;
                default:
                    System.out.println("You are not eligible to borrow book here..!");
            }
        }
        else {
            System.out.println("Code number already exist...!");
        }
    }

    public boolean isCodeExist(int codeNum){
        Iterator it = libraryUsers.iterator();
        for(LibraryUser user: libraryUsers){
            if(user.getCodeNo() == codeNum){
                return true;
            }
        }
        return  false;
    }

    public LibraryUser getMember(int codeNo){
        for(LibraryUser user : libraryUsers){
            if(user.getCodeNo() == codeNo){
                return user;
            }
        }
        return null;
    }


    public void addBook(String title, String author, int copies){
        if(copies > 0){
            books = new Book(title.toUpperCase(),author, copies);
            libraryBooks.put(books.getTitle(), books);
            System.out.println(books.getTitle()+" was added successfully...!");
        }
        else{
            System.out.println("You cannot add Zero number of book");
        }

    }

    public void updateBook(String bookTitle, int num){
        Book book = findBook(bookTitle);
        if( book!= null ){
            int availableBook = book.getnoOfCopies() + num;
            book.setNoOfCopies(availableBook);
        }

    }

    public void removeBook(String bookTitle){

    }

    public void borrowBook() {
        System.out.println("Do you like to borrow a book? Press 1 if Yes or 0 to quit...");

        int yes = scan.nextInt();
        scan.nextLine();
        if(yes == 1){
            System.out.println("Enter the book title");
            String bookTitle = scan.nextLine().toUpperCase();
            borrowBook(bookTitle);
        }
        else{
            System.out.println(newUser.getName()+" thank you for the registration...Bye!");
        }
    }
    /**
     * for newUser and to make a recursive call
     * If the book requested is not availbale and the user want another book
     */

    public void borrowBook(String title){
        Book book = findBook(title);
        if( book!= null ){
            int availableBook = book.getnoOfCopies() - 1;
            book.setNoOfCopies(availableBook);
            printFeedBack(title, daysToReturn);
        }
        else{
            bookNotAvailable(title);
        }
    }


    public  void borrowBook(String title, int code){
        //check for the availabilty of the book and return the book name, the author, the details of the borrower and the returning date
        Book book = findBook(title);
        if( book!= null && book.getnoOfCopies() > 0 ){
            if(book.getnoOfCopies() <= 2 ){
                if( getMember(code).getLevel().equalsIgnoreCase("teacher") ){
                    System.out.println("Before given "+ title+" out, its number is: "+book.getnoOfCopies());
                   int availableBook = book.getnoOfCopies() - 1;
                   book.setNoOfCopies(availableBook);
                   printFeedBack(code, title, daysToReturn);
                    System.out.println("The remaining "+title+" in the library is: "+book.getnoOfCopies());
                }
                else {
                    System.out.println("We are sorry, you cannot access the book. Try another book...");
                    bookNotAvailable(title);
                }
            }
            else{
                System.out.println("Before given "+ title+" out, its number is: "+book.getnoOfCopies());
               int availableBook = book.getnoOfCopies() - 1;
               book.setNoOfCopies(availableBook);
               printFeedBack(code, title, daysToReturn);
               System.out.println("The remaining "+title+" in the library is: "+book.getnoOfCopies());

            }
        }
        else{
            System.out.println("We are sorry, the book "+ title+" is not available");
            bookNotAvailable(title);
        }

    }

    public  void returnBook(String bookTitle, int code){
        Book book = findBook(bookTitle);
        LibraryUser member = getMember(code);
        System.out.println("Before the number of "+book.getTitle()+" is: "+book.getnoOfCopies());
        updateBook(bookTitle,numberOfBorrowedBook );
        System.out.println(member.getName()+" with code number: "+member.getCodeNo()+" returns "+book.getTitle()+" on "+ date.toString());
        System.out.println("After returning " +book.getTitle()+ " is: " +book.getnoOfCopies());

    }

    private Book findBook(String title){
        if(libraryBooks.get(title) != null){
            return libraryBooks.get(title);
        }
        return null;
    }


    private void printFeedBack(String title, long daysToReturn){
        Book book = findBook(title);
        System.out.println("\nYou borrowed " + book.getTitle() + " on " + date.toString());
        System.out.printf("%1$s %2$tB %2$td, %2$tY%n", "Due date:", date.plusDays(daysToReturn));
        return;
    }

    private void printFeedBack(int code, String title, long daysToReturn) {
        LibraryUser member = getMember(code);
        Book book = findBook(title);

        System.out.println("\nName: " + member.getName() + " borrowed " + book.getTitle() + " on " + date.toString());
        System.out.println("\tUser code: " + getMember(code).getCodeNo());
        System.out.println("\tUser Rank: " + getMember(code).getLevel());
//        System.out.println("\tReturn Date: " + date.plusDays(daysToReturn));
        System.out.printf("%1$s %2$tB %2$td, %2$tY%n", "Due date:", date.plusDays(daysToReturn));
        return;
    }

    public void bookNotAvailable(String title){
        System.out.println("\nPress ");
        System.out.println("\t 1 - To try another book");
        System.out.println("\t 0 - To return to the main menu");
        int tryAnotherBook = scan.nextInt();
        scan.nextLine();
        if(tryAnotherBook != 1){
            System.out.println("Thank you for your time...Bye!");
            return;
        }
        System.out.println("Enter the book title");
        String newTitle = scan.nextLine().toUpperCase();
        borrowBook(newTitle);
    }

    public  void displayInstruction(){
        System.out.println("\nPress ");
        System.out.println("\t 0 - To quit the App");
        System.out.println("\t 1 - To print chioce options");
        System.out.println("\t 2 - To register as a new user");
        System.out.println("\t 3 - To access the library as a registered member");
    }

    public  void libarianInstruction(){
        System.out.println("\nPress ");
        System.out.println("\t 0 - To return to main menu");
        System.out.println("\t 1 - To Add books");
        System.out.println("\t 2 - To remove book");
        System.out.println("\t 3 - To borrow book");
        System.out.println("\t 4 - To return book");
    }

}
