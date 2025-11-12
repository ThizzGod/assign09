package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a hash table that maps keys to values using the separate chaining method
 * 
 * @author Max Barker and Josi Gac
 * @version 11/11/25
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> implements Map<K, V>{
	int size;
	int capacity;
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	
	public HashTable() {
		this.size = 0;
		this.capacity = 100;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>(capacity);
		for(int i = 0; i < capacity; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
	}
	

	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length)
	 */
	public void clear() {
		this.size = 0;
		for(int i = 0; i < capacity; i++) {
		   table.set(i, new LinkedList<MapEntry<K, V>>());
		}
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		if (this.get(key) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length)
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {
		for (LinkedList<MapEntry<K, V>> bucket : table) {
		    for (MapEntry<K, V> entry : bucket) {
		        if (value.equals(entry.getValue())) {
		            return true;
		        }
		    }
		}
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length)
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries(){
		ArrayList<MapEntry<K, V>> entries = new ArrayList<MapEntry<K,V>>();
		for (LinkedList<MapEntry<K, V>> list : table) {
			for (MapEntry<K, V> entry: list) {
				entries.add(entry);
			}
		}
		return entries;
    }

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {
		int position = Math.abs(key.hashCode() % capacity);
		for (MapEntry<K, V> item : table.get(position)) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1)
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}


	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value{
	 *  
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {
		int position = Math.abs(key.hashCode() % capacity);
		
		if (this.containsKey(key)) {
			for (MapEntry<K, V> item : table.get(position)) {
				if (item.getKey().equals(key)) {
					V previous = item.getValue();
					item.setValue(value);
					return previous;
				}
			}
		}
		
		table.get(position).add(new MapEntry<K, V>(key, value));
		size++;
		if (this.calculateLoad() > 1) {
			this.rehash();
		}
		
		return null;
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1)
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {
        int position = Math.abs(key.hashCode() % capacity);
        LinkedList<MapEntry<K, V>> bucket = table.get(position);

        for (MapEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                bucket.remove(entry);
                size--;
                return oldValue;
            }
        }
        return null;
    }

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1)
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Calculates and returns the load factor of the hash table
	 * 
	 * @return the load factor
	 */
	private double calculateLoad() {
		return this.size / this.capacity;
	}
	
	/**
	 * Rebuilds the hash table
	 */
	private void rehash() {
		ArrayList<LinkedList<MapEntry<K, V>>> newTable = new ArrayList<LinkedList<MapEntry<K,V>>>();
		capacity *=2;
		for(int i = 0; i < capacity; i++)
			newTable.add(new LinkedList<MapEntry<K, V>>());
		
		List<MapEntry<K, V>> entries = this.entries();
		for (MapEntry<K, V> entry: entries ) {
			int position = Math.abs(entry.getKey().hashCode() % capacity);
			newTable.get(position).add(entry);
		}
		this.table = newTable;
	}
}
