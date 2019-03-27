package LAB2;



public abstract class KdNode {
	
	public abstract double getValue();
	public abstract int getAxis();
	public abstract int getNextAxis(int depth);
	public abstract  void setLeft(KdNode nl);
	public abstract  void setRight(KdNode nr);
	public abstract KdNode getRight();
	public abstract KdNode getLeft();
	public abstract KdNode getParent();
	public abstract boolean insert(Point p);
	public abstract Point[] getPoints();
	public abstract int getPointCounts();
	public abstract boolean pointInThisNode(Point p);
	public abstract String toString();

}
