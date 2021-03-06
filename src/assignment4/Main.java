package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.List;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
//yay
    //hi
    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        while(true) {
        	System.out.print("critters>");
        	String input = kb.next();
        	int count = 1;
        	if(input.equals("quit")) {
        		String a;
        		try {
        			a = kb.nextLine();
        		} catch (NoSuchElementException e) {
        			a = "";
        		}
        		if(a.length()!=0) {
        			System.out.println("error processing: " + input + a);
        			System.out.flush();
        			continue;
        		}
        		return;
        	}
        	else if(input.equals("show")) {
        		String a;
        		try {
        			a = kb.nextLine();
        		} catch (NoSuchElementException e) {
        			a = "";
        		}
        		if(a.length()!=0) {
        			System.out.println("error processing: " + input + a);
        			System.out.flush();
        			continue;
        		}
        		Critter.displayWorld();
        	}
        	else if(input.equals("step")) {	
        		String name = kb.nextLine();
        		if(name.length()!=0) {
        			try {
        				count = Integer.parseInt(name.substring(1));
        			} catch (NumberFormatException e) {
        				System.out.println("error processing: " + input + name);
        				System.out.flush();
        				continue;
        			}
        		}
        		else {
        			count = 1;
        		}
        		for(int i = 0; i < count;i++) {
					Critter.worldTimeStep();
				}
        	}
        	else if(input.equals("seed")) {
        		String name = kb.nextLine();
        		try {
        			count = Integer.parseInt(name.substring(1));
        		}catch (NumberFormatException e) {
        			System.out.println("error processing: " + input + name);
        			System.out.flush();
        			continue;
        		}
        		Critter.setSeed(count);
        	}
        	else if(input.equals("make")) {
        		String name;
        		name = kb.nextLine();
        		if(name.length()==0) {
        			System.out.println("error processing: " + input);
        			System.out.flush();
        			continue;
        		}
        		name = name.substring(1);
				String delims="[ ]+";
				String[] token=name.split(delims);
				ArrayList<String> a=new ArrayList<>(Arrays.asList(token));
				if (a.size()<2){
					a.add(Integer.toString(1));
				}
				else if(a.size()>2){
					System.out.println("error processing: " + input + " "+ name);
					System.out.flush();
        			continue;
				}
				try {
					count=Integer.parseInt(a.get(1));
				} catch (NumberFormatException e) {
					System.out.println("error processing: " + input +  " "+ name);
					System.out.flush();
        			continue;
				}
				try {
					Integer.parseInt(a.get(0));
					System.out.println("error processing: " + input +  " "+ name);
					System.out.flush();
        			continue;
				} catch(NumberFormatException e) {
					name = a.get(0);
				}
        		try {
        			for(int i = 0; i < count;i++) {
        				Critter.makeCritter(name);
        			}
        		} catch (InvalidCritterException e) {
        			System.out.println("error processing: " + input +  " "+ name);
					System.out.flush();
        			continue;
        		}
        	}
        	else if(input.equals("stats")) {
        		String name = "";
        		name = kb.nextLine();
        		if(name.length()==0) {
        			System.out.println("error processing: " + input);
        			System.out.flush();
        			continue;
        		}
        		try {
					Class<?> c = Class.forName("assignment4." + name.substring(1));
					try {
						c.getMethod("runStats", java.util.List.class).invoke(null,Critter.getInstances(name.substring(1)));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidCritterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					System.out.println("error processing: " + input + name);
					System.out.flush();
        			continue;
				}
        	}
        	else {
        		System.out.println("invalid command: " + input);
        	}
        	System.out.flush();
        }
        
        /* Write your code above */
    }
}
