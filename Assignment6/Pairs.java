
public class Pairs {
	int x, y;
	public Pairs(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object o) {
		Pairs p = (Pairs) o;
		
		return (x == p.x && y == p.y) || (x == p.y && y == p.x);
	}
	
	public int hashCode() {
		return x * 31 + y;
	}
}
