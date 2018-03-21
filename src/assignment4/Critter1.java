package assignment4;

import assignment4.Critter.TestCritter;
/**
 * 
 * @author Kunpeng Qin
 *
 */
public class Critter1 extends TestCritter {
	private static final int GENE_TOTAL = 32;
	private int[] genes = new int[8];
	private int dir;	
	/**
	 * Constructor of Critter1
	 */
	public Critter1() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
		permit_to_move = true;
	}
	/**
	 * return the representation of this critter
	 */
	@Override 
	public String toString() { return "1"; }
	
	/**
	 * Decide fight or not, if not, try run or walk away
	 * @return Decide to fight or not
	 * @param Enemy critter
	 */
	public boolean fight(String enemy) { 
			int roll1 = Critter.getRandomInt(GENE_TOTAL);
			
			if(enemy.equals("Algae")) {
				return true;
			}
			//decide to walk away
			if(roll1<=6) {
				walk(dir);
				//Other critters are in this position
				for(int i = 0 ;i < getPopulation().size();i++) {
					if(super.getX_coord()==getPopulation().get(i).getX()) {
						if(dir<4) {
							dir+=4;
							walk(dir);
							super.setEnergy(super.getEnergy()+Params.walk_energy_cost);
						}
						else if(dir>=4) {
							dir-=4;
							walk(dir);
							super.setEnergy(super.getEnergy()+Params.walk_energy_cost);
						}
					}
				}
				permit_to_move = false;
				//change direction
				int roll2 = Critter.getRandomInt(GENE_TOTAL);
				int turn = 0;
				while (genes[turn] <= roll2) {
					roll2 = roll2 - genes[turn];
					turn = turn + 1;
				}
				assert(turn < 8);
				
				dir = (dir + turn) % 8;
				return false;
			}
			//decide to run
			else if(roll1>6&&roll1<=12){
				run(dir);
				//other critters are in this position
				for(int i = 0 ;i < getPopulation().size();i++) {
					if(super.getX_coord()==getPopulation().get(i).getX()) {
						if(dir<4) {
							dir+=4;
							run(dir);
							super.setEnergy(super.getEnergy()+Params.run_energy_cost);
						}
						else if(dir>=4) {
							dir-=4;
							run(dir);
							super.setEnergy(super.getEnergy()+Params.run_energy_cost);
						}
					}
				}
				permit_to_move = false;
				//change direction
				int roll2 = Critter.getRandomInt(GENE_TOTAL);
				int turn = 0;
				while (genes[turn] <= roll2) {
					roll2 = roll2 - genes[turn];
					turn = turn + 1;
				}
				assert(turn < 8);
				
				dir = (dir + turn) % 8;
				return false;
			}
			else {
				return true;
			}
	}
	/**
	 * Walk, run or reproduce during each time step
	 */
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
		permit_to_move = true;
		walk(dir);
		permit_to_move = false;
		
		if(getEnergy()>=80) {
			Critter1 child = new Critter1();
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
}
