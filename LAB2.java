package LAB2;

import java.io.*;
import java.util.*;
public class LAB2 {

	public static void main(String[] args) throws IOException {

		boolean prgram_loop =true;
		System.out.println("Start");
		Scanner key = new Scanner(System.in);
		KdTree KDT = new KdTree(3);
		init(KDT);
		System.out.println(KDT.toString());
		
		while(prgram_loop) {		
			
			QueryExectution(KDT,key);
			KDT.clearPoints();
			System.out.println("Stop?");
			String ans =  key.next();
			if (ans.equals("yes")) {
				prgram_loop = false;
			}
			else {prgram_loop = true;}
		}
		key.close();
		System.out.println("End");
		

	}
	
	public static void init(KdTree KDT) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader("resource/LA2.txt"));	
		System.out.println(KDT.toString());
	
		for (int i = 0;i < 10000000;i++) {

			String test = reader.readLine();
			Point np = new Point(KDT.StringtoPointDouble(test));
			KDT.insertPoint(KDT.getRoot(), np);

		}
		
		reader.close();	
		
	}
	
	public static void QueryExectution(KdTree KDT,Scanner key) {
		
	
		System.out.println("Please enter the range to be searched in the dataset");
		System.out.print("x lower: ");
		double xl = key.nextDouble();
		System.out.print("x upper: ");
		double xu = key.nextDouble();
		
		System.out.print("y lower: ");
		double yl = key.nextDouble();
		System.out.print("y upper: ");
		double yu= key.nextDouble();
		
		System.out.print("z lower: ");
		double zl = key.nextDouble();
		System.out.print("z upper: ");
		double zu = key.nextDouble();
		
		double[] sampleRange = {xl,xu,yl,yu,zl,zu};
		KDT.RangeSearch(KDT.getRoot(), sampleRange);
		int nbPoints = KDT.getTargetPoints().size();	
		for (int i =0; i<nbPoints;i++) {
			System.out.println( KDT.getTargetPoints().get(i).toString());
		}
		KDT.clearPoints();
		
		System.out.println("Please enter the point coordinate value to find the nearest in the dataset");
		System.out.print("x: ");
		double x = key.nextDouble();
		System.out.print("y: ");
		double y = key.nextDouble();
		System.out.print("z: ");
		double z = key.nextDouble();
		double[] np = {x,y,z};
		double sr = Integer.MAX_VALUE;
		Point samplePoint = new Point(np);
		if (samplePoint.pointIntheCube()) {
			
			sr = 5;
		}
		else {
			
			sr = samplePoint.maxCoor() - 1000 + 5;
			System.out.println(sr);
		}
		Point targetPoint = KDT.NearestPoint(samplePoint,sr);
		System.out.println("Target nearest point is " + targetPoint.toString());
	
		
	}
	
	
	
	

	
	




}
