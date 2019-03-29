package LAB2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KdTree {

	private static int kd_depth;
	private static final int BlockSize = 100;
	private KdNode kd_root;
	private int kd_count = 0;
	private ArrayList<KdNode> Target = new ArrayList<KdNode>(BlockSize);
	private KdNode sp;


	public KdTree(int k) {

		kd_depth = k;		
		kd_root = new InteriorNode((0 + 1) % k,500);
		kd_count ++;

		construct(this.kd_root,500);
	}

	public void construct(KdNode root,double iniValue) {

		KdNode n = root;

		int current_axis = n.getAxis();		
		int next_axis = n.getNextAxis(kd_depth,0);


		//System.out.println("current node is " + n.toString());

		if (current_axis == 0) {


			return;
		}

		if (next_axis == 0) {

			n.setLeft(new leafNode(n));
			n.setRight(new leafNode(n));
			kd_count += 2;
		}

		else {
			n.setLeft(new InteriorNode(next_axis,iniValue));
			n.setRight(new InteriorNode(next_axis,iniValue));
			kd_count += 2;
		}
		//System.out.println("current node's left is " + n.getLeft().toString());
		//System.out.println("current node's right is " + n.getRight().toString());
		//System.out.println("--------------------");

		if (n.getLeft() != null && n.getRight()!=null) {
			construct(n.getLeft(),iniValue);
			construct(n.getRight(),iniValue);
		}

	}

	public KdNode getRoot() {

		return kd_root;
	}

	public ArrayList<KdNode> getTarget() {

		return Target;
	}

	public KdNode getSp() {

		return sp;
	}

	public void inrementNodeCounter() {
		this.kd_count++;
	}

	public void insertPoint(KdNode trace,Point np){

		if (trace.getAxis() == 0) {
			trace.insert(np,this);
			return;
		}
		double value = np.getCoorValue(trace.getAxis());


		if(value < trace.getValue()) {
			insertPoint(trace.getLeft(),np);
		}
		else {
			insertPoint(trace.getRight(),np);
		}	

	}

	public KdNode PointSearch(KdNode n,Point p) {

		int currentAxis = n.getAxis();
		double currentVaule = n.getValue();
		double pVaule = p.getCoorValue(currentAxis);

		if (currentAxis == 0) {

			if (n.pointInThisNode(p)) {


				sp = n;
				return sp;

			}			
		}

		if (pVaule < currentVaule) {

			PointSearch(n.getLeft(),p);

		}
		else {
			PointSearch(n.getRight(),p);
		}

		return sp;


	}

	public void RangeSearch(KdNode n,double[] range) {

		double lowerX = range[0];
		double UpperX = range[1];
		double lowerY = range[2];
		double UpperY = range[3];
		double lowerZ = range[4];
		double UpperZ = range[5];

		int a = n.getAxis();
		double cv = n.getValue();

		if (a == 0) {

			Target.add(n);
		}

		else {

			if (a == 1) {
				if (cv < lowerX) {
					RangeSearch(n.getRight(),range);
				}
				else if(cv >= UpperX) {
					RangeSearch(n.getLeft(),range);
				}
				else if(cv > lowerX && cv < UpperX) {
					RangeSearch(n.getRight(),range);
					RangeSearch(n.getLeft(),range);

				}
			}
			else if(a == 2) {
				if (cv < lowerY) {
					RangeSearch(n.getRight(),range);
				}
				else if(cv >= UpperY) {
					RangeSearch(n.getLeft(),range);
				}
				else if(cv > lowerY && cv < UpperY) {
					RangeSearch(n.getRight(),range);
					RangeSearch(n.getLeft(),range);

				}

			}
			else if(a == 3) {
				if (cv < lowerZ) {
					RangeSearch(n.getRight(),range);
				}
				else if(cv >= UpperZ) {
					RangeSearch(n.getLeft(),range);
				}
				else if(cv > lowerZ && cv < UpperZ) {
					RangeSearch(n.getRight(),range);
					RangeSearch(n.getLeft(),range);

				}

			}

		}

	}

	public Point NearestPoint(Point p) {

		boolean changed = true;
		double d = 3;
		int r;
		double nearestDistance = Double.MAX_VALUE;
		Point nearestPoint1 =new Point(StringtoPoint("(2000.000, 2000.000, 2000.000)"));
		Point nearestPoint2 = null;

		double ed = 0;
		double incre = 0;

		do {
			d = 3 + incre;
			double[] rangetosearch = {p.getPoints()[0] - d,p.getPoints()[0] + d, p.getPoints()[1] - d,p.getPoints()[1] + d, p.getPoints()[2] - d,p.getPoints()[2] + d};
			this.RangeSearch(this.getRoot(), rangetosearch);
			r =  this.getTarget().size();
			System.out.println("Number of Points Blocks to search are " + r +".");

		
			ed = 0;

			for (int i=0 ; i<r; i++) {

				for (int j = 0;j < this.getTarget().get(i).getPointCounts();j++) {

					if (p.compareTo(this.getTarget().get(i).getPoints()[j]) == 0) {
						break;	
					}

					ed = EuclideanDistance(p,this.getTarget().get(i).getPoints()[j]);				
					if (ed < nearestDistance) {
						nearestDistance = ed;
						nearestPoint2 = this.getTarget().get(i).getPoints()[j];
					}

				}
			}

			System.out.println("Nearest Distance is " + nearestDistance);
			if (nearestPoint2.compareTo(nearestPoint1) == 0) {
				nearestPoint1 = nearestPoint2;
				changed =false;
			}
			else {
				nearestPoint1 = nearestPoint2;
				incre = 2;

			}


			
		}while(changed);

		return nearestPoint1;


	}

	public void writeBlocksTofile(KdNode n,FileWriter fw) throws IOException {

		if (n.getAxis() == 0) {

			fw.write(n.toString());
		}
		else {
			writeBlocksTofile(n.getLeft(),fw);
			writeBlocksTofile(n.getRight(),fw);
		}


	}
	public void printPoints(KdNode n) {

		if (n == null) {return;}

		if (n.getAxis() == 0) {
			System.out.println(n.toString());
		}
		else {
			printPoints(n.getLeft());
			printPoints(n.getRight());
		}

	}

	public double[] StringtoPoint(String line) {

		double[] p = new double[3];

		p[0] = Double.parseDouble(line.substring(1, 9));
		p[1] = Double.parseDouble(line.substring(11, 19));
		p[2] = Double.parseDouble(line.substring(21, 29));

		return p;

	}

	public boolean PointAtRange(Point p, double[] r) {

		if (p.getCoorValue(1) >= r[0] && p.getCoorValue(1) <= r[1] &&
				p.getCoorValue(2) >= r[2] && p.getCoorValue(2) <= r[3] &&
				p.getCoorValue(3) >= r[4] && p.getCoorValue(3) <= r[5] ) {return true;}
		else {
			return false;
		}
	}

	public double EuclideanDistance(Point p1, Point p2) {

		double dx = Math.pow(p1.getPoints()[0] - p2.getPoints()[0],2);
		double dy = Math.pow(p1.getPoints()[1] - p2.getPoints()[1],2);
		double dz = Math.pow(p1.getPoints()[2] - p2.getPoints()[2],2);

		return (Math.sqrt(dx+dy+dz));

	}


	public String toString(){


		return ("The KD tree has " + this.kd_count + " number of nodes.");

	}




}
