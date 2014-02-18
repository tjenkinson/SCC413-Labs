package uk.ac.lancaster.scc.data.lab2;

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
	// Query for the list of alumni celebrities
    public void runQuery() {
    	//"PREFIX owl: <http://dbpedia.org/ontology/almaMater>"dbpedia-owl:almaMater ;
        
        String query ="select ?Celeb where {?Celeb  <http://dbpedia.org/ontology/almaMater> <http://dbpedia.org/resource/Lancaster_University> }" ;
        		//"select distinct ?Concept where {[] a ?Concept} LIMIT 100";
        //for the full list of only locomotives - select ?Locomotives where {?Locomotives  <http://dbpedia.org/ontology/designer> <http://dbpedia.org/resource/Nigel_Gresley> }

        // this the endpoint of the SPARQL server
        String sparqlEndpointURL =  "http://dbpedia.org/sparql";

        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointURL,query);
        ResultSet results = qexec.execSelect() ;
        for ( ; results.hasNext() ; )
        {
            // get the next solution from the list
           QuerySolution soln = results.nextSolution();

            // get the variable value from the list
           String Celeb = soln.get("?Celeb").toString();
       
        
            // output the Celeb
           	System.out.println(Celeb);
           	
         
        }
        qexec.close();
      //LINE SPACE TO SEPERATE runQuery output from runQueryB
       	System.out.println("");
    }
  
    // Query for locomotives and their withdrawn dates
    public void runQueryB() {
    	//"PREFIX owl: <http://dbpedia.org/ontology/almaMater>"dbpedia-owl:almaMater ;
        
        String query = "select ?Locomotives ?Wdate where {?Locomotives  <http://dbpedia.org/ontology/designer> <http://dbpedia.org/resource/Nigel_Gresley>.  ?Locomotives  <http://dbpedia.org/property/withdrawndate> ?Wdate}" ;
        		//"select distinct ?Concept where {[] a ?Concept} LIMIT 100";
        //

        // this the endpoint of the SPARQL server
        String sparqlEndpointURL =  "http://dbpedia.org/sparql";

        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointURL,query);
        ResultSet results = qexec.execSelect() ;
        for ( ; results.hasNext() ; )
        {
            // get the next solution from the list
           QuerySolution soln = results.nextSolution();

            // get the variable value from the list
           String Locomotives = soln.get("?Locomotives").toString();
           String Wdate = soln.get("?Wdate").toString();
        
            // output the Locomotives and Wdate
          
           	System.out.println(Locomotives +" withdrawn date; " + Wdate );
           	System.out.println("");//space for clarity
         
        }
        qexec.close();
    }
    	
    
    public static void main(String[] args) {

        QueryProcessor processor = new QueryProcessor();
        processor.runQuery();
        processor.runQueryB();
    }

}
