package uk.ac.lancaster.scc.data.lab4.prediction;


import uk.ac.lancaster.scc.data.lab4.datasets.Review;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public interface PredictionModel {

    /*
     * A method to update the parameters of the model based on the error
     * Includes model specific update rules for SGD
     */
    public void update(Review review, int reviewIndex, double error);

    /*
     * Apply the model to the review and work out the error
     */
    public double apply(Review review);

    /*
     * Returns the status of the model - i.e. how many epochs in
     */
    public String reportStatus();

    /*
     * Compares the current model with the previous one to find out if the model has converged
     * This means that the average difference between the parameter vectors is < epsilon
     */
    public boolean convergenceCheck();

    /*
     * Writes the model's diagnostics to a file for assessing (decreasing in training error)
     */
    public void writeDiagnostics(String filePath);

}
