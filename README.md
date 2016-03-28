This is a program that simulates the Universal Turing Machine.
I based my implementation of Turing Machine on the definition of Turing Machine in book "Introduction to the Theory of Computation" by Michael Sipser. There is a picture of the definition on the repository.

Input format for describing the transition function is:

(current state of the machine, current symbol, next state, next symbol, action), 
where action is either L (machine head moves one square to the left), or R (machine head moves one square to the right).

Example of a rule:
(q1,a,q2,b,R) - If the machine is in state q1 and reads symbol a, go to the state q2, write symbol b, and move one square to the right.