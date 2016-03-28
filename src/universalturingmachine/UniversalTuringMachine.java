package universalturingmachine;

import java.util.TreeSet;

/**
 * Class that represents the Universal Turing Machine. Universal Turing Machine
 * is a Turing Machine that receives the definition of arbitrary Turing Machine
 * and an input that is to be executed on that arbitrary Turing Machine, and then
 * simulates the work of that arbitrary TM on that input.
 * For more information about which definition of Turing Machine I used for 
 * implementing this program, please read README.txt.
 * 
 * @author Vukasin Karadzic, vukasin.karadzic@gmail.com
 */
public class UniversalTuringMachine {

    private final char blankSymbol;
    private final TreeSet<Rule> rules;
    private final String acceptState;
    private final String rejectState;
    private  final boolean print;
    
    private final TMTape tape;
    private String machineState;
    private int numSteps;
      
    /**
     * Constructor
     * @param blankSymbol
     * @param rules
     * @param acceptState
     * @param rejectState
     * @param input
     * @param initialState
     * @param print 
     */
    public UniversalTuringMachine (char blankSymbol, TreeSet rules, String acceptState, String rejectState, String input, String initialState, boolean print) {
        this.blankSymbol = blankSymbol;
        this.rules = rules;
        this.acceptState = acceptState;
        this.rejectState = rejectState;
        this.numSteps = 0;
        this.machineState = initialState;
        this.print = print;
        tape = new TMTape(input, blankSymbol);
        
    }
    
    /**
     * Method that executes the Universal Turing Machine.
     */
    public void execute() {
        boolean accepted, rejected;
        System.out.println("\n \n Starting state of the machine and tape:");
        tape.printCurrentState();
        System.out.println("State of the machine: " + machineState);
        do {
            Rule toApply = findRule(machineState, tape.head());
            tape.write(toApply.codomain.symbol);
            machineState = toApply.codomain.state;
            tape.moveHead(toApply.move);
            ++numSteps;
            if (print) {
                System.out.println("\n Step number: " + numSteps);
                tape.printCurrentState();
                System.out.println("State of the machine: " + machineState);
            }
            accepted = machineState.equals(acceptState);
            rejected = machineState.equals(rejectState);
        } while (!accepted && !rejected);
        if(accepted)
            System.out.println("\nInput accepted! Machine ran in " + numSteps + " steps.");
        else
            System.out.println("\nInput rejected! Machine ran in " + numSteps + " steps.");
       
    }
    
    /**
     * Method that finds the rule that is to be applied.
     * @param machineState current state of the machine.
     * @param c symbol in square that head currently point on.
     * @return Rule of the transition function, which is based on
     * current state of the machine and current symbol.
     */
    private Rule findRule(String machineState, char c){
        for(Rule r : rules){
            if(r.domain.state.equals(machineState) && r.domain.symbol == c)
                return r;
        }
        return null;
    }
   
    
}
