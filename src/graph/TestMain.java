package graph;
import graph.Constants;
import graph.Routes.NoSuchRouteException;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * This will perform the LOGIC testing
 * to check the expected and the actual
 * output 
 * @author anchitsaxena
 * VERSION 1.0
 *
 */
class TestMain {
	
	public Routes load_data() {
		Routes graph = new Routes();
		Constants constants = new Constants();
		Town a = new Town(constants.TOWN_A);
		Town b = new Town(constants.TOWN_B);
		Town c = new Town(constants.TOWN_C);
		Town d = new Town(constants.TOWN_D);
		Town e = new Town(constants.TOWN_E);

		/*
		 * Adjacency List
		 * 
		 * A | {B,5}, {D,5}, {E,7} B | {C,4} C | 
		 * {D,8}, {E,2} D | {C,8}, {E,6} E | {B,3}
		 * 
		 * Created in code below.
		 */
		graph.routingTable.put(a, new Edge(
				a, b, constants.A_B_DIST).next(
						new Edge(
				a, d, constants.A_D_DIST).next(new Edge(
						a, e, constants.A_E_DIST))));
		graph.routingTable.put(b, new Edge(
				b, c, constants.B_C_DIST));
		graph.routingTable.put(c, new Edge(
				c, d, constants.C_D_DIST).next(
				new Edge(c, e, constants.C_E_DIST)));
		graph.routingTable.put(d, new Edge(
				d, c, constants.D_C_DIST).next(
						new Edge(d, e, constants.D_E_DIST)));
		graph.routingTable.put(e, new Edge(
				e, b, constants.E_B_DIST));
		return graph;
		
	}
	
	@Test
	void testFindDistanceBetweenTowns(){
		Routes graph = load_data();
		ArrayList<Town> routes1 = new ArrayList<Town>();
		Constants constants = new Constants();
		Town a = new Town(constants.TOWN_A);
		Town b = new Town(constants.TOWN_B);
		Town c = new Town(constants.TOWN_C);
		routes1.add(a);
		routes1.add(b);
		routes1.add(c);
		try {
			assertEquals(9, graph.
					findDistanceBetweenTowns(routes1));
		} catch (NoSuchRouteException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testNumberOfStopsBetweenTowns() {
		Routes graph = load_data();
		ArrayList<Town> routes1 = new ArrayList<Town>();
		Constants constants = new Constants();
		Town a = new Town(constants.TOWN_A);
		Town b = new Town(constants.TOWN_B);
		Town c = new Town(constants.TOWN_C);
		routes1.add(a);
		routes1.add(b);
		routes1.add(c);
		assertEquals(2,graph.
		numberOfStopsBetweenTowns(c, c, 
				constants.C_C_STOP_MAX));
		
	}
	
	@Test
	void testShortestRoutesBetweenTowns() {
		Routes graph = load_data();
		ArrayList<Town> routes1 = new ArrayList<Town>();
		Constants constants = new Constants();
		Town a = new Town(constants.TOWN_A);
		Town b = new Town(constants.TOWN_B);
		Town c = new Town(constants.TOWN_C);
		routes1.add(a);
		routes1.add(b);
		routes1.add(c);
		assertEquals(9,graph.
				shortestRouteBetweenTowns(a, c));
		
	}
	
	@Test
	void testNumRoutesWithin() {
		Routes graph = load_data();
		ArrayList<Town> routes1 = new ArrayList<Town>();
		Constants constants = new Constants();
		Town a = new Town(constants.TOWN_A);
		Town b = new Town(constants.TOWN_B);
		Town c = new Town(constants.TOWN_C);
		routes1.add(a);
		routes1.add(b);
		routes1.add(c);
		assertEquals(7,graph.
				numRoutesWithin(c, c, 
						constants.C_C_DIST));
		
	}
	

}
