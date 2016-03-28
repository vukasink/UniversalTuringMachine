        package universalturingmachine;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * @author Vukasin Karadzic, vukasin.karadzic@gmail.com
 */
public class Main {
    
    /**
     * This method checks if a certain String is found in array of Strings
     * @param array array of Strings.
     * @param s string for which we need to check whether it is in the array of Strings.
     * @return true if it is, false if it is not.
     */
    private static boolean in(String[] array, String s){
        for (String s1 : array) {
            if (s.equals(s1)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the array is made of characters (Strings of length 1).
     * @param alphabet
     * @return True if array of Strings represents a valid alphabet for TM.
     */
    private static boolean validAlphabet(String[] alphabet) {
        for (String letter : alphabet) {
            letter = letter.trim();
            if ((letter.length() != 1)) {
                return false;
            }      
        }
        return true;
    }
    
    /**
     * Checks if a array of Strings can represent
     * a set of states.
     * @param states
     * @return True if states are made of valid characters.
     */
    private static boolean validSetOfStates(String[] states) {
        for (String s : states) {
            if (s.equals(""))
                return false;
            s = s.trim();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (! ( (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <=122) ) )
                    return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        System.out.println("");
        System.out.println("Welcome to the Turing Machine simulator! \n");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the states of TM, in one line, divided by commas:");
        String stringStates = sc.nextLine();
        String[] states = stringStates.split(",");
        while (!validSetOfStates(states)) {
            System.out.println("Invalid set of states! Please enter a valid one (states must consist of letters and numbers only):");
            stringStates = sc.nextLine();
            states = stringStates.split(",");
        }
        for (int i = 0; i < states.length; i++)
            states[i] = states[i].trim();
        
        System.out.println("Enter the accepting state of TM:");
        String acceptState = sc.nextLine().trim();
        while (!in(states, acceptState)) {
            System.out.println("Invalid accepting state! Please enter one that is in the set of states:");
            acceptState = sc.nextLine();
        }
        
        System.out.println("Enter the rejecting state of TM:");
        String rejectState = sc.nextLine().trim();
        while (!in(states, rejectState)) {
            System.out.println("Invalid reject state! Please enter one that is in the set of states:");
            rejectState = sc.nextLine();
        }

        System.out.println("Enter the tape alphabet (symbols, characters) of TM, in one line, divided by commas:");
        String stringTapeAlpha = sc.nextLine();
        String[] alphabet = stringTapeAlpha.split(",");
        while(!validAlphabet(alphabet)) {
            System.out.println("Enter the tape alphabet (symbols, characters) of TM, in one line, divided by commas:");
            stringTapeAlpha = sc.nextLine();
            alphabet = stringTapeAlpha.split(",");           
        }
        
        System.out.println("Enter the blank symbol: ");
        String blankLine = sc.nextLine();
        char blank = ' ';
        boolean foundBlank = false;
        while (!foundBlank) {
            if (blankLine.length() == 1 && in(alphabet, blankLine)) {
                blank = blankLine.charAt(0);
                foundBlank = true;
            }
            else {
                System.out.println("Invalid blank state. Please enter one from the tape alphabet:");
                blankLine = sc.nextLine();
            }    
        }
        
        TreeSet<Rule> rules = new TreeSet<>();
        System.out.println("Enter the rules of TM (for format of rules check README.txt):");
        String rule;
        while (!"".equals(rule = sc.nextLine())){    
            rule = rule.substring(1, rule.length()-1);
            String[] ruleParts = rule.split(",");
            if ((ruleParts.length == 5) && in(states, ruleParts[0]) && in(states, ruleParts[2]) && in(alphabet, ruleParts[1]) && in(alphabet, ruleParts[3])
                    && (ruleParts[4].equals(Action.L.toString()) || ruleParts[4].equals((Action.R.toString())))) {
                SymbolStatePair domain = new SymbolStatePair(ruleParts[0], ruleParts[1].charAt(0));
                SymbolStatePair codomain = new SymbolStatePair(ruleParts[2], ruleParts[3].charAt(0));
                Action action = Action.valueOf(ruleParts[4]);
                Rule newRule = new Rule(domain, codomain, action);
                rules.add(newRule);
            }
            else {
                System.out.println("Invalid rule, try again.");
            }
        }
                
        System.out.println("Enter the initial state for TM: ");
        String initialState = sc.nextLine().trim();
        while (!in(states, initialState)) {
            System.out.println("Invalid inital state! Please enter one from the set of states:");
            initialState = sc.nextLine().trim();
        }
        
        System.out.println("Enter the input for TM: ");
        String input = sc.nextLine().trim();
        
        System.out.println("Last question! Do you want part of the tape to be printed on each step? (enter YES or NO):");
        String print;
        boolean printTape;
        while (!("YES".equals(print = sc.nextLine().trim()) || "NO".equals(print))) {
            System.out.println("Invalid input! Please enter YES or NO:");
        }
        printTape = print.equals("YES");

        
        UniversalTuringMachine tm = new UniversalTuringMachine(blank, rules, acceptState, rejectState, input, initialState, printTape);
        tm.execute();      

    }
}
