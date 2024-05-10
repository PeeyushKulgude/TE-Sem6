import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleRepairShopChatbot {

    private static final Map<String, String> responses = new HashMap<>();

    static {
        responses.put("greeting", "Hello! Welcome to our vehicle repair shop. How can I assist you today?");
        responses.put("farewell", "Thank you for choosing our vehicle repair services. Have a great day!");
        responses.put("help", "Sure, I'm here to help. What issues are you facing with your vehicle?");
        responses.put("flat_tire", "If you have a flat tire, we can patch it up or replace it for you. Just bring your vehicle to our shop, and we'll take care of it.");
        responses.put("engine_issue", "Engine problems can be complicated. Bring your vehicle to our shop, and our technicians will diagnose and fix the issue for you.");
        responses.put("battery_problem", "If your vehicle's battery is dead, we can jump-start it or replace the battery if needed. Visit our shop, and we'll get you back on the road.");
        responses.put("brake_failure", "Brake issues are serious. It's best not to drive your vehicle. Tow it to our shop immediately, and we'll inspect and repair the brakes for you.");
        responses.put("default", "I apologize, but I couldn't understand your request.");
    }

    private static class Rule {
        private final Pattern pattern;
        private final String responseKey;

        public Rule(String regex, String responseKey) {
            this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            this.responseKey = responseKey;
        }

        public boolean matches(String input) {
            Matcher matcher = pattern.matcher(input.toLowerCase());
            return matcher.find();
        }

        public String getResponseKey() {
            return responseKey;
        }
    }

    private static class ExpertSystem {
        private final Rule[] rules;

        public ExpertSystem(Rule[] rules) {
            this.rules = rules;
        }

        public String respondToInquiry(String inquiry) {
            for (Rule rule : rules) {
                if (rule.matches(inquiry)) {
                    return responses.get(rule.getResponseKey());
                }
            }
            return responses.get("default");
        }
    }

    public static void main(String[] args) {
        Rule[] rules = {
                new Rule("\\b(?:hello|hi)\\b", "greeting"),
                new Rule("\\b(?:goodbye|bye)\\b", "farewell"),
                new Rule("\\b(?:help|support)\\b", "help"),
                new Rule("\\b(?:flat|tire)\\b", "flat_tire"),
                new Rule("\\b(?:engine|problem|issue)\\b", "engine_issue"),
                new Rule("\\b(?:battery|charge)\\b", "battery_problem"),
                new Rule("\\b(?:brake|failure)\\b", "brake_failure")
        };

        ExpertSystem chatbot = new ExpertSystem(rules);

        System.out.println("Welcome to the Vehicle Repair Shop Chatbot!");
        System.out.println("Type 'exit' to end the conversation.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Customer: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            String botResponse = chatbot.respondToInquiry(userInput);
            System.out.println("Chatbot: " + botResponse);
        }

        System.out.println("Thank you for using the Vehicle Repair Shop Chatbot. Goodbye!");
        scanner.close();
    }
}
