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
	@BeforeEach
	void setUp() {
		booksSold = new HashTable<String, Integer>();
		booksSold.put("The Wind in the Willows", 50);
		booksSold.put("Blood Meridian", 25);
		booksSold.put("Children of Dune", 120);
		booksSold.put("Frankenstein", 80);
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
	}
	
	@Test
	void testClearAlreadyEmpty() {
		emptyMap.clear();
		
		assertEquals(0, booksSold.size());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	void testIsEmptyNotEmpty() {
		assertFalse(emptyMap.isEmpty());
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
}
