
package subdictionary;

import java.util.Scanner;

/*
    Louis-Charles Marquis 40177137
    Vlad Tita 40209853
    COMP249
    Assignment # 4, Part 2.1
    April 15th, 2022
*/

public class CellPhone implements Cloneable{

    //datafield
    private long serialNum;
    private String brand;
    private int year;
    private double price;
    //default constructor
    public CellPhone() {
    }
    //parameterized constructor
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
    
    //copy constructor
    CellPhone(CellPhone c, long sN){
        serialNum=sN;
        brand=c.brand;
        year=c.year;
        price=c.price;
    }
    
    public Object clone() throws CloneNotSupportedException{
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Enter the serial number: ");
    	long newSerialNum = scan.nextLong();
    	CellPhone cell = new CellPhone(newSerialNum, this.brand, this.year, this.price);
    	return cell;
    }
    
    //get and set methods
    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public long getSerialNum() {
        return serialNum;
    }

    public int getYear() {
        return year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //toString methos
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    //equals method
    public boolean equals(Object obj) {
        
        // If the object is compared with itself then return true
        if (this == obj) {
            return true;
        }

        //Check if obj is an instance of Helicopter or not "null instanceof [type]" also returns false
        if (!(obj.getClass()  == this.getClass())) {
            return false;
        }
        
        // typecast obj to Helicopter so that we can compare data members
        CellPhone other = (CellPhone) obj;

        // Compare the data members and return accordingly
        if (brand != other.brand) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        
        return true;
        
    }
}
