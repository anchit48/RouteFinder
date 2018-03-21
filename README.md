# RouteFinder

This service will recommend the possible number of routes, compute the 
distance along a certain route and the shortest route between two towns.

This is the classical example of `Directed Acyclic Graph` problem. It
can be addressed by several frameworks such as `Neo4j Graphs` , `py-dag` or `dag-networkx`.

However, all the development has been done from scratch, to get the better understanding
of the implementation and the challenges associated with it.
Some of the basic experiment has also been done with Neo4j (computing shortest distance between adjacent nodes).
If interested, details can be shared around it as well.

# APPROACH

Underlying principle involved here is the `Greedy Algorithm` (in shortest route) which will
take the minimum distance into account at every node visit starting from source till the destination.

Function recursion has also been actively used.

# REQUIREMENT:

1. Java 1.8
2. Eclipse Oxygen
3. Junit
4. Logger
