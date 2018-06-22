import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class Bucket {
	int id;
	int nums;
	ArrayList<Integer> items;
}

class ItemComp implements Comparator<Map.Entry<Integer, Integer>> {
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}
}

class PairComp implements Comparator<Map.Entry<Pairs, Integer>> {
	public int compare(Entry<Pairs, Integer> o1, Entry<Pairs, Integer> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}
}

public class TopK {
	static int topK, nBuckets;
	static HashMap<Pairs, Integer> pairMap = new HashMap<>();
	static HashMap<Integer, Integer> itemMap = new HashMap<>();
	static ArrayList<Bucket> bArray;
	static ArrayList<Map.Entry<Integer, Integer>> alItem;
	static ArrayList<Map.Entry<Pairs, Integer>> alPair;
	
	public static void main(String[] args) {
		final JFileChooser fc = new JFileChooser();		// ���� ���ñ⸦ ���
		File file;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				 file = fc.getSelectedFile();
		else  {
				JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
				return;
		}
		
		Scanner sc = null;
		
		sc = new Scanner(System.in);
		System.out.print("Top-k�� ��? ");
		topK = sc.nextInt();
		sc.close();
		
		Pairs pa = new Pairs(-1, -1);
		int i, j, k;
		
		Bucket bk;
		
		try {
			sc = new Scanner(file);
			nBuckets = sc.nextInt();
			bArray = new ArrayList<>(nBuckets);
			for (i = 0; i < nBuckets; i++) {
				bk = new Bucket();
				bk.id = sc.nextInt();
				bk.nums = sc.nextInt();
				bk.items = new ArrayList<>(bk.nums);
				for (j = 0; j < bk.nums; j++) {
					int item = sc.nextInt();
					bk.items.add(item);
					if (itemMap.containsKey(item))
						itemMap.put(item, itemMap.get(item) + 1);	// ��ǰ�� ���� �� ����
					else
						itemMap.put(item, 1);
				}
				Collections.sort(bk.items);
				for (j = 0; j < bk.nums - 1; j++) {
					for (k = j + 1; k < bk.nums; k++) {
						pa.small = bk.items.get(j);
						pa.large = bk.items.get(k);
						if (pairMap.containsKey(pa))
							pairMap.put(pa, pairMap.get(pa) + 1);
						else {
							Pairs new_pa = new Pairs(pa.small, pa.large);
							pairMap.put(new_pa, 1);
						}
					}
				}
				bArray.add(bk);
			}
			
			System.out.println("\n���� ���� ������ " + topK + "���� ��ǰ ����Ʈ");
			alItem = new ArrayList<>(itemMap.entrySet());
			Collections.sort(alItem, new ItemComp());
			for (i = 0; i < topK; i++)
				System.out.printf("%d: ��ǰ id = %d, ���ż� = %d\n",  i+1, alItem.get(i).getKey(), alItem.get(i).getValue());
			while (alItem.get(i-1).getValue() == alItem.get(i).getValue()) {
				System.out.printf("%d: ��ǰ id = %d, ���ż� = %d\n",  i+1, alItem.get(i).getKey(), alItem.get(i).getValue());
				i++;
			}
			
			System.out.println("\n���� ���� ���ÿ� ������ " + topK + "���� ��ǰ�� ����Ʈ");
			alPair = new ArrayList<>(pairMap.entrySet());
			Collections.sort(alPair, new PairComp());
			for (i = 0; i < topK; i++) {
				System.out.print((i+1) + ": ��ǰ�� = " + alPair.get(i).getKey() + " ���ż� = " + alPair.get(i).getValue());
				System.out.printf("  (%d�� ���ż� = %d, %d�� ���ż� = %d)\n", 
						alPair.get(i).getKey().small, itemMap.get(alPair.get(i).getKey().small),
						alPair.get(i).getKey().large, itemMap.get(alPair.get(i).getKey().large));
			}
			while (alPair.get(i-1).getValue().equals(alPair.get(i).getValue())) {
				System.out.print((i+1) + ": ��ǰ�� = " + alPair.get(i).getKey() + " ���ż� = " + alPair.get(i).getValue());
				System.out.printf("  (%d�� ���ż� = %d, %d�� ���ż� = %d)\n", 
						alPair.get(i).getKey().small, itemMap.get(alPair.get(i).getKey().small),
						alPair.get(i).getKey().large, itemMap.get(alPair.get(i).getKey().large));
				i++;
			}
			
		} catch (FileNotFoundException e) { e.printStackTrace(); }

		if (sc != null)
			sc.close();
	}
}
