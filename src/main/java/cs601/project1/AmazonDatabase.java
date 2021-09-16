package cs601.project1;

import java.util.*;

/**
 * This class act as a database. It tokenize the data that matters and add it to the inverted index.
 * This class itself contain a hashmap that map the document ID to the object
 */
public class AmazonDatabase<T extends GsonObject> {

    private int documentID;
    private InvertedIndex indexOfTerms;
    private InvertedIndex indexOfAsins;
    private HashMap<Integer, T> objectByDocID;
    private List<T> listOfReturnObject;

    /**
     * Default Constructor of AmazonDatabase
     */
    public AmazonDatabase() {
        this.documentID = 0;
        this.indexOfTerms = new InvertedIndex();
        this.objectByDocID = new HashMap<>();
        this.indexOfAsins = new InvertedIndex();
    }

    /**
     * Tokenize and build database with InvertedINdex
     * @param gsonObject an object that get created when we parse a json file
     */
    public void add(T gsonObject) {
        documentID += 1;
        objectByDocID.put(documentID, gsonObject);
        indexOfAsins.add(gsonObject.getAsin(), documentID);
        String line = gsonObject.getDataSet();

        if (line != null) {
            // tokenize the string line
            String[] terms = line.split("\\s+");
            // getting all the terms to be stored in the database
            for (String term : terms) {
                String stdTerm = term.toLowerCase().replaceAll("[^a-z0-9]", "");
                indexOfTerms.add(stdTerm, documentID);
            }
        }
    }

    /**
     * Sort and Print out the result to a file
     * @param term word that we would like to search in the database
     */
    public List<T> getObjectByTerm(String term) {
        listOfReturnObject = new ArrayList<>();
        List<Integer> listOfDocID = indexOfTerms.getIDByTerm(term, true);

        for (Integer i : listOfDocID) {
            listOfReturnObject.add(objectByDocID.get(i));
        }
        return listOfReturnObject;
    }

    /**
     * Sort and Print out the result to a file
     * @param term incomplete word that we would like to search in the database
     */
    public List<T> getObjectByPartialTerm(String term) {
        listOfReturnObject = new ArrayList<>();
        Set<Integer> listOfDocID = indexOfTerms.getIDByPartialTerm(term);
        for (Integer i : listOfDocID) {
            listOfReturnObject.add(objectByDocID.get(i));
        }
        return listOfReturnObject;
    }

    /**
     * A method that call a function within Inverted index to get the documentID
     * based on the asin.
     *
     * @param asin amazon standard identification number for a product
     * @return a list of document id
     */
    public List<T> findAsin(String asin) {
        this.listOfReturnObject = new ArrayList<>();
        List<Integer> listOfDocID = indexOfAsins.getIDByTerm(asin, false);
        for (Integer i : listOfDocID) {
            listOfReturnObject.add(objectByDocID.get(i));
        }
        return listOfReturnObject;
    }

    @Override
    public String toString() {
        return "Obj:" + listOfReturnObject;
    }


    /**
     * A method that print the output
     */
    public void printList() {
        int ct = 0;

        if (listOfReturnObject.isEmpty()) {
            System.out.println("No search result");
        }

        for (T o : listOfReturnObject) {
            System.out.println(o.toString());
            ct++;
        }
        System.out.println("total search result = " + ct);
    }
}
