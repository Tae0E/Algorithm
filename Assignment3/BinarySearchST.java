import java.util.ArrayList;

public class BinarySearchST <K extends Comparable<K>, V>
{
	private static final int INIT_CAPACITY = 10;
	private K[] keys;
	private V[] vals;
	private int N;
	
	public BinarySearchST()
	{
		keys = (K[]) new Comparable[INIT_CAPACITY];
		vals = (V[]) new Object[INIT_CAPACITY];
	}
	public BinarySearchST(int capacity)
	{
		keys = (K[]) new Comparable[capacity];
		vals = (V[]) new Object[capacity];
	}
	
	public boolean contains(K key)	{ return get(key) != null; }
	public boolean isEmpty()	{ return N == 0; }
	public int size()	{ return N; }
	private void resize(int capacity)
	{
		K[] tempk = (K[]) new Comparable[capacity];
		V[] tempv = (V[]) new Object[capacity];
		for(int i = 0; i<N; i++)
		{
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}
	private int search(K key)
	{
		int lo = 0;
		int hi = N-1;
		while(lo <= hi)
		{
			int mid = (hi+lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}
	public V get(K key)
	{
		if(isEmpty())	
			return null;
		int i = search(key);
		if(i < N && keys[i].compareTo(key) == 0)	
			return vals[i];
		else 
			return null;
	}
	public void put(K key, V value)
	{
		int i = search(key);
		if(i<N && keys[i].compareTo(key) == 0)
		{
			vals[i] = value;
			return;
		}
		if(N == keys.length)
			resize(2 * keys.length);
		
		for(int j = N; j>i; j--)
		{
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		
		keys[i] = key;
		vals[i] = value;
		N++;
	}
	public void delete(K key)
	{
		if(isEmpty())	
			return;
		int i = search(key);
		if(i == N || keys[i].compareTo(key) != 0)	
			return;
		
		for(int j = i; j < N - 1; j++)
		{
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;
		if(N > 0 && N == keys.length/4)
			resize(keys.length/2);
	}

	public K min()
	{
		if(isEmpty())	
			return null;
		return keys[0];
	}
	
	public K max()
	{
		if(isEmpty())	
			return null;
		return keys[N-1];
	}

	public K floor(K key)
	{
		if(isEmpty())	
			return null;
		int i = search(key);
		if(key.compareTo(keys[0]) < 0)
			return null;
		else if(key.compareTo(keys[N-1]) > 0)
			return keys[i-1];
		if(key.compareTo(keys[i]) == 0)
			return keys[i];
		else
			return keys[i-1];
	}

	public K ceiling(K key)
	{
		if(isEmpty())	
			return null;
		int i = search(key);
		if(i >= N)
			return null;
		return keys[i];
	}

	
	public int rank(K key)
	{
		int cnt=0;
		if(isEmpty())	
			return 0;
		int i = search(key);
		if(i == 0)
			return 0;
		while(i>=0)
		{
			if(keys[i].compareTo(key) == 0)
				i--;
			else if(keys[i].compareTo(key) < 0)
			{
				i--;
				cnt++;
			}	
		}
		return cnt;
	}

	public K select(int r)
	{
		if(isEmpty())	
			return null;
		if(r < N)
			return keys[r];
		else
			return null;
	}

	public void deleteMin()
	{
		if(isEmpty())	
			return;
		delete(min());
	}
	public void deleteMax()
	{
		if(isEmpty())	
			return;
		delete(max());
	}

	public int size(K lo, K hi)
	{
		int low = search(lo);
		int high = search(hi);
		if(high >= N)
			return rank(keys[high-1])-rank(keys[low])+1;
		if(lo.compareTo(keys[low]) == 0 && hi.compareTo(keys[high]) == 0)
			return rank(keys[high])-rank(keys[low])+1;
		else if(lo.compareTo(keys[low]) != 0 && hi.compareTo(keys[high]) == 0)
			return rank(keys[high])-rank(keys[low])+1;
		else if(lo.compareTo(keys[low]) == 0 && hi.compareTo(keys[high]) != 0)
			return rank(keys[high-1])-rank(keys[low])+1;
		else
			return rank(keys[high-1])-rank(keys[low])+1;
	}

	public Iterable<K> keys()
	{
		ArrayList<K> keyList = new ArrayList<K>(N);
		for(int i = 0; i < N; i++)
			keyList.add(keys[i]);
		return keyList;
	}

	public Iterable<K> keys(K lo, K hi)
	{
		int low = search(lo);
		int high = search(hi);
		ArrayList<K> keyList = new ArrayList<K>(N);
		if(high >= N)
		{
			for(int i = low; i < high; i++)
				keyList.add(keys[i]);
		}
		else
		{
			if(lo.compareTo(keys[low]) == 0 && hi.compareTo(keys[high]) == 0)
			{
				for(int i = low; i <= high; i++)
					keyList.add(keys[i]);
			}
			else if(lo.compareTo(keys[low]) != 0 && hi.compareTo(keys[high]) == 0)
			{
				for(int i = low; i <= high; i++)
					keyList.add(keys[i]);
			}
			else if(lo.compareTo(keys[low]) == 0 && hi.compareTo(keys[high]) != 0)
			{
				for(int i = low; i < high; i++)
					keyList.add(keys[i]);
			}
			else
			{
				for(int i = low; i < high; i++)
					keyList.add(keys[i]);
			}
		}
		return keyList;
		
	}
}
