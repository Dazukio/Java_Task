package second;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CurrencyConverter {
    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();

    public static void main(String[] args) {
        initializeExchangeRates();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CURRENCY CONVERTER ===");
        System.out.println("Available currencies: USD, EUR, RUB, JPY, CNY");

        boolean continueConversion = true;

        while (continueConversion) {
            String sourceCurrency = getCurrencyInput(scanner, "Enter source currency (USD/EUR/RUB/JPY/CNY): ");
            double amount = getAmountInput(scanner);

            convertAndDisplay(amount, sourceCurrency);

            System.out.print("\nDo you want to convert another amount? (Y/N): ");
            String answer = scanner.nextLine().trim().toUpperCase();
            if (!answer.equals("Y")) {
                continueConversion = false;
            }
        }

        scanner.close();
    }

    private static void initializeExchangeRates() {
        EXCHANGE_RATES.put("USD", 1.0);
        EXCHANGE_RATES.put("EUR", 0.92);
        EXCHANGE_RATES.put("RUB", 92.50);
        EXCHANGE_RATES.put("JPY", 149.30);
        EXCHANGE_RATES.put("CNY", 7.18);
    }

    private static String getCurrencyInput(Scanner scanner, String message) {
        String currency;
        while (true) {
            System.out.print(message);
            currency = scanner.nextLine().trim().toUpperCase();

            if (EXCHANGE_RATES.containsKey(currency)) {
                break;
            } else {
                System.out.println("Error: Currency '" + currency + "' not supported.");
                System.out.println("Available: USD, EUR, RUB, JPY, CNY");
            }
        }
        return currency;
    }

    private static double getAmountInput(Scanner scanner) {
        double amount;
        while (true) {
            System.out.print("Enter amount to convert: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println("Error: Amount cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
        return amount;
    }

    private static void convertAndDisplay(double amount, String sourceCurrency) {
        System.out.println("\n=== CONVERSION RESULTS ===");
        System.out.printf("%-8s %12s%n", "Currency", "Amount");
        System.out.println("----------------------");

        double amountInUSD = amount / EXCHANGE_RATES.get(sourceCurrency);

        for (String targetCurrency : EXCHANGE_RATES.keySet()) {
            double convertedAmount = amountInUSD * EXCHANGE_RATES.get(targetCurrency);
            if (targetCurrency.equals(sourceCurrency)) {
                System.out.printf("%-8s %12.2f (original)%n", targetCurrency, convertedAmount);
            } else {
                System.out.printf("%-8s %12.2f%n", targetCurrency, convertedAmount);
            }
        }
    }
}
