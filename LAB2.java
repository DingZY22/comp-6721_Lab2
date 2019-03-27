package LAB2;

import java.io.*;
public class LAB2 {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("resource/LA2.txt"));

		KdTree KT = new KdTree(3);

		KdNode t1 = new InteriorNode(2,500);
		KdNode t2 = new InteriorNode(2,500);

		KdNode t3 = new InteriorNode(3,500);
		KdNode t4 = new InteriorNode(3,500);
		KdNode t5 = new InteriorNode(3,500);
		KdNode t6 = new InteriorNode(3,500);


		leafNode l1 = new leafNode(t3);
		leafNode l2 = new leafNode(t3);
		leafNode l3 = new leafNode(t4);
		leafNode l4 = new leafNode(t4);
		leafNode l5 = new leafNode(t5);
		leafNode l6 = new leafNode(t5);
		leafNode l7 = new leafNode(t6);
		leafNode l8 = new leafNode(t6);

		KT.getRoot().setLeft(t1);
		KT.getRoot().setRight(t2);

		t1.setLeft(t3);
		t1.setRight(t4);
		t2.setLeft(t5);
		t2.setRight(t6);

		t3.setLeft(l1);
		t3.setRight(l2);
		t4.setLeft(l3);
		t4.setRight(l4);
		t5.setLeft(l5);
		t5.setRight(l6);
		t6.setLeft(l7);
		t6.setRight(l8);


		for (int i = 0;i < 10000000;i++) {

			String test = reader.readLine();
			Point np = new Point(StringtoPoint(test));

			KT.insertPoint(KT.getRoot(), np);

		}

		double d = 3;
		double[] sampleRange = {898.5726 - d,898.5726 + d, 194.9344 - d,194.9344 + d, 365.7465 - d,365.7465 + d};
		KT.RangeSearch(KT.getRoot(), sampleRange);

		String test = "(898.5726, 194.9344, 365.7465)";
		Point np = new Point(StringtoPoint(test));


		int l =  KT.getTarget().size();

		File f = new File("resource/output_lab2/PointsBlock.txt");
		FileWriter fw = new FileWriter(f);
		double nearest = 10000000;
		Point nearestPoint = null;
		double ed = 0;
		System.out.println(l);
		for (int i=0 ; i<l; i++) {

			for (int j = 0;j < KT.getTarget().get(i).getPointCounts();j++) {

				//				if (PointAtRange(KT.getTarget().get(i).getPoints()[j],sampleRange)) {
				//
				//					System.out.println(KT.getTarget().get(i).getPoints()[j].toString());
				//					//fw.write(KT.getTarget().get(i).getPoints()[j].toString() + "\n");
				//				}

				if (np.compareTo(KT.getTarget().get(i).getPoints()[j]) == 0) {
					break;	
				}


				ed = EuclideanDistance(np,KT.getTarget().get(i).getPoints()[j]);				
				if (ed < nearest) {
					nearest = ed;
					nearestPoint = KT.getTarget().get(i).getPoints()[j];
				}

			}


		}

		System.out.println(nearestPoint.toString());



		d = 5;
		double[] sampleRange2 = {814.1884 - d,814.1884 + d, 186.4541 - d,186.4541 + d, 636.5929 - d,636.5929 + d};
		KT.RangeSearch(KT.getRoot(), sampleRange2);

		System.out.println(KT.getTarget().size());

		for (int i=0 ; i<l; i++) {

			for (int j = 0;j < KT.getTarget().get(i).getPointCounts();j++) {

				//				if (PointAtRange(KT.getTarget().get(i).getPoints()[j],sampleRange)) {
				//
				//					System.out.println(KT.getTarget().get(i).getPoints()[j].toString());
				//					//fw.write(KT.getTarget().get(i).getPoints()[j].toString() + "\n");
				//				}
				if (np.compareTo(KT.getTarget().get(i).getPoints()[j]) == 0) {
					break;	
				}


				ed = EuclideanDistance(np,KT.getTarget().get(i).getPoints()[j]);

				if (ed < nearest) {
					nearest = ed;
					nearestPoint = KT.getTarget().get(i).getPoints()[j];
				}

			}


		}

		System.out.println(nearestPoint.toString());
		System.out.println(nearest);



		//KT.writeBlocksTofile(KT.getRoot(),fw);

		//		String test = "(814.1884, 186.4541, 636.5929)";
		//		Point np = new Point(StringtoPoint(test));
		//
		//		KT.PointSearch(KT.getRoot(), np);
		//		System.out.println(KT.getSp().toString());

		reader.close();
		fw.close();
		// KT.printPoints(KT.getRoot());	 


	}

	public static double[] StringtoPoint(String line) {

		double[] p = new double[3];

		p[0] = Double.parseDouble(line.substring(1, 9));
		p[1] = Double.parseDouble(line.substring(11, 19));
		p[2] = Double.parseDouble(line.substring(21, 29));

		return p;

	}

	public static boolean PointAtRange(Point p, double[] r) {

		if (p.getCoorValue(1) >= r[0] && p.getCoorValue(1) <= r[1] &&
				p.getCoorValue(2) >= r[2] && p.getCoorValue(2) <= r[3] &&
				p.getCoorValue(3) >= r[4] && p.getCoorValue(3) <= r[5] ) {return true;}
		else {
			return false;
		}
	}

	public static double EuclideanDistance(Point p1, Point p2) {

		double dx = Math.pow(p1.getPoints()[0] - p2.getPoints()[0],2);
		double dy = Math.pow(p1.getPoints()[1] - p2.getPoints()[1],2);
		double dz = Math.pow(p1.getPoints()[2] - p2.getPoints()[2],2);

		return (Math.sqrt(dx+dy+dz));

	}





}
