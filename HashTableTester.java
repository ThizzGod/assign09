package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

class HashTableTester {
	HashTable<String, Integer> booksSold;
	HashTable<String, Integer> emptyMap;
	HashTable<Integer, Integer> largeTable;
	
	@BeforeEach
	void setUp() {
		booksSold = new HashTable<String, Integer>();
		booksSold.put("The Wind in the Willows", 50);
		booksSold.put("Blood Meridian", 25);
		booksSold.put("Children of Dune", 120);
		booksSold.put("Frankenstein", 80);
		
		emptyMap = new HashTable<String, Integer>();
		
		largeTable = new HashTable<Integer, Integer>();
		
		for (int i = -100; i < 100; i++) {
			largeTable.put(i, i);
		}
	}

	@Test
	void testPutStringKeyKeyDoesntExist() {
		assertEquals(null, booksSold.put("Don Quixote", 40));
		
		assertEquals(5, booksSold.size());
		assertTrue(booksSold.containsKey("Don Quixote"));
	}

	@Test
	void testPutStringKeyKeyDoesExist() {
		assertEquals(80, booksSold.put("Frankenstein", 100));
		
		assertEquals(4, booksSold.size());
		assertEquals(100, booksSold.get("Frankenstein"));
	}
	
	@Test void testRemoveStringKey() {
		assertEquals(80, booksSold.remove("Frankenstein"));
		
		assertEquals(3, booksSold.size());
		assertFalse(booksSold.containsKey("Frankenstein"));
	}
	
	@Test
	void testRemoveStringKeyEmptyMap() {
		assertEquals(null, emptyMap.remove("Frankenstein"));
	}
	
	@Test
	void testContainsStringKeyTrue() {
		assertTrue(booksSold.containsKey("Frankenstein"));
	}
	
	@Test
	void testContainsStringKeyFalse() {
		assertFalse(booksSold.containsKey("Frankenweenie"));
	}
	
	@Test
	void testContainsIntegerValueTrue() {
		assertTrue(booksSold.containsValue(80));
	}
	
	@Test
	void testContainsIntegerValueFalse() {
		assertFalse(booksSold.containsValue(1));
	}
	
	@Test
	void testContainsIntegerValueDuplicates() {
		booksSold.put("Naked Lunch", 80);
		assertTrue(booksSold.containsValue(80));
	}
	
	@Test
	void testClear() {
		booksSold.clear();
		
		assertEquals(0, booksSold.size());
		assertTrue(booksSold.isEmpty());
	}
	
	@Test
	void testClearAlreadyEmpty() {
		emptyMap.clear();
		
	    assertEquals(0, emptyMap.size());
	    assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	void testIsEmptyNotEmpty() {
		assertFalse(booksSold.isEmpty());
	}
	
	@Test
	void testEntries() {
		List<MapEntry<String, Integer>> list = booksSold.entries();
		
		assertTrue(list.contains(new MapEntry<String, Integer>("Frankenstein", 80)));
		assertTrue(list.contains(new MapEntry<String, Integer>("The Wind in the Willows", 50)));
		assertTrue(list.contains(new MapEntry<String, Integer>("Blood Meridian", 25)));
		assertTrue(list.contains(new MapEntry<String, Integer>("Children of Dune", 120)));
	}
	
	@Test
	void testEntriesEmptyList() {
		List<MapEntry<String, Integer>> list = emptyMap.entries();
		
		assertEquals(0, list.size());
	}
	
	@Test
	void testGet() {
		assertEquals(80, booksSold.get("Frankenstein"));
	}
	
	@Test
	void testGetNoSuchKey() {
		assertEquals(null, booksSold.get("Catch 22"));
	}
	
	//Set Rehash value to 1 before test
	
	@Test
	void testTableWorksAfterRehash() {
		assertEquals(-80, largeTable.get(-80));
		assertEquals(0, largeTable.get(0));
		assertEquals(null, largeTable.get(100));
	}
}
