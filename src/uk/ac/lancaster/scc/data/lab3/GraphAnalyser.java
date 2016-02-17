package uk.ac.lancaster.scc.data.lab3;

import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public class GraphAnalyser {

    public void analyseGraph() {

        // load the graph from the file

        // point the graph location at the file
        String fileLocation = "";

        // build the graph object from each edge in the file

        // create a new DirectedGraph object to store the social network within
        Graph<String, String> graph = new SparseMultigraph<String, String>();
        int edgeIndex = 1;

        try {

            String sCurrentLine;
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));

            while ((sCurrentLine = br.readLine()) != null) {

                // split the line by the token delimiter (this could be either a \t or a space
                String[] tokens = sCurrentLine.split(" ");

                // the first token denotes the id of the first node
                String sourceNode = tokens[0];  // from

                // the second token denotes the id of the second node
                String targetNode = tokens[1];  // to

                // add the nodes to the graph object
                if(!graph.containsVertex(sourceNode))
                    graph.addVertex(sourceNode);

                if(!graph.containsVertex(targetNode))
                    graph.addVertex(targetNode);

                // add the edge to the graph
                String edgeLabel =  "Edge_" + edgeIndex;
                graph.addEdge(edgeLabel,sourceNode,targetNode);

                // increment the edge count index
                edgeIndex++;
            }

            // measure the in-degree of a given node - you need to adapt this part for exercise 1 to return all nodes with an indegree greater than 0
            String nodeID = "1";
            int nodeInDegree = graph.inDegree(nodeID);
            System.out.println(nodeID + " = " + nodeInDegree);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        GraphAnalyser analyser = new GraphAnalyser();
        analyser.analyseGraph();

    }



}
