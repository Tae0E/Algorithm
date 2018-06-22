import java.util.Random;
public class MyPoint implements Comparable<MyPoint>{
	private int x;
	private int y;
	public MyPoint(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int compareTo(MyPoint mp)
	{
		if(this.x < mp.x)
			return -1;
		if(this.x > mp.x)
			return 1;
		if(this.y < mp.y)
			return -1;
		if(this.y > mp.y)
			return 1;
		return 0;
	}
	
	@Override
	public String toString()
	{
		return "x = " + x + "  " + "y = " + y + "\n";
	}
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		MyPoint[] mp = new MyPoint[100];
		
		
		for(int i = 0; i<100; i++)
		{
			mp[i] = new MyPoint(rand.nextInt(100), rand.nextInt(100));
		}
		System.out.println("Insertion Sort");
		Insertion.sort(mp);
		Insertion.show(mp);
		System.out.println();
		System.out.println("Selection Sort");
		Selection.sort(mp);
		Selection.show(mp);
	}
}
