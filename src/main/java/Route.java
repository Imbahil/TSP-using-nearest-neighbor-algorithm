import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
public class Route {
    private final ArrayList<Vertex> vertices = new ArrayList<>();

    public Route(ArrayList<Vertex> vertices) {
        this.vertices.addAll(vertices);
    }

    public double calculateTotalDistance() {
        int verticesSize = this.getVertices().size();
        return  (this.getVertices().stream()
                .mapToDouble(x -> {
            int vertexIndex = this.getVertices().indexOf(x);
            double returnValue = 0;
            if (vertexIndex < verticesSize - 1) {
                returnValue = x.measureDistanceBetweenVertices((this.getVertices()).get(vertexIndex + 1)); //calculate distance between vertex 1 and vertex 2 vertex 2 and vertex 3 etc. and sum them up
            }
            return returnValue;
        }).sum() + this.getVertices().get(verticesSize - 1).measureDistanceBetweenVertices(this.getVertices().get(0))); //adding way back to initial vertex
    }
}

