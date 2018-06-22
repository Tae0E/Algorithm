public class Edge {
	int v1, v2;
	int weight;
	
	public Edge(int v1, int v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = w;
	};
	
	public String toString() {
		return String.format("[%d, %d] weight = %d", v1, v2, weight);
	}
}
