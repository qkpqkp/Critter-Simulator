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
	public String toString() { return "Algae"; }
	
	
	public boolean fight(String enemy) { 
			int roll1 = Critter.getRandomInt(GENE_TOTAL);
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
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
		permit_to_move = true;
		walk(dir);
		permit_to_move = false;
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
