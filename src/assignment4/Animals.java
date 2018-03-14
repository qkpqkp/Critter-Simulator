package assignment4;
import assignment4.Critter.TestCritter;

public class Animals extends TestCritter {

		public String toString() { return "@"; }
		
		public boolean fight(String not_used) { return false; }
		
		public void doTimeStep() {
			setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
		}
}
