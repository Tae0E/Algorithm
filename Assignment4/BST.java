import java.util.ArrayList;

public class BST <K extends Comparable<K>, V>{

	protected Entry<K, V> root;
	protected Entry<K, V> treeSearch(K key)
	{
		Entry<K, V> x = root;
		while(true)
		{
			int cmp = key.compareTo(x.key);
			if (cmp == 0 || isLeaf(x))
				return x;
			else if(cmp < 0)
			{
				if (x.left == null)
					return x;
				else
					x = x.left;
			}
			else
			{
				if (x.right == null)
					return x;
				else
					x = x.right;
			}
		}
	}
	protected boolean isLeaf(Entry<K, V> x)
	{
		return x.left == null && x.right == null;
	}
	public int size()
	{
		return (root != null) ? root.N : 0;
	}
	public V get(K key)
	{
		if (root == null)
			return null;
		Entry<K, V> x = treeSearch(key);
		if(key.equals(x.key))
			return x.value;
		else
			return null;
	}
	
	public void put(K key, V val)
	{
		if (root == null)
		{
			root = new Entry<K, V>(key, val);
			return;
		}
		Entry<K, V> x = treeSearch(key);
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
			x.value = val;
		else
		{
			Entry<K, V> newNode = new Entry<K, V>(key, val);
			if(cmp < 0)
				x.left = newNode;
			else
				x.right = newNode;
			newNode.parent = x;
			rebalanceInsert(newNode);
		}
	}
	protected void rebalanceInsert(Entry<K, V> x)
	{
		resetSize(x.parent, 1);
	}
	protected void rebalanceDelete(Entry<K, V> p, Entry<K, V> deleted)
	{
		resetSize(p, -1);
	}
	private void resetSize(Entry<K, V> x, int value)
	{
		for(; x != null; x = x.parent)
			x.N += value;
	}
	public Iterable<K> keys()
	{
		if (root == null)
			return null;
		ArrayList<K> keyList = new ArrayList<K>(size());
		inorder(root, keyList);
		return keyList;
	}
	private void inorder(Entry<K, V> x, ArrayList<K> keyList)
	{
		if(x != null)
		{
			inorder(x.left, keyList);
			keyList.add(x.key);
			inorder(x.right, keyList);
		}
	}
	public boolean isTwoNode(Entry<K, V> x)
	{
		if(x.left != null && x.right != null)
			return true;
		else
			return false;
	}
	protected void relink(Entry<K, V> parent, Entry<K, V> child, boolean makeLeft)
	{
		if (child != null)
			child.parent = parent;
		if (makeLeft)
			parent.left = child;
		else
			parent.right = child;
	}
	public void delete(K key)
	{
		if (root == null)
			return;
		Entry<K, V> x, y, p;
		x = treeSearch(key);
		
		if (!key.equals(x.key))
			return;
		
		if(x == root || isTwoNode(x))
		{
			if (isLeaf(x))
			{
				root = null;
				return;
			}
			else if(!isTwoNode(x))
			{
				root = (x.right == null) ?
						x.left : x.right;
				root.parent = null;
				rebalanceDelete(null, null);
			}
			else
			{
				y = min(x.right);
				x.key = y.key;
				x.value = y.value;
				p = y.parent;
				relink(p, y.right, y==p.left);
				rebalanceDelete(p, y);
			}
		}
		else
		{
			p = x.parent;
			if(x.right == null)
				relink(p, x.left, x == p.left);
			else if(x.left == null)
				relink(p, x.right, x == p.left);
			rebalanceDelete(p, x);
		}

	}
	boolean containsKey(K key)
	{
		if(get(key) != null)
			return true;
		else
			return false;
	}
	boolean containsValue(V value)
	{
		ArrayList<K> keyList = new ArrayList<K>(size());
		inorder(root, keyList);
		int i = 0;
		while(i != keyList.size())
		{
			if(get(keyList.get(i)) == value)
				return true;
			i++;
		}
		return false;
		
	}
	public Entry<K, V> min(Entry<K, V> x)
	{
		if (root == null)
			return null;
		x = root;
		while(x.left != null)
			x = x.left;
		return x;
	}
	public Entry<K, V> min()
	{
		if (root == null)
			return null;
		Entry<K, V> x = root;
		while(x.left != null)
			x = x.left;
		return x;
	}
	public Iterable<K> descendingKeys()
	{
		if (root == null)
			return null;
		ArrayList<K> keyList = new ArrayList<K>(size());
		order(root, keyList);
		return keyList;
	}
	private void order(Entry<K, V> x, ArrayList<K> keyList)
	{
		if(x != null)
		{
			order(x.right, keyList);
			keyList.add(x.key);
			order(x.left, keyList);
		}
	}
	public BST<K, V> subTree(K key)
	{
		ArrayList<K> keyList = new ArrayList<K>(size());
		BST<K, V> bst = new BST<K, V>();
		inorder(root, keyList);
		int i = 0;
		while(true)
		{
			bst.put(keyList.get(i), get(keyList.get(i)));
			if(keyList.get(i).compareTo(key) == 0)
				break;
			i++;
		}
		return bst;
		
	}
	public Entry<K, V> pollFirstEntry()
	{
		Entry<K, V> temp = min();
		delete(min().key);
		return temp;
	}
}
