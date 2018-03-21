package graph;

/**
* Graph implementation of the Routing problem
* @author anchitsaxena
* @version 1.0
*/

/**
 * Node representation of a Town. 
 * It contains a name and a visited flag.
 */

public class Town {
	protected String name;
	protected boolean visited;

	public Town(String name) {
		this.name = name;
		this.visited = false;
	}

	public String getName() {
		return this.name;
	}

	public boolean getVisited() {
		return this.visited;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj.getClass() != getClass() ||
				obj == null) {
			return false;
		}

		Town rhsTown = (Town) obj;
		return this.name.equals(rhsTown.name);
	}

	@Override
	public int hashCode() {
		if (this.name == null)
			return 0;
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return this.name.toString();
	}

}