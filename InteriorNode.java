package LAB2;

public class InteriorNode extends KdNode {
	
	private int axis;//1 for x; 2 for y; 3 for z	
	private double v;
	private KdNode left,right;
	
	public InteriorNode(int a,double nv) {
		
		super();
		axis = a;
		v = nv;
		left = null;
		right = null;
		
	}
	
	public int getAxis() {
		
		return axis;
	}
	
	public int getNextAxis(int depth) {
		
		return (axis % depth + 1);
	}
	
	public double getValue() {
		
		return v;
	}
	
	
	public InteriorNode createNode(int a,double nv) {
		
		InteriorNode t = new InteriorNode(a,nv);
		return t;
	}
	
	public void setLeft(KdNode nl) {
		left = nl;
	}
	public void setRight(KdNode nr) {
		right = nr;
	}
	public KdNode getLeft() {
		return left;
	}
	public KdNode getRight() {
		return right;
	}
	
	public String toString() {
		
		return ("The axis is " + axis + ". The value is " + v + ".");
		
	}


	@Override
	public Point[] getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public KdNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPointCounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean pointInThisNode(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
