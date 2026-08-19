Christofides Algorithm

A java implementation of the Christofides Algorithm as a heuristic method to generate a solution for the Travelling Salesman Problem.
The program generates an image displaying the tour that it has generated across the "cities" which are represented as little circles 
and the edges between them are lines. It also displays a lower bound for the optimal solution for the TSP as well as the length of the 
generated tour.

The program begins by generating a set of randomly positioned cities on a given map size and vertex count.
Then a Minimum Spanning Tree (MST) is created on the set of randomly generated cities.
Any vertices of an odd degree have a perfect matching created on them which is then combined with the MST to ensure that all vertices
are of an even degree, making an Eulerian walk possible.
An Eulerian walk is then found on the Eulerian graph.
The Eulerian walk is then shortcut by creating direct edges between cities that have been visited more than once to create a Hamiltonian circuit.
The final tour is then displayed in an image along with a lower bound for the TSP and the total weight of all edges of the graph.
The lower bound is calculated by removing a vertex, v, from a complete graph of all the edges, then generating an MST on this graph.
The lower bound is then calculated as the sum of the total weight of all the edges of the MST and the two cheapest edges incident to the removed
vertex, v.

Christofides Algorithm generates a solution that has a length (the sum of all weights of edges in the graph) that is no longer then 1.5 times 
the length of the optimal solution. However, the genuine Christofides algorithm uses a minimum weight perfect matching, but, my code uses 
a greedy algorithm to generate the perfect matching which will not necessarily be minimum weight. Therefore, this bounding will not hold up for my code.

Class Overview:\
ChristofidesAlgorithm - Carries out Christofides Algorithm, calling other methods step by step.\
City - A data structure that holds an x and y position.\
CityGenerator - Generates randomly positioned cities and the pairwise graph which holds the Euclidean pairwise distance between each city.\
Edge - A data structure that represents the connection between cities, holds the weight of the edge as well as its position in the adjacency matrix.\
EulerianWalk - Finds a closed Eulerian Walk on a graph - a walk that starts/ends on the same vertex and visits every edge.\
GraphMatrix - Represents an undirected weighted graph using an adjacency matrix used for dense graphs such as pairwiseGraph which contains an edge between every vertex\
GraphList - Represents an undirected weighted graph using an adjacency list, used for sparse graphs such as the minimum spanning tree, it uses a lot less memory than the graphMatrix however some processes may take slightly longer to compute.\
GraphicsManager - Generates an image of the tour.\
MST - Generates an MST using Kruskal's algorithm.\
PerfectMatching - Uses a global greedy algorithm to find a perfect matching on a list of given vertices.\
TSPLowerBound - Generates a lower bound for the length of the optimal solution of the TSP.\
