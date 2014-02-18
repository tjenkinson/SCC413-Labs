package uk.ac.lancacaster.scc.data.lab2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: Matthew Rowe Email: m.rowe@lancaster.ac.uk Date / Time : 10/02/2014 /
 * 14:31
 */
public class Spider {

//	private String pSQuery = "prefix dbpedia:<http://dbpedia.org/resource/> "
//			+ "prefix foaf:<http://xmlns.com/foaf/0.1/> "
//			+ "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
//			+ "select ?person "
//			+ "where {<%s> ?p ?person.?person rdf:type foaf:Person} LIMIT 100";
//
//	private String rSQuery = "prefix dbpedia:<http://dbpedia.org/resource/> "
//			+ "select ?resource " + "where {<%s> ?p ?resource}";
//
//	private QueryProcessor qp = new QueryProcessor();
//
//	private HashSet<String> people = new HashSet<String>();
//	private Queue<String> resources = new ConcurrentLinkedQueue<String>();
//	private HashSet<String> checkedR = new HashSet<String>();
//
//	public void walk(String dbpr) {
//		people.addAll(extractPeople(dbpr));
//		resources.addAll(extractResources(dbpr));
//		while (!resources.isEmpty() && people.size() < 100) {
//			String res = resources.poll();
//			if (!checkedR.contains(res)) {
//				people.addAll(extractPeople(res));
//				resources.addAll(extractResources(res));
//				checkedR.add(res);
//			}
//		}
//		int i = 1;
//		for (String p : people) {
//			System.out.println(i + ". " + p);
//			i++;
//		}
//		/*
//		 * Note there may be some overflow of the 100 first found people. For
//		 * example if there are 99 people in the set the next resource will be
//		 * queried if that resource then returns 3 people the set will finish
//		 * containing 102 people.
//		 */
//	}
//
//	private List<String> extractPeople(String dbpr) {
//		try {
//			System.out.print("Looking for people in " + dbpr);
//			List<String> peeps = qp.runQuery(String.format(pSQuery, dbpr), "person");
//			System.out.println(" found " + peeps.size());
//			return peeps;
//		} catch (Exception e) {
//			System.out
//					.println(" couldn't find anything - probably not a resource");
//			return Arrays.asList();
//		}
//	}
//
//	private List<String> extractResources(String dbpr) {
//		try {
//			System.out.print("Looking for resources in " + dbpr);
//			List<String> res = qp.runQuery(String.format(rSQuery, dbpr),"resource");
//			System.out.println(" found " + res.size());
//			return res;
//		} catch (Exception e) {
//			System.out
//					.println(" couldn't find anything - probably not a resource");
//			return Arrays.asList();
//		}
//	}
//
//	public static void main(String[] args) {
//		new Spider().walk("http://dbpedia.org/resource/Lancaster_University");
//	}

}
