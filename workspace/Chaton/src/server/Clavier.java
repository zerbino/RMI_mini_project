package server;
import java.io.*;
public class Clavier {
   static BufferedReader input 
	=new BufferedReader (new InputStreamReader(System.in));
     public static long lireLong() {
	try {
	    return Long.parseLong(lireString().trim());
	} catch (Exception e) {
	    return 0;
	}
   }
     public static int lireInt() {
	try {
	    return Integer.parseInt(lireString().trim());
	} catch (Exception e) {
	    return 0;
	}
   }
     public static boolean isInteger(String string){
    	 try{
    		 Integer.parseInt(string);
    		 return true;
    	 } catch(NumberFormatException e){
    		 return false;
    		 
    	 }
     }
    public static double lireDouble() {
	try {
	    return Double.parseDouble(lireString().trim());
	} catch (Exception e) {
	    return 0.0;
	}
   }
    public static float lireFloat() {
	try {
	    return Float.parseFloat(lireString().trim());
	} catch (Exception e) {
	    return 0.0F;
	}
   }
    public static char lireChar() {
	try {
	    return lireString().charAt(0);
	} catch (Exception e) {
	    return (char)0;
	}
   }

    public static String lireString() {
	System.out.flush();
	try {
	    return input.readLine();
	} catch (Exception e) {
	    return "";
	}
   }
    public static void main(String[] args){
	System.out.println("Essai1 Clavier");
	System.out.print("Entier : ");
	int i= lireInt();
	System.out.println("Entier lu : " + i);
	System.out.print("Long : ");
	long il= lireLong();
	System.out.println("Entier lu (long) : " + il);
	System.out.print("Char : ");
	char c=lireChar();
	System.out.println("Char lu : " + c);
	System.out.println("Double : ");
	double d=lireDouble();
	System.out.println("Double lu : " + d);
	System.out.println("String : ");
	String s=lireString();
	System.out.println("String lu : " + s);
    }
}
