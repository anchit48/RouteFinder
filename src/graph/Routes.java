package graph;

import java.util.Hashtable;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Process containing all the routing algorithms 
 * ranging from distance finder to
 * all the routes.
 * 
 * @author anchitsaxena
 * @version 1.0
 */

public class Routes {
	public Hashtable<Town, Edge> routingTable;
	
	private static final Logger logger = Logger.
			getLogger(Routes.class.getName());
	

	public Routes() {
		this.routingTable = new Hashtable<Town, Edge>();
	}

	public Hashtable<Town, Edge> getRoutingTable() {
		return routingTable;
	}
	/**
	 * Determine the distance between the Towns
	 * 
	 * @param towns
	 * @return
	 * @throws NoSuchRouteException
	 */
	public int findDistanceBetweenTowns(
			ArrayList<Town> towns) 
					throws NoSuchRouteException {

		if (towns.size() < 2) {
			return 0;
		}

		int distance, depth, index;
		distance = depth = index = 0;

		while (index < towns.size() - 1) {
			if (this.routingTable.containsKey(
					towns.get(index))) {
				Edge route = this.routingTable.get(
						towns.get(index));
				while (route != null) {
					if (route.destination.equals(
							towns.get(index + 1))) {
						distance += route.weight;
						depth++;
						break;
					}
					route = route.next;
				}
			} else
				throw new NoSuchRouteException();
			index++;
		}

		if (depth != towns.size() - 1) {
			throw new NoSuchRouteException();
		}
		return distance;
	}

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param limit
	 * @return
	 */
	public int numberOfStopsBetweenTowns(Town origin, 
			Town destination, int limit) {
		return findRoutes(origin, destination, 0, limit);
	}

	private int findRoutes(Town origin, Town dest, 
			int depth, int limit) {
		int routes = 0;
		if (this.routingTable.containsKey(origin) && 
				this.routingTable.containsKey(dest)) {
			if (depth == limit) {
				return 0;
			}
			depth++;
			Edge edge = this.routingTable.get(origin);
			while (edge != null) {
				if (edge.destination.equals(dest)) {
					routes++;
					edge = edge.next;
					continue;
				} else if (!edge.destination.visited) {
					depth--;
					routes += findRoutes(edge.destination, 
							dest, depth, limit);

				}

				edge = edge.next;

			}

		} else {
			noRouteException();
		}

		origin.visited = false;
		return routes;
	}

	/**
	 * Find the shortest route between two Town
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public int shortestRouteBetweenTowns(Town origin, 
			Town destination) {
		return findShortestRoute(origin, 
				destination, 0, 0);
	}

	public int findShortestRoute(Town origin, Town dest, 
			int distance, int shortestPath) {
		if (this.routingTable.containsKey(origin) && 
				this.routingTable.containsKey(dest)) {
			origin.visited = true;
			Edge edge = this.routingTable.get(origin);
			while (edge != null) {
				if (edge.destination == dest || 
						!edge.destination.visited) {
					distance += edge.weight;
				}

				if (edge.destination.equals(dest)) {
					if (shortestPath == 0 || 
							distance < shortestPath)
						shortestPath = distance;
					origin.visited = false;
					return shortestPath;
				}

				/* Some backtracking and recursion */
				else if (!edge.destination.visited) {
					shortestPath = findShortestRoute(
							edge.destination, dest, 
							distance, shortestPath);
					distance -= edge.weight;
				}
				edge = edge.next;
			}
		} else {
			noRouteException();
		}

		origin.visited = false;
		return shortestPath;
	}

	/**
	 * Find number of routes between towns
	 * @param origin
	 * @param dest
	 * @param maxDistance
	 * @return
	 */
	public int numRoutesWithin(Town origin, Town dest, 
			int maxDistance) {
		return findAllRoutesBetweenTowns(origin, dest,
				0, maxDistance);
	}

	/**
	 * Compute all the routes between Towns
	 * @param origin
	 * @param destination
	 * @param weight
	 * @param maxDistance
	 * @return
	 */
	private int findAllRoutesBetweenTowns(Town origin, 
			Town destination, int weight, 
			int maxDistance) {
		int routes = 0;
		if (this.routingTable.containsKey(origin) && 
				this.routingTable.containsKey(
						destination)) {

			Edge edge = this.routingTable.get(origin);
			while (edge != null) {
				weight += edge.weight;
				if (weight <= maxDistance) {
					if (edge.destination.equals(
							destination)) {
						routes++;
						routes += 
							findAllRoutesBetweenTowns(
								edge.destination, 
								destination, weight, 
								maxDistance);
						edge = edge.next;
						continue;
					} else {
						routes += 
							findAllRoutesBetweenTowns(
								edge.destination, 
								destination, weight, 
								maxDistance);
						weight -= edge.weight;
					}
				} else
					weight -= edge.weight;

				edge = edge.next;
			}
		} else {
			noRouteException();

		}
		return routes;

	}

	public void noRouteException() {
		logger.info(graph.Constants.NO_SUCH_ROUTE);

	}

	public class NoSuchRouteException extends Exception {

		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage() {
			return graph.Constants.NO_SUCH_ROUTE;
		}
	}
}
