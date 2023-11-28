import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string with variables (use '|' to separate): ");
        String input = scanner.nextLine();

        String[] parts = input.split("\\|");
        if (parts.length != 2) {
            System.out.println("Invalid input format. Please use '|' to separate bound and free variables.");
            return;
        }

        String boundVariables = parts[0].replaceAll("[^a-zA-Z]", "");
        String freeVariables = getFreeVariables(boundVariables, parts[1]);

        System.out.println("Bound variables: " + formatVariables(boundVariables));
        System.out.println("Free variables: " + formatVariables(freeVariables));
    }

    private static String getFreeVariables(String boundVariables, String rightSide) {
        Set<Character> boundSet = new HashSet<>();
        for (char c : boundVariables.toCharArray()) {
            boundSet.add(c);
        }

        StringBuilder freeVariables = new StringBuilder();
        for (char c : rightSide.toCharArray()) {
            if (Character.isLetter(c) && !boundSet.contains(c)) {
                freeVariables.append(c);
            }
        }

        return freeVariables.toString();
    }

    private static String formatVariables(String variables) {
        if (variables.isEmpty()) {
            return "None";
        }

        StringBuilder formatted = new StringBuilder();
        formatted.append(variables.charAt(0));
        for (int i = 1; i < variables.length(); i++) {
            formatted.append(", ").append(variables.charAt(i));
        }

        return formatted.toString();
    }
}
