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


	public boolean PointAtRange(double[] r) {

		if (this.getCoorValue(1) >= r[0] && this.getCoorValue(1) <= r[1] &&
				this.getCoorValue(2) >= r[2] && this.getCoorValue(2) <= r[3] &&
				this.getCoorValue(3) >= r[4] && this.getCoorValue(3) <= r[5]) {return true;}
		else {
			return false;

		}
	}

	public double EuclideanDistance(Point p1) {

		double dx = Math.pow(p1.getPoints()[0] - this.getPoints()[0],2);
		double dy = Math.pow(p1.getPoints()[1] - this.getPoints()[1],2);
		double dz = Math.pow(p1.getPoints()[2] - this.getPoints()[2],2);

		return (Math.sqrt(dx+dy+dz));

	}

	public boolean pointIntheCube() {

		if (0<=this.getPoints()[0] && 1000>=this.getPoints()[0] &&
				0<=this.getPoints()[1] && 1000>=this.getPoints()[1] &&
				0<=this.getPoints()[2] && 1000>=this.getPoints()[2]) {
			return true;
		}
		return false;
	}

	public double maxCoor() {
		double max = coordinates[0];
		for (int i = 1; i < this.coordinates.length; i++) {
			if (coordinates[i] > max) {
				max = coordinates[i];
			}
		}
		return max;
	}

	public String toString() {

		String s = "(" + this.coordinates[0] + ", " + this.coordinates[1] + ", " + this.coordinates[2] + ")";
		return s;
	}

}
