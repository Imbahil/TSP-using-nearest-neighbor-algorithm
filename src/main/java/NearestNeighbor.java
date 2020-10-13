import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Setter
public class NearestNeighbor {

    private int initialIndex;

    public Route findShortestRoute(ArrayList<Vertex> vertices) { //takes as parameter initial vertices list
        ArrayList<Vertex> shortestRouteVertices = new ArrayList<>(vertices.size());

        System.out.println("\n************************************************");
        System.out.println("Initial route: " + Arrays.toString(vertices.toArray()));
        System.out.println("Initial vertex: " + vertices.get(initialIndex));
        System.out.println("************************************************\n");

        Vertex vertex = vertices.get(initialIndex); //starting vertex

        updateRoutes(shortestRouteVertices, vertices, vertex);

        //keep removing vertices from vertices ArrayList and
        //putting them in the shortest route vertices ArrayList
        while (vertices.size() >= 1) {
            vertex = getNextVertex(vertices, vertex);
            updateRoutes(shortestRouteVertices, vertices, vertex); //having this new vertex will call the updateRoutes passing it
        }

        return new Route(shortestRouteVertices); //returns shortest route using nearest neighbor algorithm
    }

    // removes the vertex passed from the vertices ArrayList
    // and add it to the shortest route vertices ArrayList
    // and also prints the current shortest route vertices and the reaming route vertices
    private void updateRoutes(ArrayList<Vertex> shortestRouteVertices, ArrayList<Vertex> vertices, Vertex vertex) {
        vertices.remove(vertex);
        shortestRouteVertices.add(vertex);

        System.out.println("Vertices in shortest route: " + Arrays.toString(shortestRouteVertices.toArray()));
        System.out.println("Reaming vertices: " + Arrays.toString(vertices.toArray()) + "\n");
    }

    //chooses the next vertex, measure the distance from the passed vertex
    //to each one of the vertices in the vertices ArrayList and returns the vertex with the minimum distance
    private Vertex getNextVertex(ArrayList<Vertex> vertices, Vertex vertex) {
        return vertices.stream()
                .min((vertex1, vertex2) -> {
                    int comparatorMark = 0;
                    if (vertex1.measureDistanceBetweenVertices(vertex) < vertex2.measureDistanceBetweenVertices(vertex))
                        comparatorMark = -1;
                    else if (vertex1.measureDistanceBetweenVertices(vertex) > vertex2.measureDistanceBetweenVertices(vertex))
                        comparatorMark = 1;
                    return comparatorMark;
                }).orElse(null);
    }
}
