**CPEN 221 / Fall 2017: Machine Problem 3**

The Graph ADT and Social Networks
===

## Logistics

+ The weight for this machine problem is 6% of your raw score in the course.
+ This is an *individual* assignment.

## Background

In this assignment, you will implement a `Graph` interface using two different graph representations. You will then develop several algorithms that use the `Graph` interface that might be used in a social network.

Your goals for this machine problem are to:
+ Understand and apply the concept of encapsulation;
+ Understand interfaces;
+ Understand what graphs are and how they can be represented;
+ Implement some basic graph algorithms.

**Preliminary reference**: Wikipedia entry on Graphs https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)

## Instructions

### Graph Implementations
First, write two classes that implement the `ca.ubc.ece.cpen221.mp3.staff.Graph` interface, which represents a _directed_ graph.
+ **Adjacency List**: Inside the package `ca.ubc.ece.cpen221.mp3.graph`, implement the `AdjacencyListGraph` class. Your implementation must internally represent the graph as an _adjacency list_. If you are not familiar with the adjacency list representation of graphs, see the [Wikipedia page on the adjacency list representation](https://en.wikipedia.org/wiki/Adjacency_list) as a reference.
+ **Adjacency Matrix**: Next, implement the `AdjacencyMatrixGraph` class in the `ca.ubc.ece.cpen221.mp3.graph` package. Your implementation must internally represent the graph as an adjacency matrix. If you are not familiar with the adjacency matrix representation of graphs, see the [Wikipedia page on the adjacency matrix representation](https://en.wikipedia.org/wiki/Adjacency_matrix) as a reference.

#### Algorithm Implementations
For this part of the assignment, you will implement algorithms that might be used for social network analysis using your graph implementations.

Your algorithms must use only the methods provided in the interface, and can not use any features specific to the implementation of `Graph` being used. Your algorithms must work correctly on any correct implementation of a `Graph`, including your `AdjacencyMatrixGraph` and `AdjacencyListGraph`.

_All your algorithms must work on directed graphs (digraphs)._

+ **Breadth first search (BFS)**: Implement the [breadth first search](https://en.wikipedia.org/wiki/Breadth-first_search) algorithm to traverse a graph.
+ **Depth first search (DFS)**: Implement the [depth first search](https://en.wikipedia.org/wiki/Depth-first_search) algorithm to traverse a graph.

For both BFS and DFS above, you should use traverse the entire graph. You should return a set of lists (`Set<List<Vertex>>`). Each list in the set is a connected component of the graph. The vertices in each list are in the order they were visited by the traversal routines. More specifically, start a BFS or DFS traversal at every possible vertex and each such traversal will produce a list. The set of all these lists is what you want to return.

+ **Distance**: Implement a method to find the distance between two vertices in an unweighted graph. In an unweighted graph _G_, given two vertices _s_ and _t_, the distance between the two vertices is the minimum number of edges that would have to be traversed to get to _t_ from _s_. In other words, the distance between two vertices is the length of the shortest path between the two vertices. The distance between a vertex and itself is 0. If no path exists from _s_ to _t_ then your method should take appropriate action.
+ **Graph diameter**: The diameter of a graph is the maximum distance among the distances between all pairs of vertices in the graph. If it is not possible to get to vertex _t_ from vertex _s_ then the distance between those two vertices, and consequently the graph diameter, is _infinity_. We will, however, adopt a more relaxed definition where we will specify the diameter as being the maximum finite distance among pairs of vertices except in the case when all distances are infinite (when we will treat the diameter as infinite). Implement a method to determine the diameter of a graph.
+ **Graph center**: For a vertex _s_, the _eccentricity_ of _s_ is defined as the maximum distance between _s_ and any other vertex _t_ in the graph. The center of a graph is the vertex with minimum eccentricity. Like we did in the case of the diameter, we will consider only finite distances unless all distances are infinite. Implement a method to find vertex that is the center of a given graph.
+ **Common upstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _u_ such that there is an edge from _u_ to _a_ and an edge from _u_ to _b_. If there are no such vertices then your implementation should return an empty list.
+ **Common downstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _v_ such that there is an edge from _a_ to _v_ and an edge from _b_ to _v_.  

### Social Network Analysis

We will apply our graph ADT and algorithms to two datasets. Interestingly, both these datasets represent _undirected_ graphs, which implies that edges are bidirectional.

1. **Marvel Character Universe**: This dataset represents relationships between characters in the Marvel comics universe. The dataset is a text file where each row contains the name of a character (Column 1) and a comic book number (Column 2) in which that character appeared. In the graph representation, you should treat the characters as vertices with an edge existing between two characters if they appeared in the same comic book.
2. **Enron Email Exchange**: This dataset captures the flow of email within Enron before its demise. This dataset is anonymized and each row contains two employee ids and represents that fact that the employees with those ids exchanged email. We will consider employees as vertices and an edge exists between two employees if there is a row that contains the two corresponding ids.

We are interested in determining the diameter and the center of the social networks that these two datasets represent.

## Testing Your Code
Use JUnit to test the correctness of your implementation. Write tests that check the correctness of normal cases as well as edge cases of the Graph ADT and the algorithms. Tests should be in the package `ca.ubc.ece.cpen221.mp3.tests`.

## Evaluation

To earn full credit you must:
+ Properly encapsulate your implementation. Use the most restrictive access level that makes sense for each of your fields and methods (i.e., use `private` unless you have a good reason not to). Instead of manipulating class fields directly, make them `private` and implement getter and setter methods to manipulate them from outside of the class.
+ Not edit any files in the `ca.ubc.ece.cpen221.mp3.staff` package or any of the method declarations we’ve initially provided for you.
+ Make sure your code is readable. Use proper indentation and whitespace, abide by standard Java naming conventions, and add additional comments as necessary to document your code.
+ Follow good code hygiene by adopting practices discussed in the notes on code review.
+ Write clear and concise specifications for the methods that you have been asked to implement (and other methods too).
+ State the representation invariant and the abstraction function for the two implementations of the Graph ADT as comments in the appropriate Java files.
+ Write tests that provide 100% code coverage for all the methods of the implementation.

## Additional Hints

+ You may create helper classes and helper methods to help you with the assignment, as long as your code is compatible with the provided interfaces.
+ The tasks may be underspecified. Use your judgment. Write specifications. You can ask reasonable questions on Piazza.
+ As long as your code runs in a _reasonable_ amount of time, and returns the correct values, you do not need to worry about the [time complexity](https://en.wikipedia.org/wiki/Time_complexity) of your algorithms.
+ Consider using <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>F</kbd> in Eclipse to auto-format your code.
+ **Build Status on Travis CI**: Your code will be compiled and tested (built) on a system different from your own. To this end, you can use **Travis CI** as a service that independently builds and tests your project. You can see the status of the build on Travis CI after you push your work to Github.
+ **Code Coverage on Coveralls**: You can view the code coverage for your work using a service provided by [`https://coveralls.io`](https://coveralls.io/). The provided Travis configuration file (`.travis.yml`) has a directive to push code coverage results generated by JaCoCo to Coveralls. To make this step work correctly, you will have to do the following:
	- Login to `coveralls.io` using your Github account;
	- Add your Github repository for this machine problem to Coveralls;
	- Examine the details for your repo on Coveralls, and obtain the `repo\_token` generated by Coveralls;
	- In your repository, edit the `.travis.yml` file and add this token after the `COVERALLS_REPO_TOKEN=` directive. Travis will need this token to push the code coverage details to Coveralls.
- **Code Quality on Codacy**: You should have received an invitation to join the CPEN 221 organization on [`codacy.com`](https://www.codacy.com/). Codacy automates some aspects of assessing code quality and you should address issues with you code that Codacy identifies. Sign up and login to Codacy (follow the link in the invitation email) and then you can add your Github repository to a team in the CPEN 221 organization on Codacy. At this point, each student is part of a separate team because you are only reviewing your own code.
- Given the tools and services we are using, it is in *your best interest* to push your work to Github regularly (and not just before the submission deadline) to benefit from the feedback on code quality (via Codacy) and code coverage (via Coveralls) as well as general build status (via Travis CI).

## Submission Instructions

+ You should commit all source code to your assigned Github repository.
+ You should submit your work and preserve the provided repository folder structure. While you should not submit binaries (`.class` files, etc.), it is best to submit all other files so that compilation and execution is easy. Think of working with the staff as your collaborators; you want to keep the repository organization consistent for this purpose.
+ **On Canvas**, you should submit a PDF version of your `Algorithms.java` file for peer review.

## Previously Asked Questions

1. Can an edge have the same vertex as the start and end point?
	* Such edges are sometimes called self-loops. For the purpose of this assignment, we will disallow self-loops.
2. What is the distance from a vertex to itself?
	* The distance from a vertex to itself is 0. (We will use this as a definition for this assignment.)
3. What is an upstream neighbour?
 	* In a directed graph, if you have _a -> b_, or an edge from _a_ to _b_, then _a_ is an upstream neighbour of _b_, and equivalently _b_ is a downstream neighbour of _a_.
4. In an undirected graph, an edge between _a_ and _b_ implies that we can go from _a_ to _b_ and then _b_ to _a_. Is this a loop?
	* No; this is not considered a loop. A loop requires two or more edges except in the case of self-loops, which we are disallowing in any case. We will also not consider the situation when there are multiple edges between the same pair of vertices.
