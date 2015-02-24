package uk.ac.lancaster.scc.data.lab4.prediction;

import cern.colt.matrix.impl.SparseDoubleMatrix1D;
import cern.colt.matrix.linalg.SeqBlas;
import uk.ac.lancaster.scc.data.lab4.datasets.Dataset;
import uk.ac.lancaster.scc.data.lab4.datasets.Review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public class SVDModel implements PredictionModel {

    // cutoff parameters
    int maxEpochs;
    double epsilon;

    // hyperparameters
    double eta;
    double lambda;

    // logging parameters
    int epochs;
    TreeMap<Integer,Double> epochErrors;

    // model parameters
    double mu;
    HashMap<String,Double> itemBiases;
    HashMap<String,Double> priorItemBiases;
    HashMap<String,Double> userBiases;
    HashMap<String,Double> priorUserBiases;

    // factors vectors
    HashMap<String,SparseDoubleMatrix1D> itemLatentFactorVectors;
    HashMap<String,SparseDoubleMatrix1D> priorItemLatentFactorVectors;
    HashMap<String,SparseDoubleMatrix1D> userLatentFactorVectors;
    HashMap<String,SparseDoubleMatrix1D> priorUserLatentFactorVectors;


    public SVDModel(Dataset training, int maxEpochs, double epsilon, double lambda, double eta, int factors) {
        this.maxEpochs = maxEpochs;
        this.epsilon = epsilon;
        this.lambda = lambda;
        this.epochErrors = new TreeMap<Integer, Double>();

        // set the learning rate
        this.eta = eta;

        // initialise the biases
        itemBiases = new HashMap<String, Double>();
        priorItemBiases = new HashMap<String, Double>();
        userBiases = new HashMap<String, Double>();
        priorUserBiases = new HashMap<String, Double>();

        // initialise the latent factor vectors
        itemLatentFactorVectors = new HashMap<String, SparseDoubleMatrix1D>();
        userLatentFactorVectors = new HashMap<String, SparseDoubleMatrix1D>();

        // derive the scalar for the biases
        double scalar = 5;

        // initialise the model and set the parameters to be in line with the platform's rating scale
        mu = Math.random() * scalar;

        // item biases
        for (String item : training.getItems()) {
            itemBiases.put(item,(Math.random() * scalar));
        }
        // user biases
        for (String user : training.getUsers()) {
            userBiases.put(user,(Math.random() * scalar));
        }

        // prime the latent factor vectors
        for (String item : training.getItems()) {
            // prime the vector object with random values for each factor
            SparseDoubleMatrix1D latentVector = new SparseDoubleMatrix1D(factors);
            for (int i = 0; i < latentVector.size(); i++) {
                latentVector.setQuick(i,Math.random());
            }
            itemLatentFactorVectors.put(item,latentVector);
        }
        // user latent factors
        for (String user : training.getUsers()) {
            // prime the vector object with random values for each factor
            SparseDoubleMatrix1D latentVector = new SparseDoubleMatrix1D(factors);
            for (int i = 0; i < latentVector.size(); i++) {
                latentVector.setQuick(i,Math.random());
            }
            userLatentFactorVectors.put(user,latentVector);
        }

        // initialise the epochs
        epochs = 0;
    }

    @Override
    public void update(Review review, int reviewIndex, double error) {
        // update the model's parameters given the error

        // to do: write the update rules for the model's parameter vectors - see the update table from the lecture notes

        // this maps the errors to
        this.epochErrors.put(this.epochs,error);
    }

    @Override
    public double apply(Review review) {
        double error = 0;

        // predict based on the model performance
        // to do: predict the rating of the review given the current model, and derive the error

        return error;
    }

    @Override
    public String reportStatus() {
        return "Epochs = " + epochErrors.size();
    }

    @Override
    public boolean convergenceCheck() {
        // if the model has been run all the way through for the first time then initialise the prior vectors and return false
        if(priorItemBiases.size() == 0) {
            this.priorItemBiases = this.itemBiases;
            this.priorUserBiases = this.userBiases;

            this.priorItemLatentFactorVectors = this.itemLatentFactorVectors;
            this.priorUserLatentFactorVectors = this.userLatentFactorVectors;

            return false;
            // if the prior vectors have been initialised then compare
        } else {
            boolean value = true;
            for (String item : itemBiases.keySet()) {
                double diff = Math.abs(itemBiases.get(item) - priorItemBiases.get(item));
                if(diff > epsilon)
                    value = false;
            }
            for (String user : userBiases.keySet()) {
                double diff = Math.abs(userBiases.get(user) - priorUserBiases.get(user));
                if(diff > epsilon)
                    value = false;
            }

            // check that the the latent vectors have converged:
            // items
            for (String item : itemLatentFactorVectors.keySet()) {
                SparseDoubleMatrix1D latentVector = itemLatentFactorVectors.get(item);
                SparseDoubleMatrix1D priorLatentVector = priorItemLatentFactorVectors.get(item);
                for (int i = 0; i < latentVector.size(); i++) {
                    double diff = Math.abs(latentVector.get(i) - priorLatentVector.get(i));
                    if(diff > epsilon)
                        value = false;
                }
            }
            // users
            for (String user : userLatentFactorVectors.keySet()) {
                SparseDoubleMatrix1D latentVector = userLatentFactorVectors.get(user);
                SparseDoubleMatrix1D priorLatentVector = priorUserLatentFactorVectors.get(user);
                for (int i = 0; i < latentVector.size(); i++) {
                    double diff = Math.abs(latentVector.get(i) - priorLatentVector.get(i));
                    if(diff > epsilon)
                        value = false;
                }
            }

            return value;
        }
    }

    @Override
    public void writeDiagnostics(String pathToDiagnotisticsFileDir) {

        // write the epoch buffer to a file
        StringBuffer buffer = new StringBuffer();
        for (Integer epoch : this.epochErrors.keySet()) {
            // take the squared error
            double error = this.epochErrors.get(epoch);
            double sError = error * error;
            buffer.append(epoch + "\t" + sError + "\n");
        }

        // write this to the file for the model
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(pathToDiagnotisticsFileDir + "svd_model_" + this.eta + "_" + this.lambda + "_.tsv"),false));
            writer.println(buffer.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
