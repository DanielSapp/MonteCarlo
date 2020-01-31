import java.security.SecureRandom;
import java.util.Scanner;
public class MainAlgorithm {
    private static SecureRandom sr = new SecureRandom();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the house edge as a decimal to 4 digits");
        double edge = scanner.nextDouble();
        System.out.println("Enter the number of test cases to run");
        int cases = scanner.nextInt();
        double[] results = new double[cases];
        System.out.println("Enter the number of rolls to simulate");
        int rollsPerCase = scanner.nextInt();
        System.out.println("Enter the starting balance of the player as xx.yy");
        double balance = scanner.nextDouble();
        System.out.println("Enter the amount to waiger on each roll");
        double bet = scanner.nextDouble();
        for (int i = 0; i < cases; i++) {
            double balanceInCase = balance;
            for (int j = 0; j < rollsPerCase; j++) {
                if (balanceInCase < bet) {
                    break;
                }
                if (rollDice(edge)) {
                    balanceInCase += bet;
                } else {
                    balanceInCase -= bet;
                }
            }
            //For each roll within the case
            results[i] = balanceInCase;
        }
        printResults(results, bet);
    }
    private static boolean rollDice(double houseEdge) {
        if (sr.nextInt(1000) <= ((1000-(houseEdge*1000))/2)-1) {
            return true;
        } else {
            return false;
        }
    }
    private static void printResults(double[] results, double bet) {
        int bust = 0;
        double sum = 0;
        for (double d : results) {
            sum += d;
            if (d < bet) {
                bust++;
            }
        }
        System.out.println("Your average result was being left with " + sum/results.length + "\nYou went bust in " + bust + " cases out of " + results.length);
    }
}
