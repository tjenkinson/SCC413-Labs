package uk.ac.lancaster.scc.data.lab4.datasets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 * Date / Time : 06/02/2014 / 13:39
 */
public class DatasetLoader {


    public DatasetLoader() {
    }

    /*
    * Constructs the training dataset
    */
    public Dataset buildTrainingDataset(String pathToTrainingDataFile) {
        Dataset dataset = new Dataset();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // get the reviews that were processed before the cutoff
            HashSet<Review> reviews = new HashSet<Review>();
            HashSet<String> users = new HashSet<String>();
            HashSet<String> items = new HashSet<String>();

            // read from the platform's training data file
            BufferedReader reader = new BufferedReader(new FileReader(pathToTrainingDataFile));
            String line = null;
            while ((line = reader.readLine()) != null) {

                // tokenise the line by the tab delimiter
                String[] toks = line.split("\t");
                if(toks.length == 4) {
                    String userid = toks[0];
                    String itemid = toks[1];
                    Date time = sdf.parse(toks[2]);
                    double rating = Double.parseDouble(toks[3]);

                    // construct the review object and save it
                    Review review = new Review(userid,itemid,time,rating);
                    reviews.add(review);
                    users.add(userid);
                    items.add(itemid);
                }
            }
            reader.close();

            // if everything went ok then build the dataset
            dataset.setSplit("training");
            dataset.setReviews(reviews);
            dataset.setUsers(users);
            dataset.setItems(items);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }

    /*
     * Constructs the test dataset
     */
    public Dataset buildTestDataset(String pathToTestDataFile) {
        Dataset dataset = new Dataset();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // get the reviews that were processed before the cutoff
            HashSet<Review> reviews = new HashSet<Review>();
            HashSet<String> users = new HashSet<String>();
            HashSet<String> items = new HashSet<String>();

            // read from the platform's training data file
            BufferedReader reader = new BufferedReader(new FileReader(pathToTestDataFile));
            String line = null;
            while ((line = reader.readLine()) != null) {

                // tokenise the line by the tab delimiter
                String[] toks = line.split("\t");
                if(toks.length == 4) {
                    String userid = toks[0];
                    String itemid = toks[1];
                    Date time = sdf.parse(toks[2]);
                    double rating = Double.parseDouble(toks[3]);

                    // construct the review object and save it
                    Review review = new Review(userid,itemid,time,rating);
                    reviews.add(review);
                    users.add(userid);
                    items.add(itemid);
                }
            }
            reader.close();

            // if everything went ok then build the dataset
            dataset.setSplit("test");
            dataset.setReviews(reviews);
            dataset.setUsers(users);
            dataset.setItems(items);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }
}
