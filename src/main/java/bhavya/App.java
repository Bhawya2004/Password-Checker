package bhavya;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String pwd = sc.nextLine();
        sc.close();
        int score = 0;
        if (pwd.length() >= 8)
            score++;
        if (pwd.length() >= 12)
            score++;
        if (pwd.matches(".*[A-Z].*") && pwd.matches(".*[a-z].*"))
            score++;
        if (pwd.matches(".*\\d.*"))
            score++;
        if (pwd.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*"))
            score++;
        String strength = score <= 2 ? "WEAK" : score == 3 ? "MEDIUM" : "STRONG";
        System.out.println("Password strength: " + strength);
        System.out.println("Score: " + score + "/5");
    }
}
