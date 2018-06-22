import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FrequencyCounter {

	public static void main(String[] args) {
		LinearProbingHashST <String, Integer> lphst = new LinearProbingHashST <String, Integer>();
		File file;
		
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
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

			long start = System.currentTimeMillis();
			while(sc.hasNext())
			{
				String word = sc.next();
				if(!lphst.contains(word))
					lphst.put(word, 1);
				else
					lphst.put(word, lphst.get(word)+1);
			}
			String maxKey = "";
			int maxValue = 0;
			for(String word : lphst.keys())
			{
				if(lphst.get(word) > maxValue)
				{
					maxValue = lphst.get(word);
					maxKey = word;
				}
			}
			
			System.out.println("전체");
			System.out.println("(maxKey, maxValue) (" + maxKey + ", " + maxValue + ")");
			System.out.println(lphst.keys());
			System.out.println(lphst.hashs());
			System.out.println();
			
			for(int i = 0; i<5; i++)
			{
				System.out.println("maxKey " + maxKey + " 삭제");
				lphst.delete(maxKey);
			
				maxKey = "";
				maxValue = 0;
				for(String word : lphst.keys())
				{
					if(lphst.get(word) > maxValue)
					{
						maxValue = lphst.get(word);
						maxKey = word;
					}
				}
				System.out.println("(maxKey, maxValue) (" + maxKey + ", " + maxValue + ")");
				System.out.println(lphst.keys());
				System.out.println(lphst.hashs());
				System.out.println();
			}
			
			long end = System.currentTimeMillis();
			System.out.println("소요 시간 = " + (end-start) + "ms");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(sc != null)
			sc.close();
	}

}
