package uk.ac.lancaster.scc.data.lab6;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public class TermVectorSpaceModel {

    public void analyseUsers() {

        // read in the text from the Twitter Haiti file
        // the file path needs to point to the location of the the downloaded file.
        String filePath = "";

        // map the user to his text
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = reader.readLine()) != null) {

                // read in your data
                // you need to check the file to see how the data is split up (i.e. what the delimiter is betweeen elements)
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // build the term index

        // construct the term vector object for each user

        // Exercise 1: Which user is most similar to user 41060366 based on binary weighting and cosine similarity, and what is their similarity score?

        // Exercise 2: Which user is *least* similar to user 41060366 based on frequency weighting and cosine similarity, and what is their similarity score?

        // Exercise 3: Which user is *least* similar to user 41060366 based on TF-IDF weighting and cosine similarity, and what is their similarity score?

        // Exercise 4. Which term is most commonly mentioned within users' Tweets and how many times has it been mentioned? (5 marks)

    }

    public static void main(String[] args) {
        TermVectorSpaceModel model = new TermVectorSpaceModel();
        model.analyseUsers();
    }

}
