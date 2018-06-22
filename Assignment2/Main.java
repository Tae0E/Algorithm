import java.util.*;
public class Main{

	public static void main(String[] args)
	{
		int n=0;
		long start, end;
		Random rand = new Random();
		

		
		Scanner scan = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("n을 입력?(-1누르면 종료)");
		n=scan.nextInt();

		while(n != -1)
		{
			Integer[] A = new Integer[n];
			Integer[] B = new Integer[n];
			Integer[] C = new Integer[n];
			for(int i = 0; i<n; i++)
				A[i] = rand.nextInt(10000);
			System.arraycopy(A, 0, B, 0, A.length);
			System.arraycopy(A, 0, C, 0, A.length);
			
			start = System.currentTimeMillis();
			Shell.sort(A);
			end = System.currentTimeMillis();
			System.out.println("Shell Sort : " + (end-start)/1000.0 + "초");
			
			start = System.currentTimeMillis();
			MergeTD.sort(B);
			end = System.currentTimeMillis();
			System.out.println("Top Down Merge Sort : " + (end-start)/1000.0 + "초");
			
			start = System.currentTimeMillis();
			MergeBU.sort(C);
			end = System.currentTimeMillis();
			System.out.println("Bottom Up Merge Sort : " + (end-start)/1000.0 + "초");
			
			System.out.print("n을 입력?(-1누르면 종료)");
			n=scanner.nextInt();
		}
		
	}
}
