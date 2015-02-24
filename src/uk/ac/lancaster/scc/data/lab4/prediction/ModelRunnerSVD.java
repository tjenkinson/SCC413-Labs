package uk.ac.lancaster.scc.data.lab4.prediction;

import uk.ac.lancaster.scc.data.lab4.datasets.Dataset;
import uk.ac.lancaster.scc.data.lab4.datasets.DatasetLoader;
import uk.ac.lancaster.scc.data.lab4.datasets.Review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public class ModelRunnerSVD {

    Dataset training;
    Dataset test;

    public ModelRunnerSVD() {
    }

    /*
     * This method loads the datasets (training and test) needed to train and evaluate the SVD Model
     */
    public void loadDatasets() {
        System.out.println("\n-Loading datasets");

        // Initialise the dataset loader object
        DatasetLoader loader = new DatasetLoader();

        // point to the .tsv file containing the training reviews
        String pathToTrainingDataFile = ""; // you need to fill this in once you have downloaded the file
        this.training = loader.buildTrainingDataset(pathToTrainingDataFile);

        // point to the .tsv file containing the test reviews
        String pathToTestDataFile = "";
        this.test = loader.buildTestDataset(pathToTestDataFile);
    }


    /*
     * Shuffles and iterates over the training dataset instances to train the model
     * Quits either after the maximum number of epochs have run, or when the model has converged
     */
    public PredictionModel trainModel(Dataset training, int maxEpochs, double epsilon, double lambda, double eta, int factors) {
        System.out.println("\n-Training the Model");


        // Initialise the SVD model with the following settings:
        PredictionModel model = new SVDModel(training, maxEpochs, epsilon, lambda, eta, factors);

        // set the epoch count
        int epochCount = 1;
        while(epochCount <= maxEpochs) {

            // randomly shuffle the reviews within the dataset
            HashSet<Review> reviews =  training.getReviews();
            ArrayList<Review> listOfReviews = new ArrayList<Review>();
            for (Review review : reviews) {
                listOfReviews.add(review);
            }
            Collections.shuffle(listOfReviews);

            // set the initial review index
            int reviewIndex = 1;
            // go through the set of training reviews
            for (Review review : listOfReviews) {

                // get the prediction error with the model
                double error = model.apply(review);
                // update the model based on the prediction error
                if(error != 0) {
                    model.update(review,reviewIndex,error);
                }

                // update the review index
                reviewIndex++;
            }
            // update the epoch count
            epochCount++;

            // check if the model has converged
            if(model.convergenceCheck()) {
                break;
            }
        }

        // write model diagnostics
        String pathToDiagnosticsFileDir = "";   // you need to fill this in with the directory where the diagnostics file should be written to (make sure it ends in a /)
        model.writeDiagnostics(pathToDiagnosticsFileDir);

        return model;
    }

    /*
     * Evaluates the model by applying it to the given dataset
     */
    public double evaluateModel(Dataset test, PredictionModel model) {
        // work out the root mean square error
        // work out the mean square error
        double avgError = 0;
        double tally = 0;
        for (Review review : test.getReviews()) {
            double error = model.apply(review);
            if(error != 0) {
                avgError += error * error;
                tally++;
            }
        }
        if(avgError != 0) {
            avgError /= tally;
            avgError = Math.sqrt(avgError);
        }

        return avgError;
    }


    public static void main(String[] args) {

        // 1. Initialise the model runner class
        ModelRunnerSVD runnerSVD = new ModelRunnerSVD();

        // 2. Load the datasets to be used for the experiments
        runnerSVD.loadDatasets();

        // 3. Set up some initial values and train the model
        double eta = 0.005;
        double lambda = 0.005;
        int factors = 20;
        int maxEpochs = 500;
        double epsilon = 0.000000001;

        // train the model on the training dataset
        PredictionModel model = runnerSVD.trainModel(runnerSVD.training, maxEpochs, epsilon, lambda, eta, factors);

        // 4. Evaluate the learnt model on the test dataset
        double error = runnerSVD.evaluateModel(runnerSVD.test, model);
        System.out.println("Error = " + error);

    }

}
