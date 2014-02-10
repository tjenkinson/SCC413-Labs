package uk.ac.lancaster.scc.data.lab2;

import com.hp.hpl.jena.query.*;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 */
public class QueryProcessor {


    public void runQuery() {

        // setup the initial repository connection to the remote SPARQL endpoint
        String endpointURL = "http://dbpedia.org/sparql"; // fill in the endpoint URL for dbpedia here

        String query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100"; // enter your query here that is to be processed

        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointURL, query);

        ResultSet results = qexec.execSelect() ;
        for ( ; results.hasNext() ; )
        {
            // get the solution object
            QuerySolution soln = results.nextSolution();

            // get the values bound to the '?Concept' variable
            String concept = soln.get("?Concept").toString();

            // print them to a line
            System.out.println(concept);

        }
        qexec.close();
    }


    public static void main(String[] args) {

        QueryProcessor processor = new QueryProcessor();
        processor.runQuery();

    }

}
