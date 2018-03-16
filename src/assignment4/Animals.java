package assignment4;
import assignment4.Critter.TestCritter;

public class Animals extends TestCritter {
		private static final int GENE_TOTAL = 24;
		private int[] genes = new int[8];
		private int dir;
		public Animals() {
			for (int k = 0; k < 8; k += 1) {
				genes[k] = GENE_TOTAL / 8;
			}
			dir = Critter.getRandomInt(8);
		}
		public String toString() { return "A"; }
		
		public boolean fight(String not_used) { return false; }
		
		public void doTimeStep() {
		}
}
