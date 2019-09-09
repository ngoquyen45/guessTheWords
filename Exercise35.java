/*
 * Hannoi university of science and technology
 * 9/9/2019
 */
package ngoquyen.java.ydanieliang.chapter7exercise;

import java.util.Scanner;

/**
 *
 * @author NgoQuyen
 */
public class Exercise35 {
    public static void main(String[] args) {
        runGame();
    }
    private static final String ARROW = "-------> ";
    public  static void runGame(){
        Scanner input = new Scanner(System.in);
        char hasContinue;
        do {
            // My program
            String temp;
            boolean valid;
            
            String source = randomString();
                int n = source.length();
            String guess  = new String(new char[n]).replace('\0', '*');
            char charGuess;
            int[] countMissed = new int[1];
            do {
                do {
                    // Get char
                    System.out.print("(Guess) Enter a letter in word " + guess + " > ");
                    temp = input.next();
                    charGuess = temp.charAt(0);
                    valid = temp.length() == 1 && 
                            'a' <= charGuess && charGuess <= 'z' ||
                            'A' <= charGuess && charGuess <= 'Z' || 
                            charGuess == '_';
                    if (!valid) System.out.println(ARROW + "Entered wrong !");
                } while (!valid);
                
                guess = processString(source, guess, charGuess, countMissed);
            } while (!guess.equals(source));
            
            System.out.println(
                    "The word is " + source + ". " +
                    "You missed " + countMissed[0] + " time");
            
            do {
                System.out.print("Do you want to guess another word? Enter y or n > ");
                temp = input.next();
                hasContinue = temp.charAt(0);
                valid = temp.length() == 1 && hasContinue == 'y' || hasContinue == 'n';
                if (!valid) System.out.println(ARROW + "Entered wrong !");
            } while (!valid);
            
        } while (hasContinue == 'y');
        System.out.println("Say Goodbye!");
    }
    
    public static String randomString() {
        String[] words = {
            "Hacker_is_here", 
            "Programmer", 
            "Information_Technology",
            "Harvard_University",
            "Doreamon",
            "Computer_Science"
        };
        return words[(int) (Math.random() * (words.length))];
    }
    
    public  static String processString(String source, String guessed, char charGuess, int[] countMissed) {
        int l = source.length();
        boolean isCorrectGuess = false;
        boolean isCorrected = false;
        
        for (int i = 0; i < l; i++) {
            if (guessed.charAt(i) == '*') {
                // if guess is correct then edit data
                if (source.charAt(i) == charGuess) {
                    isCorrectGuess = true;
                    guessed = guessed.substring(0, i) +
                            charGuess +
                            guessed.substring(i+1);
                }
            }
            else { // Đã đoán rồi
                // if guess is corrected then print and exit
                if (source.charAt(i) == charGuess) {
                    isCorrected = true;
                    System.out.println(ARROW + charGuess + " is already in the word");
                    break;
                }
            }
        }
        
        // guess is incorrcet
        if (!isCorrected && !isCorrectGuess) {
            System.out.println(ARROW + charGuess + " is not in the word");
            countMissed[0]++;
        }
            
        
        return guessed;
    }
}

