package uk.ac.lancaster.scc.data.lab2;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 * Date / Time : 10/02/2014 / 14:31
 */
public class QueryProcessor {
	// Query for the list of alumni celebrities
    public void runQuery() {
        
        String query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";

        // this the endpoint of the SPARQL server
        String sparqlEndpointURL =  "http://dbpedia.org/sparql";

        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointURL,query);
        ResultSet results = qexec.execSelect() ;
        for ( ; results.hasNext() ; )
        {
            // get the next solution from the list
           QuerySolution soln = results.nextSolution();

            // get the variable value from the list
           String Celeb = soln.get("?Concept").toString();
       
        
            // output the Celeb
           	System.out.println(Celeb);
           	
         
        }
        qexec.close();
    }

    public List<String> runQuery(String query, String variableName) {
        List<String> variables = new ArrayList<String>();
        // this the endpoint of the SPARQL server
        String sparqlEndpointURL = "http://dbpedia.org/sparql";

        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointURL, query);
        ResultSet results = qexec.execSelect();
        for (; results.hasNext(); ) {
            // get the next solution from the list
            QuerySolution soln = results.nextSolution();

            // get the variable value from the list
            String variable = soln.get("?" + variableName).toString();
            variables.add(variable);


        }
        qexec.close();

        // return the idenified variables
        return variables;
    }

    
    public static void main(String[] args) {

        QueryProcessor processor = new QueryProcessor();
        processor.runQuery();
    }

}
