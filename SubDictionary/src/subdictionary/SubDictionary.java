package subdictionary;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SubDictionary {
    
    public static String removeIllegalChar(String st) {
        char[] ch = st.toCharArray();//create an array of character from the method's argument
        if (ch[ch.length - 1] == '.' || ch[ch.length - 1] == ':' || ch[ch.length - 1] == ',' || ch[ch.length - 1] == '?'
                || ch[ch.length - 1] == ';' || ch[ch.length - 1] == '!')
            return st.substring(0, st.length() - 1);//remove illegal characters
        else if (ch.length > 2 && ch[ch.length - 2] == '\'')
            return st.substring(0, st.length() - 2);//remove illegal character
        else if (ch.length == 1)
            return null;//only one char
        else
            return st;//no illegal characters
    }
    
    public static boolean containsNumber(String st) {
        char[] ch = st.toCharArray();//create an array of character from the method's argument
        for (int i = 0; i < ch.length; i++)//go through array of char
            if (Character.isDigit(ch[i]))//checks if contains number
                return true;
        return false;
    }
    
    public static void createArrayList(ArrayList<String> a, Scanner inFile) {
        while (inFile.hasNext()) {//goes through file
            String st = inFile.next().toUpperCase();
            st = removeIllegalChar(st);
            if (st != null && !a.contains(st) && !containsNumber(st))
                a.add(st);//add string to array list
        }
    }
    public static void write(ArrayList<String> arr, PrintWriter writer) {
    	writer.println("The document produced this sub-dictionary, which has "+arr.size()+" entries.");
        for (int i = 0; i < arr.size() - 1; i++) {
            if (i == 0) {
            	writer.println("\n" + arr.get(0).charAt(0));
            	writer.println("==");
            	writer.println(arr.get(0));
            }
            if (i > 0) {
            	writer.println(arr.get(i));
                if (arr.get(i).charAt(0) != arr.get(i + 1).charAt(0)) {
                	writer.println("\n" + arr.get(i + 1).charAt(0));
                	writer.println("==");
                }
            }
        }
        writer.print(arr.get(arr.size()-1));//write into file using printwriter
    }
    public static void main(String[] args) {
    	System.out.println("Please enter your file path: ");
    	Scanner sc = new Scanner(System.in);
    	String file = sc.nextLine(); 
        Scanner inFile = null;
        try {
            //Create the file reader
            inFile = new Scanner(new FileInputStream(file));
        }
        
        catch(FileNotFoundException e) {
                //catch exceptions
        	System.out.println("Could not open input file" + file + " for reading. ");
        	System.out.println("Please check that the file exists and is readable. This program will terminate after closing any opened files.");
        	return;
        }
        ArrayList<String> arr = new ArrayList<String>();
        createArrayList(arr, inFile);
        arr.sort(null);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("Subdictionary.txt"));

            write(arr, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        //close scanners
        inFile.close();
        sc.close();
    }
}