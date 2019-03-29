package LAB2;

public class leafNode extends KdNode {

	private static final int BlockSize = 100;
	private Point[] PointsBlockBuffef;
	private int PointsCounter = 0;
	private int axis;
	private KdNode parent = null;


	public leafNode(KdNode p) {

		super();
		PointsBlockBuffef = new Point[BlockSize];
		parent = p;	
		this.axis = 0;
	}

	public boolean isFull() {

		if (PointsCounter == BlockSize) {return true;}
		else {return false;}
	}

	public KdNode getParent() {

		return parent;
	}


	public void setParent(InteriorNode np) {

		parent = np;
	}
	@Override
	public int getAxis() {

		return this.axis;
	}
	public void incrementCounts() {

		PointsCounter++;
	}

	public boolean insert(Point p, KdTree kt){

		if (!isFull()) {

			PointsBlockBuffef[PointsCounter] = p;
			incrementCounts();
			return true;

		}
		else {
			//Inform its parent node to create new Interior node and points to 
			//two new leafNode and redistribute points to new blocks
			//System.out.println("I am full");

			int a1 = parent.getNextAxis(3,1);
			int a2 = parent.getAxis();
			double v1 = p.getCoorValue(a1);
			double v2 = p.getCoorValue(a2);
			//new interior node
			KdNode kd1 = new InteriorNode(a1,v1);
			kt.inrementNodeCounter();
			if (v2 < parent.getValue()) {
				//set new node to parent's left
				parent.setLeft(kd1);
			}
			else {
				//set new node to parent's right
				parent.setRight(kd1);
			}

			KdNode l1 = new leafNode(kd1);
			KdNode l2 = new leafNode(kd1);

			kd1.setLeft(l1);//left new leaf block
			kd1.setRight(l2);//Right new leaf block
			kt.inrementNodeCounter();
			kt.inrementNodeCounter();
			

			//redistribute 101 points to these two blocks (p + this)
			if (p.getCoorValue(kd1.getAxis()) < v1) {

				l1.insert(p,kt);
			}
			else {
				l2.insert(p,kt);
			}

			for (int i = 0; i < PointsCounter; i++) {

				if (PointsBlockBuffef[i].getCoorValue(kd1.getAxis()) < v1) {

					l1.insert(PointsBlockBuffef[i],kt);			
				}
				else {

					l2.insert(PointsBlockBuffef[i],kt);

				}
			}
		}

		return false;	
	}

	public int getPointCounts() {
		return PointsCounter;
	}

	public Point[] getPoints() {
		return PointsBlockBuffef;
	}

	public boolean pointInThisNode(Point p) {

		for (int i = 0; i < this.PointsCounter; i++) {

			if (this.PointsBlockBuffef[i].getPoints()[0] == p.getCoorValue(1) &&
					this.PointsBlockBuffef[i].getPoints()[1] == p.getCoorValue(2) &&
					this.PointsBlockBuffef[i].getPoints()[2] == p.getCoorValue(3) ) {

				return true;
			}


		}
		return false;
	}

	public String toString() {

		String s = "";
		if (this.PointsCounter != 0) {
			s = getPointCounts() + "\n";

			for(int i = 0; i < PointsCounter;i++) {
				s+=PointsBlockBuffef[i].toString() + "\n";
			}

			return s;

		}
		else {
            return ("The axis is "+this.axis+"."+" Empty leaf node with no point");
		}


	}
}
