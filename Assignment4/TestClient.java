import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TestClient{

	public static void main(String[] args) {
		BST<String, Integer> bst = new BST<String, Integer>();
		File file;
		int num;
		int value;
		String keys;
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();
		else
		{
			JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Scanner sc = null;
		try
		{
			sc = new Scanner(file);
			for (int i = 0; sc.hasNext(); i++)
			{
				String key = sc.next();
				bst.put(key, i);
			}
			for(String s : bst.keys())
				System.out.println(s + " " + bst.get(s));
			System.out.println("(1. containsKey  2. containsValue  3. Iterable Keys  4. Iterable descendingKeys  5. subTree  6.pollFirstEntry)");
			System.out.print("���ϴ� �޴��� �Է��ϼ��� : ");
			num = input.nextInt();
			while(num != -1)
			{
				
				if(num == 1)
				{
					System.out.print("Ű�� �Է��� �ּ��� : ");
					keys = input2.nextLine();
					System.out.println("containsKey(" + keys + ") = " + bst.containsKey(keys));
					System.out.println();
				}
				else if(num == 2)
				{
					System.out.print("Value�� �Է��� �ּ��� : ");
					value = input.nextInt();
					System.out.println("containsValue(" + value + ") = " + bst.containsValue(value));
					System.out.println();
				}
				else if(num == 3)
				{
					System.out.println("Keys = " + bst.keys());
					System.out.println();
				}
				else if(num == 4)
				{
					System.out.println("Descending Keys = " + bst.descendingKeys());
					System.out.println();
				}
				else if(num == 5)
				{
					System.out.print("Ű�� �Է��� �ּ��� : ");
					keys = input2.nextLine();
					System.out.println("subTree = " + bst.subTree(keys).keys());
					System.out.println();
				}
				else if(num == 6)
				{
					System.out.println("pollFirstEntry = " + bst.pollFirstEntry().getKey());
					System.out.println();
				}
				else
				{
					break;
				}
				System.out.println("(1. containsKey  2. containsValue  3. Iterable Keys  4. Iterable descendingKeys  5. subTree  6.pollFirstEntry)");
				System.out.print("���ϴ� �޴��� �Է��ϼ��� : ");
				num = input.nextInt();
			}
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(sc != null)
			sc.close();
	}

}
