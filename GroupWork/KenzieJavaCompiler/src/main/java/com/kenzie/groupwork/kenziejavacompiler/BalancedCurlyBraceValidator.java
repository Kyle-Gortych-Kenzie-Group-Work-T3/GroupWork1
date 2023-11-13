package com.kenzie.groupwork.kenziejavacompiler;

import java.util.List;
import java.util.Stack;

/**
 * Compiler check that ensures the curly braces in a file are evenly matched. The file must contain an even number of
 * opening and closing curly braces. This check does not perform any validation that the curly braces are in legal
 * locations in the file. [NOTE] Your implementation does not need to handle escaped curly braces within strings
 * literals.
 */
public class BalancedCurlyBraceValidator {

    private static final char OPEN = '{';
    private static final char CLOSE = '}';
    private boolean debug = false;

    /**
     * Validates that the curly braces in the list of provided file characters are balanced.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean check(List<Character> fileCharacters) {
        // TODO: complete this method

        Stack<Character> stack = new Stack<>();

        for (Character character : fileCharacters) {

            if (character == OPEN) {

                stack.push(OPEN); // Push an opening brace onto the stack

            } else if (character == CLOSE) {

                if (stack.isEmpty()) {
                    // If there is no matching opening brace, return false
                    if (debug) {
                        System.out.println("Unmatched closing brace found.");
                    }
                    return false;
                } else {
                    // Pop the matching opening brace from the stack
                    stack.pop();
                }
                
            }
    }

        // If the stack is empty at the end, it means all braces were matched
        // If the stack is not empty, there were unmatched opening braces
        if (stack.isEmpty()) {
            return true;
        } else {
            if (debug) {
                System.out.println("Unmatched opening braces found.");
            }
            return false;
        }
    }

    /**
     * Does the above and prints out debugging information. This can be combined into one method, but separating these
     * out so we can see both the basic solution and the extension solution.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean checkExtension(List<Character> fileCharacters) {
        // TODO: complete this method

        Stack<Character> stack = new Stack<>();

        int maxUnbalanced = 0;
        int currentUnbalanced = 0;

        for (int i = 0; i < fileCharacters.size(); i++) {
            Character character = fileCharacters.get(i);

            if (character == OPEN) {
                stack.push(OPEN); // Push an opening brace onto the stack
                currentUnbalanced++;
            } else if (character == CLOSE) {
                if (stack.isEmpty()) {
                    // If there is no matching opening brace, return false
                    if (debug) {
                        System.out.println("Unmatched closing brace found at line " + (i + 1));
                    }
                    return false;
                } else {
                    // Pop the matching opening brace from the stack
                    stack.pop();
                    currentUnbalanced--;

                    if (currentUnbalanced < 0) {
                        if (debug) {
                            System.out.println("Unmatched opening brace found at line " + (i + 1));
                        }
                        currentUnbalanced = 0;
                    }

                    maxUnbalanced = Math.max(maxUnbalanced, currentUnbalanced);
                }
            }
        }

        if (debug) {
            System.out.println("Maximum number of unbalanced braces encountered: " + maxUnbalanced);
        }

        return stack.isEmpty();
    }

    /**
     * Use this to enable or disable additional debug messages.
     * @param debug the value to set the debug variable
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}

