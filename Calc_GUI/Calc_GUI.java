/**
 * Author: Leah Kuperman and Jillian Baggett
 * Course: CMPS 1600
 * Lab Section: Tuesday at 5pm (2)
 * Assignment: Project 0
 * Date Created: 10/31/18
 */

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;

public class Calc_GUI extends JFrame implements ActionListener {

    private JButton nine;
    private JButton eight;
    private JButton seven;
    private JButton six;
    private JButton five;
    private JButton four;
    private JButton three;
    private JButton two;
    private JButton one;
    private JButton zero;
    private JButton minus;
    private JButton plus;
    private JButton multiply;
    private JButton left;
    private JButton right;
    private JButton equals;
    private JButton clear;

    private String userMsg = "";

    private GridBagLayout layout;
    private GridBagConstraints c;
    private JFrame calc;
    private JTextField userField;

    public Calc_GUI() {

        //sets up the calculator grid and buttons, and adds action listeners to them
        layout = new GridBagLayout();
        calc = new JFrame("Calculator");
        calc.setLayout(layout);
        c = new GridBagConstraints();
        calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
        calc.setVisible(true);
        calc.setLayout(layout);
        calc.setSize(350, 570);
        calc.setResizable(false);

        Scanner console = new Scanner(System.in);

        userField = new JTextField("");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridwidth = GridBagConstraints.REMAINDER;
        calc.add(userField, c);

        clear = new JButton("Clear");
        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        clear.addActionListener(this);
        calc.add(clear, c);

        seven = new JButton("7");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        seven.addActionListener(this);
        calc.add(seven, c);

        eight = new JButton("8");
        c.gridx = 1;
        c.gridy = 1;
        eight.addActionListener(this);
        calc.add(eight, c);

        nine = new JButton("9");
        c.gridx = 2;
        c.gridy = 1;
        nine.addActionListener(this);
        calc.add(nine, c);

        multiply = new JButton("X");
        c.gridx = 3;
        c.gridy = 1;
        multiply.addActionListener(this);
        calc.add(multiply, c);

        four = new JButton("4");
        c.gridx = 0;
        c.gridy = 2;
        four.addActionListener(this);
        calc.add(four, c);

        five = new JButton("5");
        c.gridx = 1;
        c.gridy = 2;
        five.addActionListener(this);
        calc.add(five, c);

        six = new JButton("6");
        c.gridx = 2;
        c.gridy = 2;
        six.addActionListener(this);
        calc.add(six, c);

        minus = new JButton("-");
        c.gridx = 3;
        c.gridy = 2;
        minus.addActionListener(this);
        calc.add(minus, c);

        one = new JButton("1");
        c.gridx = 0;
        c.gridy = 3;
        one.addActionListener(this);
        calc.add(one, c);

        two = new JButton("2");
        c.gridx = 1;
        c.gridy = 3;
        two.addActionListener(this);
        calc.add(two, c);

        three = new JButton("3");
        c.gridx = 2;
        c.gridy = 3;
        three.addActionListener(this);
        calc.add(three, c);

        plus = new JButton("+");
        c.gridx = 3;
        c.gridy = 3;
        plus.addActionListener(this);
        calc.add(plus, c);

        zero = new JButton("0");
        c.gridx = 0;
        c.gridy = 4;
        zero.addActionListener(this);
        calc.add(zero, c);

        left = new JButton("(");
        c.gridx = 1;
        c.gridy = 4;
        left.addActionListener(this);
        calc.add(left, c);

        right = new JButton(")");
        c.gridx = 2;
        c.gridy = 4;
        right.addActionListener(this);
        calc.add(right, c);

        equals = new JButton("=");
        c.gridx = 3;
        c.gridy = 4;
        equals.addActionListener(this);
        calc.add(equals, c);

        calc.setVisible(true);
    }

    public static void main(String[] args) {
        Calc_GUI x = new Calc_GUI();
        x.setVisible(true);

        /*
        System.out.println(userMsg);
        while (userMsg.indexOf("=") == -1)
        {
        userField = new JTextField(userMsg);
        calc.add(userField, c);
        }
         */

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zero) {
            //System.out.println("0");
            userMsg += "0";
            userField.setText(userMsg);

        }
        else if (e.getSource() == clear)
        { userMsg = "";
            userField.setText(userMsg);
        }
        else if (e.getSource() == one) {
            userMsg += "1";
            userField.setText(userMsg);
        } else if (e.getSource() == two) {
            userMsg += "2";
            userField.setText(userMsg);
        } else if (e.getSource() == three) {
            userMsg += "3";
            userField.setText(userMsg);
        } else if (e.getSource() == four) {
            userMsg += "4";
            userField.setText(userMsg);
        } else if (e.getSource() == five) {
            userMsg += "5";
            userField.setText(userMsg);
        } else if (e.getSource() == six) {
            userMsg += "6";
            userField.setText(userMsg);
        } else if (e.getSource() == seven) {
            userMsg += "7";
            userField.setText(userMsg);
        } else if (e.getSource() == eight) {
            userMsg += "8";
            userField.setText(userMsg);
        } else if (e.getSource() == nine) {
            userMsg += "9";
            userField.setText(userMsg);
        } else if (e.getSource() == multiply) {
            userMsg += "*";
            userField.setText(userMsg);
        } else if (e.getSource() == minus) {
            userMsg += "-";
            userField.setText(userMsg);
        } else if (e.getSource() == plus) {
            userMsg += "+";
            userField.setText(userMsg);
        } else if (e.getSource() == left) {
            userMsg += "(";
            userField.setText(userMsg);
        } else if (e.getSource() == right) {
            userMsg += ")";
            userField.setText(userMsg);
        } else if (e.getSource() == equals) {
            if (isValidInput(userMsg)) {
                //userField.setText("Valid Input");
                //call myLong method
                String result = "";
                result =  eval2(userMsg);
                userField.setText(result);

            } else {
                userField.setText("Invalid Input");
            }
            userMsg = "";
        }

    }

    /**
     * Checks several different calculator error cases to determine whether or not the input of
     * the calculator is valid before attempting to evaluate it.
     */
    public boolean isValidInput(String input) {
        boolean check_parentheses = false;

        int left = 0;
        int right = 0;
        String ops = "+*-";
        int save_middle1 = 0;
        int save_middle2 = 0;

        for (int i = 0; i < input.length(); i++) {

            if (input.substring(i, i + 1).equals("(")) {
                left++;
                save_middle1 = i;
            }

            if (input.substring(i, i + 1).equals(")")) {
                right++;
                save_middle2 = i;
            }

        }
        if (right == left)
            check_parentheses = true;
        if (save_middle1 == (save_middle2 - 1))
        {
            check_parentheses = false;
        }

        //this is to check that the +/- has numbers around it
        for (int i = 0; i < input.length(); i++) {
            int last = input.length() - 1;
            if (ops.contains(input.substring(0, 1)) || ops.contains(input.substring(last))) {
                return false;
            }

            if (ops.contains(input.substring(i, i + 1))) {
                if (ops.contains(input.substring(i + 1, i + 2)))
                    return false;
            }
        }
        return check_parentheses;
    }

    /**
     * Evaluates a mathematic equation that has already been deemed valid by isValidInput()
     * Uses recursion and doMath() to implement order of operations and return the correct result
     */
    public static String eval2(String input)
    {
        String pass = ""; //what will be passed into doMath for math evaluation
        String remaining1 = ""; //what is left of the input statement to be evaluated on the left
        String remaining2 = "";
        String returnString = ""; //string that will be returned throughout recursive calls
        boolean checkInsides = false;

        if (!input.contains("("))
        { return doMath(input);
        }

        if (input.contains("(")) //this checks to see if input has parenthesis and if recursion should be used at all
        {
            for (int i = input.length()-1; i >=0; i--) {
                if (input.charAt(i) == '(') {
                    for (int j = i + 1; j < input.length(); j++) //checks from where left ( was to find right )
                    {
                        if (input.charAt(j) == ')')//this means it must be inner parenthesis
                        {
                            if (i == 0) {
                                checkInsides = true;
                            }
                            pass = input.substring((i + 1), j); //inside of middle most paranthesis
                            remaining1 = input.substring(0, i); //left of innermost parenthesis
                            remaining2 = input.substring((j + 1)); //right of innermost parenthesis
                            return (eval2(remaining1 + doMath(pass) + remaining2));
                        }
                    }
                }
            }
        }
        return (eval2(remaining1 + doMath(pass) + remaining2));
    }

    /**
     * Implements order of operations and returns the correct result of a mathematic equation
     * by storing each number and operation as separate elements of an ArrayList
     */
    public static String doMath(String pass) //method that actually computes inside the parenthesis
    {
        String returnString = "";
        pass = pass + " ";
        MyLong number1;
        MyLong number2;
        String result = "";
        ArrayList<Object> equation = new ArrayList<Object>();

        String ops = "+*-";
        int minusIndex = -1;
        boolean flag = true;
        String number = "";
        String negativeReturn = "";
        boolean negativeFlag = false;
        String tempPass = "";
        if(pass.contains("-"))
        {
            minusIndex = pass.indexOf('-');

            if (minusIndex==0)
            { tempPass = pass.substring(1);
                if (!(tempPass.contains("*")) && !(tempPass.contains("+")) && !(tempPass.contains("-")))
                { return (pass);
                }
            }

        }
        for (int h = 0; h < pass.length(); h++)//to make an arraylist of operators and numbers, in order
        {
            number = "";

            if (h == 0)
            {
                if (pass.substring(h, h+1).equals("-"))
                { negativeFlag = true;
                    pass = pass.substring(1);
                }
            }
            else if (pass.substring(h-1, h).equals("-"))
            {
                if (ops.contains(pass.substring((h - 2), h-1)))
                {
                    negativeFlag = true;
                    equation.remove(equation.size() - 1);
                }
            }

            if (Character.isDigit(pass.charAt(h))) {
                while (Character.isDigit(pass.charAt(h)) || pass.substring(h,h+1).equals(".") ) //this makes sure we have all digits of the number
                { number += pass.substring(h, (h + 1));
                    h++;
                }
                MyLong temp = new MyLong(number);
                if (negativeFlag == true)
                { temp.setNeg(true);

                }


                equation.add(temp);

                h--;
            }
            else if (pass.charAt(h) == '*') {
                number = "*";
                equation.add(number);
            }
            else if (pass.charAt(h) == '+')
            {
                number = "+";
                equation.add(number);
            }
            else if (pass.charAt(h) == '-')
            {
                number = "-";
                equation.add(number);
            }
            negativeFlag = false;
        }

        for(int i = 0; i < equation.size(); i++)
        {

            if (equation.get(i).equals("*"))
            {
                number1 = (MyLong)(equation.get(i-1));
                number2 = (MyLong)(equation.get(i+1));
                if((number1.getNeg() == true && number2.getNeg() == false) || (number1.getNeg() == false && number2.getNeg() == true))
                { result = "-";
                }

                result = result + number1.multiply(number2).toString();

                //removing the two numbers and their operator from the ArrayList
                equation.remove(i);
                equation.remove(i);
                equation.remove(i-1);
                MyLong a = new MyLong(result);
                equation.add(i - 1, a);
                i--;
            }
        }

        for(int i = 0; i < equation.size(); i++)
        {
            if (equation.get(i).equals("+") || equation.get(i).equals("-"))
            {  if (equation.get(i).equals("+"))
            {

                number1 = (MyLong)(equation.get(i-1));
                number2 = (MyLong)(equation.get(i+1));
                if (number1.getNeg() == true && number2.getNeg() == false)
                {   number1.setNeg(false);
                    result = number2.subtract(number1).toString();
                }
                else if (number1.getNeg() == false && number2.getNeg() == true)
                {   number2.setNeg(false);
                    result = number1.subtract(number2).toString();
                }
                else if (number1.getNeg() == true && number2.getNeg() == true)
                { number2.setNeg(false);
                    number1.setNeg(false);
                    result = "-" + number1.add(number2).toString();
                }
                else
                {
                    result = number1.add(number2).toString();}

                //removing the two numbers and their operator from the ArrayList
                equation.remove(i);
                equation.remove(i);
                equation.remove(i-1);
                MyLong a = new MyLong(result);
                equation.add(i - 1, a);
                i--;

            }
            else
            {

                number1 = (MyLong)(equation.get(i-1));
                number2 = (MyLong)(equation.get(i+1));

                result = number1.subtract(number2).toString();

                //removing the two numbers and their operator from the ArrayList
                equation.remove(i);
                equation.remove(i);
                equation.remove(i-1);
                MyLong a = new MyLong(result);
                equation.add(i - 1, a);
                i--;
            }

            }

        }

        for (int g = 0; g < equation.size(); g++)
        {
            returnString += equation.get(g) + " ";
        }
        return ridZeroes(returnString); //returns the number without preceding zeroes
    }

    /**
     * gets rid of zeroes preceding a number
     */
    public static String ridZeroes(String num)
    {
        while (num.length()>1 && num.substring(0,1).equals("0"))
            num = num.substring(1);
        return num;
    }
}



