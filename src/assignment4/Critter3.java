package assignment4;
import assignment4.Critter.TestCritter;




public class Critter3 extends TestCritter{

    private static final int GENE_TOTAL = 16;
    private int[] genes = new int[8];
    private int dir;

    /**
     * Constructor of Critter1
     */
    public Critter3() {
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
    public String toString() { return "3"; }

    public boolean fight(String enemy) {
        return false;
    }

    /**
     * Critter walks in direction, reproduces and changes up gene count of offspring, changes direction
     */

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
        permit_to_move = true;
        walk(dir);
        permit_to_move = false;

        if (getEnergy() >= 30) {
            Critter3 child = new Critter3();
            for (int k = 0; k < 8; k += 1) {
                child.genes[k] = this.genes[k];
            }
            int g = Critter.getRandomInt(8);
            while (child.genes[g] == 0) {
                g = Critter.getRandomInt(8);
            }
            child.genes[g] -= 1;
            g = Critter.getRandomInt(8);
            child.genes[g] += 2;
            reproduce(child, Critter.getRandomInt(8));
        }

        int roll = Critter.getRandomInt(GENE_TOTAL);

        dir = roll  % 8;
    }

}
