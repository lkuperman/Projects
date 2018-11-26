/**
 * Author: Leah Kuperman and Jillian Baggett
 * Course: CMPS 1600
 * Lab Section: Tuesday at 5pm (2)
 * Assignment: Project 0
 * Date Created: 10/31/18
 */


import java.util.*;
import java.util.ArrayList;
import java.lang.Math.*;

public class MyLong {

    ArrayList<Integer> wholeArray = new ArrayList<Integer>();
    boolean isNeg;
    boolean swap;
    int count;

    //constructor with no parameter
    public MyLong() {
        swap = false;
        count = 0;
    }

    //constructor with parameter so user doesn't have to set long
    public MyLong(String num) {
        swap = false;
        setLong(num);
        count = 0;
    }


    /**
     * sets MyLong using the string newNum, which is called in the constructor.
     */
    public void setLong(String newNum) //updated
    {
        String value1 = newNum;
        int number = 0;
        int stringLength = value1.length();
        this.wholeArray = new ArrayList<Integer>();

        String value = value1;
        if (value1.charAt(0) == ('-')) {
            //System.out.println("Is negative");
            isNeg = true;
            value = value1.substring(1, stringLength);
        } else {
            isNeg = false;
        }
        stringLength = value.length();

        for (int i = 0; i < stringLength; i++) {
            number = Integer.parseInt(value.substring(i, (i + 1)));
            this.wholeArray.add(number);
        }
        this.swap = false;
        count = 0;
    }

    /**
     *
     * Helper method to return MyLong in the add method as desired
     */
    public MyLong add(MyLong other)
    {
        MyLong temp = new MyLong();
        temp.setLong(this.addStr(other));
        return temp;
    }

    /**
     *
     * Helper method to return MyLong in the multiply method as desired
     */
    public MyLong multiply(MyLong other)
    {
        MyLong temp = new MyLong();
        temp.setLong(this.multiplyStr(other));
        return temp;
    }

    /**
     *
     * Helper method to return MyLong in the subtract method as desired
     */
    public MyLong subtract(MyLong other)
    {
        MyLong temp = new MyLong();
        temp.setLong(this.subtractStr(other));
        return temp;
    }

    /**
     * Adds two myLong objects together and returns a string
     */
    public String addStr(MyLong other) { //updated
        String newLong = "";
        int carry = 0;  //digit that will store what is carried over to the next column in addition
        int len_t = this.wholeArray.size();
        int len_o = other.wholeArray.size();
        int diff = Math.abs(len_o-len_t);
        String dis = "";
        String oth = "";
        if (len_o >= len_t)
        {
            dis = "0" + this.makeStr(diff);     //adds extra zeroes to deal with "carry" error
            oth = "0" + other.makeStr(0);
        }
        else
        {
            dis = "0" + this.makeStr(0);
            oth = "0" + other.makeStr(diff);
        }

        for (int j = dis.length() - 1; j >= 0 ; j--) //iterates through whole array for different "10s" of numbers
        {
            int comp1 = Integer.parseInt(dis.substring(j, (j + 1)));
            int comp2 = Integer.parseInt(oth.substring(j, (j + 1)));
            int result = comp1 + comp2 + carry;
            //adds both items in the column, along with a carry if it exists
            if (result >= 10) {
                carry = result / 10; //carry is stored for the next column
                result = result % 10; //result is what gets written below the line
            } else {
                carry = 0; //if the result is a single digit, there is no carry
            }
            newLong = "" + result + newLong; //adds the numbers in order into a string
        }
        return ridZeroes(newLong); //returns the string of numbers

    }

    /**
     * Multiply takes one MyLong parameter and multiplies every component of that to the object
     * being referenced. An object containing the result of the multiplication is returned
     *
     * @param is the MyLong that you want multiplied by the MyLong being referenced
     * @return a MyLong object representing the result of the multiplication
     */
    public String multiplyStr(MyLong other) {
        String newNum = "";
        String progress = "0"; //"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        //this string holds the additions of each row of multiplication, since they can only be added
        //two at a time. Starts as all zeroes
        int carry = 0; //digit that will store what is carried over to the next column when multiplying
        int len_t = this.wholeArray.size();
        int len_o = other.wholeArray.size();
        int diff = Math.abs(len_o-len_t);
        String dis = "";
        String oth = "";
        if (len_o >= len_t)
        {
            dis = "0" + this.makeStr(diff);     //adds extra zeroes to deal with "carry" error
            oth = "0" + other.makeStr(0);
        }
        else
        {
            dis = "0" + this.makeStr(0);
            oth = "0" + other.makeStr(diff);
        }

        int last = this.wholeArray.size()-1;
        for (int i = dis.length()-1; i >= 0; i--) //represents the array of 10s we are working in
        {
            String offset = ""; //offset keeps track of how many zeroes go before a row of multiplication
            for (int w = (dis.length()-1 - i); w > 0; w--) {
                offset += "0";
            }
            newNum = offset; //before each row, newNum starts off as how much it is shifted over
            for (int k = dis.length()-1; k>=0; k--) //represents the digit of the number we are multiplying
            {
                int comp1 = Integer.parseInt(dis.substring(i, (i + 1)));
                int comp2 = Integer.parseInt(oth.substring(k, (k + 1)));
                int result = comp1 * comp2 + carry;
                //multiplies the two items, and then adds a carry if it exists
                if (result >= 10)
                {
                    carry = result / 10; //carry is stored for the next column
                    result = result % 10; //result is what gets written below the line

                }
                else {
                    carry = 0; //if the result is a single digit, there is no carry
                }
                newNum = "" + result + newNum; //the multiplied number is added to the string

            }
            MyLong num = new MyLong(newNum);
            //after each number of the second object gets multiplied by a single number in the
            //first, a new object is created with the string of numbers
            MyLong prog = new MyLong(progress);
            progress = prog.addStr(num); //progress becomes the addition of the two numbers

        }
        //MyLong prog = new MyLong(progress);
        //returns a long object of the result once all the for loops are finished
        return ridZeroes(progress);
    }
    //returns length of number


    /**
     * subtract takes one MyLong parameter and subtracts every component of that to the object
     * being referenced. A string is returned
     *
     * @param is the long that you want subtracted to the long you are referencing
     * @return a string that can be turned into a long object if needed
     */

    public String subtractStr(MyLong b) //updated
    {
        String new_num = "";
        int tempDig1 = -1;
        int tempDig2 = -1;
        int digit = 0;
        ArrayList<Integer> top = copyArr(this.wholeArray);
        ArrayList<Integer> bottom = copyArr(b.wholeArray);

        int len_t = top.size();
        int len_b = bottom.size();
        int diff = Math.abs(len_b-len_t);

        if (this.isNeg == true && b.isNeg == false) //any negative minus positive (2 cases)
            return ("-" + ridZeroes(this.addStr(b)));
        else if (this.isNeg == false && b.isNeg == true) //any positive minus negative (2 cases)
            return ridZeroes(this.addStr(b));
        if (this.isEqual(b)) {
            //ArrayList<Integer> zero = new ArrayList<Integer> ();
            return "0";
        }
        if (this.isBigger(b) == false) {
            if (this.isNeg == false && b.isNeg == false) //checks cases in which numeric subtract should be used
            {
                //this means we are doing a - b where b>a, which is same as doing (-) + b - a
                this.swap = true; //updates if order was switched
                b.swap = true;
                return ("-" + ridZeroes(b.subtractStr(this)));
            }
            else {
                //this means we are doing (-a) - (-b) where b>a, which is same as doing b - a
                this.swap = true;
                b.swap = true;
                //this.isNeg = false;
                //b.isNeg = false;
                this.count++;
                b.count++;
                return ridZeroes((b.subtractStr(this)));
            }
        }

        else if (this.isNeg == true && b.isNeg == true && this.count == 0 && b.count==0)
        {
            this.count++;
            b.count++;
            return ("-" + b.subtractStr(this));
        }

        //now, "this" is bigger


        else {
            bottom = this.addZeroes(diff, bottom);
            for (int j = len_t - 1; j >= 0 ; j--) //iterates through whole array
            {
                tempDig1 = top.get(j);
                tempDig2 = bottom.get(j);
                digit = tempDig1 - tempDig2;
                if (digit > -1) //case if first digit larger than second
                {
                    new_num = digit + new_num;
                }
                else //case if answer is negative & borrowing is necessary
                {
                    int temp = j;
                    if (temp > 0)
                    {
                        if (top.get(temp-1) > 0)
                        {
                            top.set(temp-1, top.get(temp-1) - 1);
                        }
                        else
                        {
                            while (top.get(temp-1) == 0) {
                                temp--;
                            }
                            top.set(temp-1, top.get(temp)-1);
                            while (temp <= j) {
                                top.set(temp, 9);
                                temp++;
                            }
                        }
                        tempDig1 += 10;
                        digit = tempDig1 - tempDig2;
                        new_num = digit + new_num;
                    }
                }
            }
            return new_num;
        }

    }

    //helper methods used in the above functions

    /**
     * isBigger is used to aid subtract in determining the number with the largest absolute value
     */
    public boolean isBigger(MyLong d) //if true, it means that this is bigger than d
    //updated
    {
        boolean flag2 = false;
        if (this.wholeArray.size() < d.wholeArray.size())
            flag2 = false;
        else if (this.wholeArray.size() > d.wholeArray.size())
            flag2 = true;
        else {
            for (int i = 0; i <= this.wholeArray.size(); i++) {
                if (this.wholeArray.get(i) > d.wholeArray.get(i)) {
                    flag2 = true;
                    break;
                } else if (this.wholeArray.get(i) < d.wholeArray.get(i)) {
                    flag2 = false;
                    break;
                }
            }
        }
        return flag2;
    }


    public int getLength() { //updated
        return this.wholeArray.size();
    }


    /**
     * aids subtract method in determining if the numbers are equal
     */
    public boolean isEqual(MyLong b) { //updated
        if (this.wholeArray.size() != b.wholeArray.size())
        {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < wholeArray.size(); i++) {
            if (this.wholeArray.get(i) != b.wholeArray.get(i))
                flag = false;
        }

        return flag;
    }

    /**
     * Turns a myLong into a string and if it is negative adds a negative symbol
     */
    public String toString() { //updated
        String wholeNum = "";
        if (this.isNeg == true) {
            wholeNum+= "-";
        }
        for (int v = 0; v < wholeArray.size(); v++)
            wholeNum += this.wholeArray.get(v);

        return ridZeroes(wholeNum);
    }

    /**
     * prints contents of a myLong variable
     */
    public void getLong() //updated
    {
        //also used for testing purpose
        String wholeNum = "";
        for (int v = 0; v < wholeArray.size(); v++) {
            wholeNum += this.wholeArray.get(v);
        }
        while (wholeNum.length()>1 && wholeNum.substring(0,1).equals("0"))
            wholeNum = wholeNum.substring(1);
        System.out.println(wholeNum);
    }

    /**
     * turns the array of numbers into a string of numbers and adds appropriate zeroes
     * to match the length of another myLong array
     */
    public String makeStr(int diff)
    {
        String num = "";
        for (int i=0; i<diff; i++)
        {
            num += "0";
        }
        for (int j=0; j<this.wholeArray.size(); j++)
        {
            num += "" + this.wholeArray.get(j);
        }
        return num;
    }

    /**
     * gets rid of zeroes preceding a number
     */
    public String ridZeroes(String num)
    {
        while (num.length()>1 && num.substring(0,1).equals("0"))
            num = num.substring(1);
        return num;
    }

    /**
     * prints contents of a myLong variable
     */
    public ArrayList<Integer> makeArr(int diff)
    {

        String str = this.makeStr(diff);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i=0; i<str.length(); i++)
        {
            temp.add(Integer.parseInt(str.substring(i, i+1)));
        }
        return temp;
    }

    /**
     * copies one array into a different one
     */
    public ArrayList<Integer> copyArr(ArrayList<Integer> input)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i=0; i<input.size(); i++)
        {
            temp.add(input.get(i));
        }
        return temp;
    }

    /**
     * Prepends an existing array with zeroes to allow subtraction to work with large numbers
     */
    public ArrayList<Integer> addZeroes(int diff, ArrayList<Integer> x)
    {

        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i =0; i<diff; i++)
        {
            temp.add(0);
        }
        for (int j=0; j<x.size(); j++)
        {
            temp.add(x.get(j));
        }
        return temp;
    }

    public void setNeg(boolean val)
    { isNeg = val;
    }

    public boolean getNeg()
    { return isNeg;
    }
}

