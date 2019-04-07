/*
	Grammar:
	E -> x + T
	T -> (E)
	T -> x
*/

import java.util.*;

public class RecDesParser {
    static int ptr;
    static char[] input;

    public static void main(String args[]) {
    	String sample="x+(x+x)";
        System.out.println("Enter the input string:");
        @SuppressWarnings("resource")
		String s = new Scanner(System.in).nextLine();
        input = s.toCharArray();
        if(input.length < 2) {
            System.out.println("The input string is invalid.");
            System.exit(0);
        }
        ptr = 0;
        boolean isValid = E();
        if((isValid) & (ptr == input.length)) {
            System.out.println("The input string is valid.");
        } else {
            System.out.println("The input string is invalid.");
        }
    }

    static boolean E() {
        int fallback = ptr;
        if(input[ptr++] != 'x') {
            ptr = fallback;
            return false;
        }
        if(input[ptr++] != '+') {
            ptr = fallback;
            return false;
        }
        if(T() == false) {
            ptr = fallback;
            return false;
        }
        return true;
    }

    static boolean T() {
        int fallback = ptr;
        if(input[ptr] == 'x') {
            ptr++;
            return true;
        }
        else {
            if(input[ptr++] != '(') {
                ptr = fallback;
                return false;
            }
            if(E() == false) {
                ptr = fallback;
                return false;
            }
            if(input[ptr++] != ')') {
                ptr = fallback;
                return false;
            }
            return true;
        }
    }
}
