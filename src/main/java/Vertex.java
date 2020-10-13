import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(exclude = {"xAxis", "yAxis"}, includeFieldNames = false)
public class Vertex {
    private final String name;
    private final double xAxis;
    private final double yAxis;

    public double measureDistanceBetweenVertices(final Vertex vertex){
        double ac = Math.abs(vertex.yAxis - this.getYAxis());
        double cb = Math.abs(vertex.xAxis - this.getXAxis());

        return Math.hypot(ac, cb);
    }
}
