package universalturingmachine;

/**
 *
 * @author Vukasin Karadzic, vukasin.karadzic@gmail.com
 */
interface TMTapeInterface {

    /**
     * Read what symbol is in a square that the head points on.
     * @return Symbol in node that the head points on.
     */
    public char head();
    
    /**
     * Writes a symbol in the square that the head points on.
     * @param c Symbol that is to be written in the node that the
     * head points on. 
     */
    public void write(char c);
    
    /**
     * Method that simulates the moving of the Turing Machine head
     * on the tape.
     * @param action 
     */
    public void moveHead(Action action);
    
    /**
     * Method that prints current state of the tape. It prints 10 symbols
     * that are found left and 10 symbols that are found right of the head.
     */
    public void printCurrentState();
    
  
}
