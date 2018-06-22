import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TestClient {

	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		File file;
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();
		else
		{
			JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Scanner sc = null;
		try
		{
			sc = new Scanner(file);
			for (int i = 0; sc.hasNext(); i++)
			{
				String key = sc.next();
				st.put(key, i);
			}
			for (String s : st.keys())
				System.out.println(s + " " + st.get(s));
			System.out.println("가장 작은 키 = " + st.min());
			System.out.println("가장 큰 키 = " + st.max());
			System.out.println("rank = " + st.rank("f"));
			System.out.println("rank = " + st.rank("b"));
			System.out.println("select = " + st.select(3));
			System.out.println("select = " + st.select(1));
			System.out.println("select = " + st.select(0));
			System.out.println("floor = " + st.floor("a"));
			System.out.println("floor = " + st.floor("g"));
			System.out.println("floor = " + st.floor("z"));
			System.out.println("ceiling = " + st.ceiling("z"));
			System.out.println("ceiling = " + st.ceiling("a"));
			System.out.println("ceiling = " + st.ceiling("b"));
			System.out.println("size = " + st.size("a", "f"));
			System.out.println("size = " + st.size("b", "e"));
			System.out.println(st.keys());
			System.out.println(st.keys("i", "z"));
			System.out.println(st.keys("c", "e"));
			st.deleteMin();
			st.deleteMax();
			System.out.println("가장 작은 키 = " + st.min());
			System.out.println("가장 큰 키 = " + st.max());
			System.out.println(st.keys());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(sc != null)
			sc.close();
	}

}
