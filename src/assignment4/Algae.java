package assignment4;


/*
 * Do not change or submit this file.
 */
import assignment4.Critter.TestCritter;

public class Algae extends TestCritter {
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;	
	
	public Algae() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
		permit_to_move = true;
	}
	@Override public String toString() { return "@"; }
	
	
	public boolean fight(String enemy) { 
			return false;
	}
	public static void TestMethod(String a) {
		return;
	}
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
		permit_to_move = true;
		walk(dir);
		permit_to_move = false;
		
		if(getEnergy()>=80) {
			Algae child = new Algae();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}
	public static void runStats(java.util.List<Critter> algaes) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : algaes) {
			Algae c = (Algae) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + algaes.size() + " total Algaes    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * algaes.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * algaes.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * algaes.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * algaes.size()) + "% left   ");
		System.out.println();
	}
}
