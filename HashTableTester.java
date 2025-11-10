package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTester {
	HashTable<String, Integer> booksSold;
	@BeforeEach
	void setUp() {
		booksSold = new HashTable<String, Integer>();
		booksSold.put("The Wind in the Willows", 50);
		booksSold.put("Blood Meridian", 25);
		booksSold.put("Children of Dune", 120);
		booksSold.put("Frankenstein", 80);
	}

	@Test
	void testPutStringKey() {
		booksSold.put("Don Quixote", 40);
		
		assertEquals(5, booksSold.size());
		assertTrue(booksSold.containsKey("Don Quixote"));
	}

}
