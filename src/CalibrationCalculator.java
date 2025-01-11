import java.io.*;
import java.util.regex.*;

public class CalibrationCalculator {

    public static void main(String[] args) {
        String fileName = "src/document.txt";
        int totalSum = 0;

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Fichier introuvable : " + fileName);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println("Ligne lue : " + line);

                String digits = extractDigits(line);
                System.out.println("Chiffres extraits : " + digits);

                if (digits.length() >= 2) {
                    int calibrationValue = Integer.parseInt("" + digits.charAt(0) + digits.charAt(digits.length() - 1));
                    System.out.println("Valeur d'étalonnage calculée : " + calibrationValue);

                    totalSum += calibrationValue;
                }
            }

            System.out.println("La somme totale des valeurs d'étalonnage est : " + totalSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractDigits(String line) {
        StringBuilder digits = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            digits.append(matcher.group());
        }

        return digits.toString();
    }
}
