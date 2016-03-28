package universalturingmachine;

/**
 * Class that represent one rule of the transition function of
 * the Turing Machine.
 * @author Vukasin Karadzic, vukasin.karadzic@gmail.com
 */
public class Rule implements Comparable<Rule>{
    
    SymbolStatePair domain, codomain;
    Action move;
    
    Rule(SymbolStatePair domain, SymbolStatePair codomain, Action move) {
        this.domain = domain;
        this.codomain = codomain;
        this.move = move;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        
        if (this == obj)
            return true;
        
        if (getClass() != obj.getClass())
            return false;
        
        Rule nr = (Rule) obj;
        
        if (move != null && domain != null && codomain != null
                &&  nr.move != null && nr.domain != null && nr.codomain != null)
            if (move == nr.move && domain.equals(nr.domain) && codomain.equals(nr.codomain))
                return true;
        
        return false;
    }
    
    @Override
    public String toString() { 
        return "(" + domain.state + "," + domain.symbol + "," + codomain.state + "," + codomain.symbol + "," + move + ")";
    }

    @Override
    public int compareTo(Rule o) {
        if (o == null)
            return 0;
        else if (domain.state.compareTo(o.domain.state) == 0)
            return (int) domain.symbol - (int) o.domain.symbol;
        else
            return domain.state.compareTo(o.domain.state);
           
    }

}

/**
 * Turing machine is described with transition function
 * delta: setOfStates x alphabet x {L,R} ---> setOfStates x alphabet.
 * Rules are determined with this transition function.
 * This class represents one pair (x,y) such that x is a state, and y is a
 * letter from the tape alphabet.
 * @author vukasin
 */
class SymbolStatePair {
    
    String state;
    char symbol;
    
    public SymbolStatePair(String state, char symbol) {
        this.state = state;
        this.symbol = symbol;
    }
    
    @Override
    public int hashCode(){
        if (state != null)
            return symbol * state.hashCode() * 31;
        else 
            return 0;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        
        if (this == obj)
            return true;
        
        if (getClass() != obj.getClass())
            return false;
        
        SymbolStatePair ssp = (SymbolStatePair) obj;
        
        if (state != null && ssp.state != null)
            if (state.equals(ssp.state) && symbol == symbol)
                return true;
        
        return false;
    }
    
    
}

enum Action{
    L, R;
}