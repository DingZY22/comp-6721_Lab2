package LAB2;

public abstract class KdNode {
	
	public double getValue() {
		return 0;
	}
	public abstract int getAxis();
	
	public int getNextAxis(int depth,int mode) {
		return 0;
	}
	public void setLeft(KdNode nl) {
		
	}
	public void setRight(KdNode nr) {
		
	}
	public KdNode getRight(){
		return null;
	}
	public KdNode getLeft() {
		return null;
	}
	
	public KdNode getParent() {
		return null;
	}
	
	public boolean insert(Point p,KdTree kt) {
		return false;
	}
	public Point[] getPoints() {
		return null;
	}
	public int getPointCounts() {
		return 0;
	}
	public boolean pointInThisNode(Point p) {
		return false;
	}
	
	public abstract String toString();

}
