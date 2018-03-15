package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.List;
//test push
/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();



	protected boolean permit_to_move;
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public abstract String toString();
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	protected int getX() { return x_coord;}
	protected int getY() { return y_coord;}
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		if(permit_to_move==true) {
			switch(direction) {
				case 0: x_coord++;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
						break;
				case 1: x_coord++;
						y_coord--;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 2: y_coord--;
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 3: x_coord--;
						y_coord--;
					if (x_coord<0){
						x_coord=Params.world_width-1;
					}
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 4: x_coord--;
						if (x_coord<0){
							x_coord=Params.world_width-1;
						}
						break;
				case 5: x_coord--;
						y_coord++;
					if (x_coord<0){
						x_coord=Params.world_width-1;
					}
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				case 6: y_coord++;
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				case 7: x_coord++;
						y_coord++;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				default: break;
			}
		}
		energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		if(permit_to_move==true) {
			switch(direction) {
				case 0: x_coord+=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
						break;
				case 1: x_coord+=2;
						y_coord-=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 2: y_coord-=2;
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 3: x_coord-=2;
						y_coord-=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 4: x_coord-=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
						break;
				case 5: x_coord-=2;
						y_coord+=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				case 6: y_coord+=2;
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				case 7: x_coord+=2;
						y_coord+=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				default: break;
			}
		}
		energy -= Params.run_energy_cost;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(energy < Params.min_reproduce_energy) {
			return;
		}
		offspring.energy = (int)(energy * 0.5);
		energy = (int)(energy * 0.5) + 1;
		switch(direction) {
		case 0: offspring.x_coord = x_coord++;
				offspring.y_coord = y_coord;
			if (offspring.x_coord==Params.world_width){
				offspring.x_coord=0;
			}
				break;
		case 1: offspring.x_coord = x_coord++;
				offspring.y_coord = y_coord--;
			if (offspring.x_coord==Params.world_width){
				offspring.x_coord=0;
			}
			if (offspring.y_coord<0){
				offspring.y_coord=Params.world_height-1;
			}
				break;
		case 2: offspring.x_coord = x_coord;
				offspring.y_coord = y_coord--;
			if (offspring.y_coord<0){
				offspring.y_coord=Params.world_height-1;
			}
				break;
		case 3: offspring.x_coord = x_coord--;
				offspring.y_coord = y_coord--;
			if (offspring.x_coord<0){
				offspring.x_coord=Params.world_width-1;
			}
			if (offspring.y_coord<0){
				offspring.y_coord=Params.world_height-1;
			}
				break;
		case 4: offspring.x_coord = x_coord--;
				offspring.y_coord = y_coord;
			if (offspring.x_coord<0){
				offspring.x_coord=Params.world_width-1;
			}
				break;
		case 5: offspring.x_coord = x_coord--;
				offspring.y_coord = y_coord--;
			if (offspring.x_coord<0){
				offspring.x_coord=Params.world_width-1;
			}
			if (offspring.y_coord==Params.world_height){
				offspring.y_coord=0;
			}
				break;
		case 6: offspring.x_coord = x_coord;
				offspring.y_coord = y_coord--;
			if (offspring.y_coord==Params.world_height){
				offspring.y_coord=0;
			}
				break;
		case 7: offspring.x_coord = x_coord++;
				offspring.y_coord = y_coord--;
			if (offspring.x_coord==Params.world_width){
				offspring.x_coord=0;
			}
			if (offspring.y_coord==Params.world_height){
				offspring.y_coord=0;
			}
				break;
		default: break;
		}
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 * @throws ClassNotFoundException 
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException{

			try {
				Class c = Class.forName(critter_class_name);
				Critter crit=(Critter) c.newInstance();


					crit.x_coord = getRandomInt(Params.world_width);
					crit.y_coord = getRandomInt(Params.world_height);
					crit.energy = Params.start_energy;


					population.add(crit);


			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new InvalidCritterException(critter_class_name);

			}
			catch(InstantiationException e){

				throw new InvalidCritterException(critter_class_name);
			}

			catch(IllegalAccessException e){

				throw new InvalidCritterException(critter_class_name);

			}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter c: population) {
			if(c.getClass().getName().equalsIgnoreCase(critter_class_name)) {
				result.add(c);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	
	public static void worldTimeStep() {
		int roll1=0, roll2=0;
		//Add all babies to population
		for(int l = 0; l < babies.size(); l++) {
			population.add(babies.get(l));
		}
		babies.clear();
		//Do time step
		for(int i = 0;i<population.size();i++) {
			population.get(i).doTimeStep();
			if(population.get(i).getEnergy()<=0) {
				population.remove(i);
				i--;
			}
		}

		//Check encountered,if two have same position
		for(int j = 0; j < population.size();j++) {
			for (int k = j + 1; k < population.size(); k++) {
				if (population.get(j).x_coord == population.get(k).x_coord) {
					if (population.get(j).y_coord == population.get(k).y_coord) {
						//decide to fight or move

						if (!population.get(j).fight(population.get(k).toString())){
							roll1=0;
						}
						else{
							roll1=(getRandomInt(population.get(j).getEnergy()));
						}

						if (!population.get(k).fight(population.get(j).toString())){
							roll2=0;
						}
						else {
							roll2=getRandomInt(population.get(k).getEnergy());
						}
					}
				}
				//Still need to check if they are still in the same position
				if (population.get(j).x_coord == population.get(k).x_coord) {
					if (population.get(j).y_coord == population.get(k).y_coord) {	
						if (roll1>=roll2) {
							population.get(j).energy += 0.5 * population.get(k).energy;
							population.get(k).energy=0;
						}
						else {
							population.get(k).energy+=0.5*population.get(j).energy;
							population.get(j).energy=0;

						}
						//Not enough energy, die
						if (population.get(k).getEnergy() <= 0) {
							population.remove(k);
							k--;
							if (population.get(j).getEnergy() <= 0) {
								population.remove(j);
								j--;
								break;
							}
						}
					}
				}
			}
		}
	}

	public static void displayWorld() {
		Critter[][] world = new Critter[Params.world_width][Params.world_height];
		for(int i = 0; i < population.size();i++) {
			world[population.get(i).getX()][population.get(i).getY()] = population.get(i);
		}
		for(int j = 0; j < Params.world_height; j++) {
			for(int k = 0; k < Params.world_width; k++) {
				if(world[k][j]==null) {
					System.out.print(" ");
				}
				else {
					System.out.print(world[k][j].toString());
				}
			}
			System.out.println("");
		}
	}
}
