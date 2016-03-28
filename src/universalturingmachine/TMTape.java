package universalturingmachine;

/**
 *
 * @author Vukasin Karadzic, vukasin.karadzic@gmail.com
 */
public class TMTape implements TMTapeInterface{
    
    private static class DLLNode {
        
        char symbol;
        DLLNode prev, next;
        
        DLLNode(char symbol){
            this.symbol = symbol;
            prev = null;
            next = null;
        }
        
    }

    DLLNode tmHead;
    DLLNode first, last;
    char blank;
    
    TMTape(String input, char blank) {
        this.blank = blank;
        if (input.isEmpty()) {
            first = new DLLNode(blank);
            last = first;
            tmHead = first;
        }
        else {
            first = new DLLNode(input.charAt(0));
            last = first;
            tmHead = first;
            DLLNode curr = first;
            for (int i = 1; i < input.length(); i++) {
               DLLNode newNode = new DLLNode(input.charAt(i));
               curr.next = newNode;
               newNode.prev = curr;
               last = newNode;
               curr = newNode;
               newNode = null;
            }
            curr = null;
        }
    }
    
    @Override
    public char head() {
        return tmHead.symbol;
    }
    
    @Override
    public void write(char c){
        tmHead.symbol = c;
    }
    
    @Override
    public void moveHead(Action action) {
        if (action == Action.L) {
            if (tmHead == first) {
                DLLNode newFirst = new DLLNode(blank);
                newFirst.next = first;
                first.prev = newFirst;
                first = newFirst;
                tmHead = newFirst;
            }
            else {
                tmHead = tmHead.prev;
            }
        }
        else {
            if (tmHead == last) {
               DLLNode newLast = new DLLNode(blank);
               newLast.prev = last;
               last.next = newLast;
               last = newLast;
               tmHead = newLast;
            }
            else {
                tmHead = tmHead.next;
            }
        }
    }
    
    @Override
    public void printCurrentState(){
        DLLNode printHead = tmHead;        
        System.out.println(" _____________________________________________________________\\*/_____________________________________________________________");
        System.out.println("|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |");
        int i = 0, k = 0;
        while (i < 10) {
            if (printHead.prev != null)
                printHead = printHead.prev;
            else {
                k = 10 - i;
                break;
            }
            i++;
        }
        for(i = 0; i < k; i++)
            System.out.print("|  " + blank + "  ");
        i = k;
        while (printHead != null && i < 21) {
                System.out.print("|  " + printHead.symbol + "  ");
                printHead = printHead.next;
            i++;
        }
        while (i < 21) {
            System.out.print("|  " + blank + "  ");
            i++;
        }
        System.out.println("|");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
    }
    
    
}
