package uk.ac.lancaster.scc.data.lab3;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 * Date / Time : 10/02/2014 / 14:31
 */
public class QueryProcessor {

    public void runQuery() {

        // replace the below default query with your own
        String query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";

        // this the endpoint of the SPARQL server
        String sparqlEndpointURL = "http://dbpedia.org/sparql";

        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointURL,query);
        ResultSet results = qexec.execSelect() ;
        for ( ; results.hasNext() ; )
        {
            // get the next solution from the list
            QuerySolution soln = results.nextSolution();

            // get the variable value from the list
            String concept = soln.get("?Concept").toString();

            // output the concept
            System.out.println(concept);

        }
        qexec.close();
    }

    public static void main(String[] args) {

        QueryProcessor processor = new QueryProcessor();
        processor.runQuery();
    }

}
