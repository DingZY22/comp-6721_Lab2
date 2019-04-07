package LAB2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KdTree {

	private static int kd_depth;
	private KdNode kd_root;
	private int kd_count;
	private ArrayList<KdNode> Target = new ArrayList<KdNode>(100);
	private KdNode sp;
	
	

	public KdTree(int k) {

		kd_depth = k;		
		kd_root = new InteriorNode(1,500);
		kd_count = 1;
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
	
	


	public void insertPoint(KdNode trace,Point np){


		if (trace.getAxis() == 0) {
			trace.insert(np);
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




}
