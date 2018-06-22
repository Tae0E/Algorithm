import java.util.ArrayList;

public class LinearProbingHashST <K, V>{
	private int N;
	private int M;
	private K[] keys;
	private V[] vals;
	
	public LinearProbingHashST()
	{
		this(31);
	}
	public LinearProbingHashST(int M)
	{
		keys = (K[]) new Object[M];
		vals = (V[]) new Object[M];
		this.M = M;
	}
	
	public boolean contains(K key)
	{
		return get(key) != null;
	}
	public boolean isEmpty()
	{
		return N == 0;
	}
	public int size()
	{
		return N;
	}
	
	public int hash(K key)//private
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public void resize(int cap)//private
	{
		LinearProbingHashST<K, V> t;
		t = new LinearProbingHashST<>(cap);
		for(int i = 0; i < M; i++)
			if(keys[i] != null)
				t.put(keys[i],  vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
	public V get(K key)
	{
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if(keys[i].equals(key))
				return vals[i];
		return null;
	}
	
	public void put(K key, V value)
	{
		if(N >= M / 2)
			resize(2 * M + 1);
		int i;
		for(i = hash(key); keys[i] != null; i = (i+1) % M)
			if(keys[i].equals(key))
			{
				vals[i] = value;
				return;
			}
		keys[i] = key;
		vals[i] = value;
		N++;
	}
	
	public void delete(K key)
	{
		if(!contains(key))
			return;
		
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i+1) % M;
		
		int num = i;
		keys[i] = null;
		vals[i] = null;
		
		for(i=(i+1)%M; keys[i] != null; i = (i+1)%M);
		i--;
		int j = i;
		
		while(keys[j] != null)
		{
			K keyToRehash = keys[j];
			V valToRehash = vals[j];
			if(hash(keys[j]) <= num)
			{
				keys[j] = null;
				vals[j] = null;
				put(keyToRehash, valToRehash);
				num = j;
				j=i;
			}
			else
				j=(j-1)%M;
		}
		N--;
		if(N<M/8) 
			resize(M/2+1);
	}

	public Iterable<K> keys()
	{
		ArrayList<K> keyList = new ArrayList<K>();
		for (int i = 0; i < M; i++)
		{
			if(keys[i] != null)
				keyList.add(keys[i]);
		}
		return keyList;
	}
	
	public ArrayList hashs()
	{
		ArrayList keyList = new ArrayList();
		for (int i = 0; i < M; i++)
		{
			if(keys[i] != null)
				keyList.add(hash(keys[i]));
		}
		return keyList;
	}
}
