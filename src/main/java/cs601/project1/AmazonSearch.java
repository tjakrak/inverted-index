package cs601.project1;

import java.util.Scanner;

/**
 * This class is the driver of this search program. It is mainly handling the user interface.
 * It tokenize the user commands and act accordingly depending on the input.
 */
public class AmazonSearch {

    String[] args;
    FileProcessor reviewFile;
    FileProcessor qaFile;

    /**
     * Default Constructor of AmazonDatabase
     */
    public AmazonSearch(String[] args) {
        this.args = args;
    }

    /**
     * A driver that run the program:
     * Getting the filename and calling FileProcessor class to parse and build Database out of it.
     * Execute the UI to enable the user to input some commands
     */
    public void runProgram () {
        // check if there is some missing commands
        // format: -reviews filename1 -qa filename2
        if (args.length != 4 || !args[0].equals("-reviews") || !args[2].equals("-qa")) {
            System.out.println("Missing input or syntax error");
            System.exit(0);
        }

        // read review file
        reviewFile = new FileProcessor(args[1], "review");
        reviewFile.readFileAndBuildDatabase();

        // read qa file
        qaFile = new FileProcessor(args[3], "qa");
        qaFile.readFileAndBuildDatabase();

        executeUI();
    }

    /**
     * A method containing UI which check and help execute the command given by the user
     */
    private void executeUI () {
        Scanner scanner = new Scanner(System.in);
        printCommandList();

        // prompt for user input
        System.out.print("> ");
        String userInput = scanner.nextLine();

        // keep prompting for user input until the user type "quit"
        while (!userInput.equalsIgnoreCase("quit")) {
            String[] command = userInput.split("\\s+");

            // check if the user put the right command and call the right function based on the user input
            if (command.length == 2) {
                if (command[0].equalsIgnoreCase("find")) {
                    reviewFile.findAsin(command[1]);
                    qaFile.findAsin(command[1]);
                } else if (command[0].equalsIgnoreCase("reviewsearch")) {
                    reviewFile.fullTerm(command[1]);
                } else if (command[0].equalsIgnoreCase("qasearch")) {
                    qaFile.fullTerm(command[1]);
                } else if (command[0].equalsIgnoreCase("reviewpartialsearch")) {
                    reviewFile.partialTerm(command[1]);
                } else if (command[0].equalsIgnoreCase("qapartialsearch")) {
                    qaFile.partialTerm(command[1]);
                }
            } else if (command.length == 1 && command[0].equalsIgnoreCase("h")) {
                printCommandList();
            } else {
                System.out.println("not a valid command, please try again");
            }
            System.out.print("> ");
            userInput = scanner.nextLine();
        }
    }

    /**
     * A helper method showing the list of Commands that are supported by this program
     */
    private void printCommandList() {
        System.out.println("---------------------------------COMMAND LIST---------------------------------");
        System.out.println("h                               - to show command list");
        System.out.println("find <asin>                     - to find all the review and qa by asin");
        System.out.println("reviewsearch <term>             - to find all the review by term");
        System.out.println("qasearch <term>                 - to find all the qa by term");
        System.out.println("reviewpartialsearch <term>      - to find all the review that contains the term");
        System.out.println("qapartialsearch <term>          - to find all the qa that contains the term");
    }

    /**
     * The main method which get the user arguments and start the program
     */
    public static void main(String[] args) {
        AmazonSearch search = new AmazonSearch(args);
        search.runProgram();
    }

}
