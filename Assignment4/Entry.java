
public class Entry <K, V> extends BST
{
	K key;
	V value;
	int N;
	int aux;
	Entry <K, V> parent, left, right;

	public Entry(K key, V value)
	{
		this.key = key;
		this.value = value;
		this.N = 1;
	}
	
	public int getAux()
	{
		return aux;
	}
	public void setAux(int value)
	{
		aux =  value;
	}
	
	public K getKey()
	{
		return this.key;
	}
	public V getValue()
	{
		return this.value;
	}
	
}
