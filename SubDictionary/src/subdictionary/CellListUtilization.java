package subdictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/*
    Louis-Charles Marquis 40177137
    Vlad Tita 40209853
    COMP249
    Assignment # 4, Part 2.4
    April 15th, 2022
*/

public class CellListUtilization {

    public static void main(String[] args){
        //create 2 empty lists
        CellList list = new CellList();
        CellList list2 = new CellList();

        PrintWriter pw=null;
        Scanner sc=null;
        
        try{
            //initialize new printwriter and scanner
            pw=new PrintWriter(new FileOutputStream("Cell_Info.txt"));
            sc=new Scanner(new FileInputStream("Cell_Info.txt"));
            
            long sn;
            String brand;
            int year;
            double price;
            CellPhone c;
            //CHECK FOR SERIAL NUMBER DUPLICATES
            while(sc.hasNextLine()){//go through the whole file
                //reads and stores the data into variables
                sn = sc.nextLong();
                brand = sc.next();
                price = sc.nextDouble();
                year = sc.nextInt();
                //creates a new cellphone object with the previous data
                c= new CellPhone(sn,brand,year,price);
                //add the object to the linked list
                list.addToStart(c);
            }
            pw.close();
            sc.close();
            //shows content of the file by using the linked list
            list.showsContent();
        }
        catch(FileNotFoundException e){
            //display message for the given exception
            System.out.println("File could not be found. "+e.getMessage());
            System.exit(0);
        }
        
        //user enters some serial numbers
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a serial number:");
        int sn = scanner.nextInt();
        System.out.println("The serial number was matched in the following node (if null, serial number wasn't found):"+list.find(sn));
        int sn2 = scanner.nextInt();
        System.out.println("The serial number was matched in the following node (if null, serial number wasn't found):"+list.find(sn2));
        int sn3 = scanner.nextInt();
        System.out.println("The serial number was matched in the following node (if null, serial number wasn't found):"+list.find(sn3));
        int sn4 = scanner.nextInt();
        System.out.println("The serial number was matched in the following node (if null, serial number wasn't found):"+list.find(sn4));
        int sn5 = scanner.nextInt();
        System.out.println("The serial number was matched in the following node (if null, serial number wasn't found):"+list.find(sn5));
        
        //test the methods with the linked list
        CellPhone c1 = new CellPhone(1234,"Apple",1,2010);
        CellPhone c2 = new CellPhone(2345,"Samsung",500,2000);
        CellPhone c3 = new CellPhone(3456,"Moto",600,2100);
        CellPhone c4 = new CellPhone(4567,"LG",200,3000);
        CellPhone c5 = new CellPhone(5678,"Huawei",100,2008);
        CellPhone c6 = new CellPhone(6789,"Nokia",999999,1);
        CellPhone c7 = new CellPhone(6789,"Nokia",999999,1);
        
        list2.addToStart(c1);
        list2.addToStart(c3);
        list2.addToStart(c6);
        list2.addToStart(c5);
        list2.insertAtIndex(c2, 2);
        list2.replaceAtIndex(c4, 2);
        list2.deleteFromStart();
        list2.deleteFromIndex(4);//out of bounds
        //the list is arranged as follows: c6,c4,c1
        list2.find(1234);//returns node c1
        list2.contains(2345);//returns null since no match found
        list2.addToStart(c2);
        list2.contains(2345);//returns node c2
        c6.equals(c7);//true
        c1.equals(c2);//false
        list2.showsContent();//prints content of list
    } 
}