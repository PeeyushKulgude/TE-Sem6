package ExpertSystem;

import java.util.Scanner;

public class DiseasePrediction {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean askQuestion(String question) {
        System.out.print(question + ": ");
        String response = scanner.nextLine().toLowerCase().trim();
        return response.equals("y") || response.equals("yes");
    }

    public static boolean diagnoseAllergies() {
        return askQuestion("Do you experience any itching or swelling?") || askQuestion("Do you have red, watery eyes?");
    }

    public static boolean diagnoseFever() {
        return askQuestion("Do you have a temperature above 37.5°C?") || askQuestion("Do you experience chills?");
    }

    public static boolean diagnoseCold() {
        return askQuestion("Do you have a runny or stuffy nose?") || askQuestion("Are you sneezing frequently?");
    }

    public static boolean diagnoseFlu() {
        return askQuestion("Do you have body aches?")
                && askQuestion("Do you feel tired or fatigued?")
                && askQuestion("Do you have a temperature above 38°C?");
    }

    public static boolean diagnoseStrepThroat() {
        return askQuestion("Do you have a sore throat?") && askQuestion("Are your tonsils swollen?");
    }

    public static boolean diagnoseFoodPoisoning() {
        return askQuestion("Do you feel nauseous?")
                && askQuestion("Have you been vomiting?")
                && askQuestion("Do you have diarrhea?");
    }

    public static boolean diagnoseAppendicitis() {
        return askQuestion("Do you have severe abdominal pain?") && askQuestion("Have you lost your appetite?");
    }

    public static void main(String[] args) {
        System.out.println("Expert System for diagnosing ailments");

        if (diagnoseAllergies()) {
            System.out.println("You might have allergies");
        }
        if (diagnoseFever()) {
            System.out.println("You might have fever");
        }
        if (diagnoseCold()) {
            System.out.println("You might have cold");
        }
        if (diagnoseFlu()) {
            System.out.println("You might have flu");
        }
        if (diagnoseStrepThroat()) {
            System.out.println("You might have strep throat");
        }
        if (diagnoseFoodPoisoning()) {
            System.out.println("You might have food poisoning");
        }
        if (diagnoseAppendicitis()) {
            System.out.println("You might have appendicitis");
        }

        scanner.close();
    }
}
