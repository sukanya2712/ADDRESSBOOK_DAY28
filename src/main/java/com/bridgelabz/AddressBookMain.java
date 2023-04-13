package com.bridgelabz;

import java.io.*;
import java.nio.file.Files;y

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
//uc14
public class AddressBookMain {

    static HashMap<String , AddressBook> hashMap = new HashMap<String, AddressBook>();
    public void AddAddressbook(){
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the AddressBook");
        String name=sc.next();
        if(hashMap.containsKey(name)){
            System.out.println("Enter different name for the AddressBook");
            AddAddressbook();
        }else {
            addressBook.setAddressbookName(name);
            hashMap.put(addressBook.getAddressbookName(), addressBook);
            System.out.println("Address book added!!");
        }
    }
    public void Addcontact(){
        if(hashMap.isEmpty())
        {
            System.out.println("Your address book is empty first please add new Addressbook");
            AddAddressbook();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the addressbok in which you want to add contact: ");
        String name = sc.next();
        if(hashMap.containsKey(name)){
            AddressBook temp = hashMap.get(name);
            temp.AddContact();
        }
    }
    public void Display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the addressbok in which you want to display contact: ");
        String name = sc.next();
        if(hashMap.containsKey(name)){
            AddressBook temp = hashMap.get(name);
            temp.display();
        }
    }

//    public void opencsv(){
//        private static final String SAMPLE_CSV_FILE_PATH=""
//    }


    public void Delete(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the addressbok in which you want to Delete contact: ");
        String name = sc.next();
        if(hashMap.containsKey(name)){
            AddressBook temp = hashMap.get(name);
            temp.delete();
        }
    }
    public void Edit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the addressbok in which you want to Edit contact: ");
        String name = sc.next();
        if(hashMap.containsKey(name)){
            AddressBook temp = hashMap.get(name);
            temp.Edit();
        }
    }
    public void displayAllAddressbook(){
        System.out.println("Displaying all addressbook");
        if(hashMap.isEmpty()){
            System.out.println("Addressbook is empty");
        }else {
            System.out.println(hashMap);
        }
    }
    public void searchBycity(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the city which you want to show");
        String cityname = sc.next();
        List<Contact> citylist = new ArrayList<>();
        hashMap.values().stream().forEach(addressBook ->{
            citylist.addAll(addressBook.getContactBook().
                    stream().filter(contact ->  contact.getCity().equalsIgnoreCase(cityname)).sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList()));
        });
        int count = citylist.size();
        System.out.println(count+" Person Found!!! which belongs to " +cityname +" city");
        System.out.println(citylist);
    }
    public void sort(){
        List<Contact> citylist = new ArrayList<>();
        hashMap.values().stream().forEach(addressBook ->{
            citylist.addAll(addressBook.getContactBook().
                    stream().sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList()));
        });
        System.out.println(citylist);
    }
    public void searchByState(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to search person by using name of the city ");
        System.out.println("Press 2 to search person by using name of the city ");
        int num =sc.nextInt();
        if(num==1){
            searchBycity();
        }else {
            System.out.println("Enter the name of the city which you want to show");
            String State = sc.next();
            List<Contact> Statelist = new ArrayList<>();
            hashMap.values().stream().forEach(addressBook ->{
                Statelist.addAll(addressBook.getContactBook().
                        stream().filter(contact ->  contact.getCity().equalsIgnoreCase(State)).sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList()));
            });
            int count = Statelist.size();
            System.out.println("Total number of contact person");
            System.out.println(count+" Person Found!!! which belongs to " +State +" city");
            System.out.println(Statelist);
        }
    }
    private void writeToFile() {
        String path = "C:\\Users\\Sukanay\\IdeaProjects\\DAY28\\src\\main\\java\\com\\bridgelabz\\address_book.txt";
        StringBuffer addressBookBuffer = new StringBuffer();
        hashMap.values().stream().forEach(contact -> {
            String personDataString = contact.toString().concat("\n");
            addressBookBuffer.append(personDataString);
        });

        try {
            Files.write(Paths.get(path), addressBookBuffer.toString().getBytes());
        }
        catch (IOException e) {
            System.out.println("Catch block");
        }
    }
    private void readFromFile() {
        String path = "C:\\Users\\Sukanay\\IdeaProjects\\DAY28\\src\\main\\java\\com\\bridgelabz\\address_book.txt";
        System.out.println("Reading from : " + path + "\n");
        try {
            Files.lines(new File(path).toPath()).forEach(employeeDetails -> System.out.println(employeeDetails));
        }
        catch(IOException e){
            System.out.println("Catch block");
        }
    }
    private void writetocsv() {
        String csvPath = "C:\\Users\\Sukanay\\IdeaProjects\\DAY28\\src\\main\\java\\com\\bridgelabz\\addressbook.csv";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(csvPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String[]> csvLines = new ArrayList<String[]>();
        CSVWriter writer = new CSVWriter(fileWriter);
        String[] header = {"FirstName","LastName","Address","City","State","zip code","phone number","Email"};
        writer.writeNext(header);
        hashMap.keySet().stream().forEach(bookName -> hashMap.get(bookName).getContactBook()
                .stream().forEach(person -> csvLines.add(person.getContactStrings())));
        writer.writeAll(csvLines);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readfromcsv(){
        // Reading CSV
        String csvPath = "C:\\Users\\Sukanay\\IdeaProjects\\DAY28\\src\\main\\java\\com\\bridgelabz\\addressbook.csv";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(csvPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVReader reader = new CSVReaderBuilder(fileReader).build();
        List<String[]> linesOfData = null;
        try {
            linesOfData = reader.readAll();
        } catch (IOException | CsvException e) {

            e.printStackTrace();
        }
        System.out.println("\nReading data from csv file:");
        linesOfData.stream().forEach(csvs -> {
            for (String value : csvs)
                System.out.print(value + "\t");
            System.out.println();
        });
        try {
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AddressBookMain addressBookMain = new AddressBookMain();
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Welcome to AddressBook program");
            System.out.println("1) Press 1 to Add new contact");
            System.out.println("2) press 2 to Display contact");
            System.out.println("3) press 3 to Edit contact");
            System.out.println("4) press 4 to Delete contact");
            System.out.println("5) press 5 to Add AddressBook");
            System.out.println("6) press 6 to Display all AddressBook");
            System.out.println("7) press 7 to Exit");
            System.out.println("8) press 8 to Display all the contact from specified city ");
            System.out.println("9) press 9 to Display all the contact from specified State ");
            System.out.println("10) press 10 to Display all the contact in sorted order ");
            System.out.println("11) press 11 to Read from file ");
            System.out.println("12) press 12 to Read from csv file ");


            input = sc.nextInt();
            switch (input) {
                case 1 -> {
                    addressBookMain.Addcontact();
                    addressBookMain.writeToFile();
                    addressBookMain.writetocsv();
                }
                case 2 -> {
                    addressBookMain.Display();
                }
                case 3 -> {
                    addressBookMain.Edit();
                }
                case 7 -> {
                    System.out.println("Thank you so much !!!");
                    System.exit(0);
                }
                case 4 -> {
                    addressBookMain.Delete();
                }
                case 5 -> {
                    addressBookMain.AddAddressbook();
                }
                case 6 -> {
                    addressBookMain.displayAllAddressbook();
                }
                case 8 -> {
                    addressBookMain.searchBycity();
                }
                case 9 -> {
                    addressBookMain.searchByState();
                }
                case 10 -> {
                    addressBookMain.sort();
                }
                case 11 -> {
                    addressBookMain.readFromFile();
                }case 12 -> {
                    addressBookMain.readfromcsv();
                }
            }
        }
    }
}
