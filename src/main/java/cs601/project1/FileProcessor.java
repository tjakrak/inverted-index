package cs601.project1;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * This class main responsibility is to parse json file to an object and store
 * those object in AmazonDatabase.
 */
public class FileProcessor {
    private String filename;
    private String fileType;
    private AmazonDatabase<GsonObject> database;

    /**
     * Constructor of FileProcessor
     * @param filename the name of the input file
     * @param fileType the type of the file (Review or QA)
     */
    public FileProcessor(String filename, String fileType) {
        this.filename = filename;
        this.fileType = fileType;
        this.database = new AmazonDatabase<>();
    }

    /**
     * Getter for filename
     * @return the name of the file
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for file name
     * @param filename the name of the file that we are trying to read
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for file type (QA/REVIEW)
     * @return the type of the file
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter for file type
     * @param fileType the name of the file that we are trying to read
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * A method that help the program to read a file line by line, parse to gson and store it to a database
     */
    public void readFileAndBuildDatabase() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.ISO_8859_1))) {
            String line;
            while((line = br.readLine()) != null) {
                parseGson(line);
            }
        } catch (IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * A helper method to parse gson and store it to a database
     * @param line a string of sentence
     */
    private void parseGson(String line) {
        Gson gson = new Gson();
        try {
            if (fileType.equals("review")) {
                Review review = gson.fromJson(line, Review.class);
                database.add(review);
            } else if (fileType.equals("qa")) {
                QA qa = gson.fromJson(line, QA.class);
                database.add(qa);
            }
        } catch (com.google.gson.JsonSyntaxException ignore) {
            System.out.println("JsonSyntaxException - Skip a line");
        }
    }

    /**
     * This method handle a case where the user searching by partial term
     */
    public void partialTerm(String term) {
        database.getObjectByPartialTerm(term);
        database.printList();
    }

    /**
     * This method handle a case where the user searching using full term
     */
    public void fullTerm(String term) {
        database.getObjectByTerm(term);
        database.printList();
    }

    /**
     * This method handle a case where the user searching by Asin
     */
    public void findAsin(String asin) {
        database.findAsin(asin);
        database.printList();
    }

}
