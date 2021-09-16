package cs601.project1;

import java.util.*;

/**
 * This class is an inverted index data structure
 * it handles add, getDocID and sorting
 */
public class InvertedIndex {
    private Map<String, Map<Integer, Integer>> documentIDByTerm;
    private List<Integer> listOfDocID;
    private List<Integer> listOfFreq;

    /**
     * Default Constructor of AmazonDatabase
     */
    public InvertedIndex() {
        this.documentIDByTerm = new HashMap<>();
    }

    /**
     * Adding term and docID to the InvertedIndex
     * @param term word that are going to get stored in the InvertedIndex
     * @param docID unique key that represent the document where the term is
     */
    public void add(String term, int docID) {
        if (documentIDByTerm.containsKey(term)) {
            if (documentIDByTerm.get(term).containsKey(docID)) {
                documentIDByTerm.get(term).put(docID, documentIDByTerm.get(term).get(docID) + 1);
            } else {
                documentIDByTerm.get(term).put(docID, 1);
            }
        } else {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(docID, 1);
            documentIDByTerm.put(term, m);
        }
    }

    /**
     * This method look the term provided in the user input in the map.
     * If it is exist, it will return the list of documents that contains the term.
     *
     * @param term word that we want to look for in the map
     * @param sort true if we want to sort the data by freq; false if we dont need to sort the data
     * @return list of document ID that associated with the term
     */
    public List<Integer> getIDByTerm(String term, Boolean sort) {
        listOfDocID = new ArrayList<>();
        listOfFreq = new ArrayList<>();

        // check if our map contains the term
        if (documentIDByTerm.containsKey(term)) {
            // iterate through the list of documentID
            for (var entry : documentIDByTerm.get(term).entrySet()) {
                listOfFreq.add(entry.getValue());
                listOfDocID.add(entry.getKey());

                // sort the array, similar to how selection sort work
                if (sort) {
                    sortByFreq();
                }
            }
            return listOfDocID;
        }

        return listOfDocID;
    }

    /**
     * This method look at partial term provided in the user input in the map.
     * If it is exist, it will return the list of documents that contains the term.
     *
     * @param partialTerm partial word that we want to look for in the map
     * @return list of document ID that associated with the partialTerm
     */
    public Set<Integer> getIDByPartialTerm(String partialTerm) {
        Set<Integer> setOfDocID = new HashSet<>();
        // iterate through all the key in the map and check if the key contains partial term
        for (var entry : documentIDByTerm.entrySet()) {
            if (entry.getKey().contains(partialTerm)) {
                // iterate through all the documentID and add it to the set to avoid duplicate
                for (var subEntry : entry.getValue().entrySet()) {
                    setOfDocID.add(subEntry.getKey());
                }
            }
        }
        return setOfDocID;
    }

    /**
     * This is a helper method to help sort the list of document ID that we obtain from hashmap
     */
    private void sortByFreq() {

        if (listOfFreq.size() > 1) {
            int indexOfLastNum = listOfFreq.size() - 1;
            int pointer = indexOfLastNum;

            // iterate from right to left. The bigger number will move to the left.
            for (int i = indexOfLastNum - 1; i >= 0; i--) {
                if (listOfFreq.get(indexOfLastNum) > listOfFreq.get(i)) {
                    pointer = i;
                }
            }

            // swap the position of the frequency to be in sorted order
            int temp1 = listOfFreq.get(pointer);
            listOfFreq.set(pointer, listOfFreq.get(indexOfLastNum));
            listOfFreq.set(indexOfLastNum, temp1);

            // swap the position of the documentID to be in sorted order
            int temp2 = listOfDocID.get(pointer);
            listOfDocID.set(pointer, listOfDocID.get(indexOfLastNum));
            listOfDocID.set(indexOfLastNum, temp2);
        }
    }

    @Override
    public String toString() {
        return "listOfDocID=" + documentIDByTerm;
    }
}
