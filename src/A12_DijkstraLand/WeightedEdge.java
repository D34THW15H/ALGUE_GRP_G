package A12_DijkstraLand;

public class WeightedEdge {
	public int vertex;
	public int weight;
	int to_vertex;

	public WeightedEdge(int v, int to_vertex, int weight) {
		this.vertex = v;
		this.to_vertex = to_vertex;
		this.weight = weight;
	}
}
