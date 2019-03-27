package LAB2;

public class Point {

	private double[] coordinates;//0 for x, 1 for y, 2 for z
	private static int dimention;

	
	protected Point(int d) {
		
		dimention = d;
		coordinates = new double[dimention];
	}
	
	public int compareTo(Point p1){ 
	    
		if (this.getPoints()[0] == p1.getPoints()[0] &&
				this.getPoints()[1] == p1.getPoints()[1] &&
						this.getPoints()[1] == p1.getPoints()[1]){
							return 0;
						}
		
		else return -1;
	}

	protected Point(double[] x) {

		coordinates = new double[x.length];
		for (int i = 0; i < x.length; ++i)
			coordinates[i] = x[i];
	}

	protected Object clone() {

		return new Point(coordinates);
	}

	protected boolean equals(Point p) {


		for (int i = 0; i < coordinates.length; ++i)
			if (coordinates[i] != p.coordinates[i])
				return false;

		return true;
	}

	public double getCoorValue(int axis) {

		double coor = 0;
		if (axis == 1) {
			coor =  coordinates[0];
		}
		else if (axis == 2) {
			coor = coordinates[1];
		}
		else if (axis == 3) {
			coor = coordinates[2];
		}		
		return coor;

	}
	
	public double[] getPoints() {
		
		return this.coordinates;
	}

	

	public String toString() {
		String s = "";
		for (int i = 0; i < coordinates.length; ++i) {
			s = s + coordinates[i] + " ";
		}
		return s;
	}

}
