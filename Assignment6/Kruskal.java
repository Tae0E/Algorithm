import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class MyComp implements Comparator<Edge> {
	public int compare(Edge e1, Edge e2) {
		if (e1.weight < e2.weight)
			return -1;
		else if (e1.weight == e2.weight)
			return 0;
		else
			return 1;
	}
}

public class Kruskal {
	static HashMap<Integer, Integer> hm;
	
	public static void main(String[] args) {	
		Edge e;
		int N = 0;
		PriorityQueue<Edge> heap;
		final JFileChooser fc = new JFileChooser();	
		File file;
		
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				 file = fc.getSelectedFile();
		else  {
				JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
				return;
		}
		heap = new PriorityQueue<Edge>(new MyComp());	// min heap
		HashSet<Pairs> eSet = new HashSet<Pairs>(); 	// �ߺ��� ���� ����
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			if (sc.hasNext())
				N = sc.nextInt();
			
			while (sc.hasNext()) {
				int v1 = sc.nextInt();
				int v2 = sc.nextInt();
				int w = sc.nextInt();
				
				Pairs p = new Pairs(v1, v2);
				if (eSet.contains(p)) 
					continue;	// �ߺ��� ������ �ѹ��� �Է�
				
				eSet.add(p);
				e = new Edge(v1, v2, w);
				heap.add(e);
			}
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
		
		int edges = 0, esum = 0;
		int root1, root2;
		hm = new HashMap<Integer, Integer>();	// union-find ����
		while (heap.peek() != null && edges < N - 1) {	// weight�� ���� ���� �������� ��������...
			e = heap.poll();
			if (hm.get(e.v1) == null)		// ó������ �����ϴ� vertex�� ���, ������ root��...
				hm.put(e.v1, -1);
			if (hm.get(e.v2) == null)
				hm.put(e.v2, -1);
			
			root1 = find(e.v1);
			root2 = find(e.v2);	
			
			if (root1 == root2)
				continue;	// cycle �߻�
			
			edges++;
			esum += e.weight;
			System.out.println("���� �߰�: " + e);
			int val1 = hm.get(root1);		// (���� 1�� ���� ��) * -1
			int val2 = hm.get(root2);		// (���� 2�� ���� ��) * -1
			if (val1 > val2) {				// ���� 2�� ���Ұ� ���� ���
				hm.put(root1, root2);		// ���� 1�� ���� 2�� ����
				hm.put(root2, val1 + val2);
			}
			else {							// ���� 1�� ���Ұ� ���� ���
				hm.put(root2, root1);
				hm.put(root1, val1 + val2);
			}
		}
		
		if (edges == N - 1)
			System.out.println("����Ʈ�� ���� �Ϸ�: ������ �� = " + esum);
		else
			System.out.println("����Ʈ���� �������� �ʽ��ϴ�");
	}

	static int find(int v) {
		while (hm.get(v) > 0) {
			v = hm.get(v);
		}
		return v;
	}
}
