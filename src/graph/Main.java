package graph;

import java.util.ArrayList;
import java.util.logging.Logger;

import graph.Routes.NoSuchRouteException;
import graph.Constants;

/**
 * Wrapper Process calling all the modules
 * 
 * @author anchitsaxena
 *
 */

public class Main {

	private static final Logger logger = Logger.
			getLogger(Main.class.getName());

	public static void main(String[] args) 
			throws NoSuchRouteException {

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

		/* Distance of route A-B-C */
		ArrayList<Town> routes1 = new ArrayList<Town>();
		routes1.add(a);
		routes1.add(b);
		routes1.add(c);
		logger.info(constants.OUTPUT_1 + graph.
				findDistanceBetweenTowns(routes1));

		/* Distance of route A-D */
		ArrayList<Town> routes2 = new ArrayList<Town>();
		routes2.add(a);
		routes2.add(d);
		logger.info(constants.OUTPUT_2 + graph.
				findDistanceBetweenTowns(routes2));

		/* Distance of route A-D-C */
		ArrayList<Town> routes3 = new ArrayList<Town>();
		routes3.add(a);
		routes3.add(d);
		routes3.add(c);
		logger.info(constants.OUTPUT_3 + graph.
				findDistanceBetweenTowns(routes3));

		/* Distance of route A-E-B-C-D */
		ArrayList<Town> routes4 = new ArrayList<Town>();
		routes4.add(a);
		routes4.add(e);
		routes4.add(b);
		routes4.add(c);
		routes4.add(d);

		logger.info(constants.OUTPUT_4 + graph.
				findDistanceBetweenTowns(routes4));

		/* Distance of route A-E-D */
		ArrayList<Town> routes5 = new ArrayList<Town>();
		routes5.add(a);
		routes5.add(e);
		routes5.add(d);
		try {
			logger.info(constants.OUTPUT_5 + graph.
					findDistanceBetweenTowns(routes5));
		} catch (Exception ex) {
			logger.info(constants.OUTPUT_5 + 
					ex.getMessage());
		}

		/* Number of trips starting at C,
		 * ending at C with 3 stops max */
		logger.info(constants.OUTPUT_6 + graph.
				numberOfStopsBetweenTowns(c, c, 
						constants.C_C_STOP_MAX));

		/* Number of trips starting at A,
		 * ending at C with 4 stops max */
		logger.info(constants.OUTPUT_7 + graph.
				numberOfStopsBetweenTowns(a, c, 
						constants.A_C_STOP_MAX));

		/* The length of the shortest route 
		 * from A to C. */
		logger.info(constants.OUTPUT_8 + graph.
				shortestRouteBetweenTowns(a, c));

		/* The length of the shortest route 
		 * from B to B. */
		logger.info(constants.OUTPUT_9 + graph.
				shortestRouteBetweenTowns(b, e));

		/*
		 * Different routes from C to C 
		 * with a distance of less than 30
		 */
		logger.info(constants.OUTPUT_10 + graph.
				numRoutesWithin(c, c, 
						constants.C_C_DIST));

	}
}