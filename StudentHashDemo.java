package assign09;

/**
 * This class demonstrates how to use a hash table to store key-value pairs.
 * 
 * @author Erin Parker
 * @version March 24, 2021
 */
public class StudentHashDemo {

	public static void main(String[] args) {
		
		StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
		StudentBadHash ada = new StudentBadHash(1004203, "Ada", "Lovelace");
		StudentBadHash edsger = new StudentBadHash(1010661, "Edsger", "Dijkstra");
		StudentBadHash grace = new StudentBadHash(1019941, "Grace", "Hopper");

		HashTable<StudentBadHash, Double> studentGpaTable = new HashTable<StudentBadHash, Double>();
		studentGpaTable.put(alan, 3.2);
		studentGpaTable.put(ada, 3.5);
		studentGpaTable.put(edsger, 3.8);
		studentGpaTable.put(grace, 4.0);
		
		for(MapEntry<StudentBadHash, Double> e : studentGpaTable.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + " " + e.getKey().hashCode() + ".");
		
		StudentMediumHash alanMedium = new StudentMediumHash(1019999, "Alan", "Turing");
		StudentMediumHash adaMedium = new StudentMediumHash(1004203, "Ada", "Lovelace");
		StudentMediumHash edsgerMedium = new StudentMediumHash(1010661, "Edsger", "Dijkstra");
		StudentMediumHash graceMedium = new StudentMediumHash(1019941, "Grace", "Hopper");

		HashTable<StudentMediumHash, Double> studentGpaTableMedium = new HashTable<StudentMediumHash, Double>();
		studentGpaTableMedium.put(alanMedium, 3.2);
		studentGpaTableMedium.put(adaMedium, 3.5);
		studentGpaTableMedium.put(edsgerMedium, 3.8);
		studentGpaTableMedium.put(graceMedium, 4.0);
		
		for(MapEntry<StudentMediumHash, Double> e : studentGpaTableMedium.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + " " + e.getKey().hashCode() +".");

		StudentGoodHash alanGood = new StudentGoodHash(1019999, "Alan", "Turing");
		StudentGoodHash adaGood = new StudentGoodHash(1004203, "Ada", "Lovelace");
		StudentGoodHash edsgerGood = new StudentGoodHash(1010661, "Edsger", "Dijkstra");
		StudentGoodHash graceGood = new StudentGoodHash(1019941, "Grace", "Hopper");

		HashTable<StudentGoodHash, Double> studentGpaTableGood = new HashTable<StudentGoodHash, Double>();
		studentGpaTableGood.put(alanGood, 3.2);
		studentGpaTableGood.put(adaGood, 3.5);
		studentGpaTableGood.put(edsgerGood, 3.8);
		studentGpaTableGood.put(graceGood, 4.0);
		
		for(MapEntry<StudentGoodHash, Double> e : studentGpaTableGood.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + " " + e.getKey().hashCode() + "." );

	}
}
