package LAB2;

import java.io.*;
public class LAB2 {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("resource/LA2.txt"));

		KdTree KDT = new KdTree(3);
		System.out.println(KDT.toString());

		for (int i = 0;i < 10000000;i++) {

			String test = reader.readLine();
			Point np = new Point(KDT.StringtoPoint(test));
			KDT.insertPoint(KDT.getRoot(), np);

		}
		System.out.println(KDT.toString());
		
		double[] sampleRange = {0,10,20,30,40,50};
		KDT.RangeSearch(KDT.getRoot(), sampleRange);
		int nbBlocks = KDT.getTarget().size();
		
		for (int i =0; i<nbBlocks;i++) {
			int nbPoints = KDT.getTarget().get(i).getPointCounts();
			for (int j =0; j < nbPoints;j++) {
				if (KDT.PointAtRange(KDT.getTarget().get(i).getPoints()[j], sampleRange)) {
					System.out.println(KDT.getTarget().get(i).getPoints()[j].toString());
				}
			}
		}
		
		Point samplePoint = new Point(KDT.StringtoPoint("(100.5556, 100.2938, 100.4495)"));
		Point targetPoint = KDT.NearestPoint(samplePoint);
		
		System.out.println("Target nearest point is " + targetPoint.toString());

		
		



		


		//KT.writeBlocksTofile(KT.getRoot(),fw);

		//		String test = "(814.1884, 186.4541, 636.5929)";
		//		Point np = new Point(StringtoPoint(test));
		//
		//		KT.PointSearch(KT.getRoot(), np);
		//		System.out.println(KT.getSp().toString());

		reader.close();
		
		// KT.printPoints(KT.getRoot());	 


	}

	
	




}
