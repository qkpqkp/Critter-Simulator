package assignment4;

import assignment4.Critter.TestCritter;

//does not reproduce, only runs

public class Critter4 extends TestCritter{
    private static final int GENE_TOTAL = 72;
    private int[] genes = new int[8];
    private int dir;

    /**
     * Constructor of Critter1
     */
    public Critter4() {
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
    public String toString() { return "4"; }

    /**
     *
     * @param enemy the critter currently at the same position on board as this critter
     * @return True if this critter fought the critter passed in, false if this critter ran away
     * This critter only has the ability to run left, it cannot fight.
     */
    public boolean fight(String enemy) {
        if (dir==2 || dir==6 || dir==4){
            dir=0;
        }
        else if (dir==3 || dir==5){
            dir=7;
        }
        if (dir==0 || dir==1 || dir==7) {
            run(dir);


            for (int i = 0; i < getPopulation().size(); i++) {
                if (super.getX_coord() == getPopulation().get(i).getX()) {
                    if (dir < 4) {
                        dir += 4;
                        run(dir);
                        super.setEnergy(super.getEnergy() + Params.run_energy_cost);
                    } else if (dir >= 4) {
                        dir -= 4;
                        run(dir);
                        super.setEnergy(super.getEnergy() + Params.run_energy_cost);
                    }
                }
            }
            permit_to_move = false;

            int roll = Critter.getRandomInt(GENE_TOTAL);
            int turn = 0;
            while (genes[turn] <= roll) {
                roll = roll - genes[turn];
                turn = turn + 1;
            }
            assert(turn < 8);

            dir = (dir + turn) % 8;
            return false;

        }

        return true;
    }

    /**
     *Critter does not reproduce, only walks in given direction, and changes its direction
     */
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
