package LAB2;
import java.io.*;
import java.util.*;
public class LAB2 {

	public static void main(String[] args) throws IOException {

		long startTime = System.currentTimeMillis();
		System.out.println("Start");
		Scanner key = new Scanner(System.in);
		
		KdTree KDT = new KdTree(3);
		memoryRecorder();
		init(KDT);		
		memoryRecorder();		
		long duration = timeRecorder(startTime);
		System.out.println("Finish constructing the KD tree." + "Takes " + duration + " milliseconds.");
		System.out.println(KDT.toString());

		while(true) {
			System.out.println("1 for range search, 2 for nearest point finding");
			int type_query = key.nextInt();
			QueryExectution(KDT,key,type_query);

			KDT.clearPoints();
			System.out.println("Stop?");
			String ans =  key.next();
			if (ans.equals("yes")) {
				break;
			}
			else {continue;}
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

	public static void QueryExectution(KdTree KDT,Scanner key,int type) {

		if (type == 1) {

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

			long startTime = System.currentTimeMillis();		
			double[] sampleRange = {xl,xu,yl,yu,zl,zu};
			KDT.RangeSearch(KDT.getRoot(), sampleRange);
			int nbPoints = KDT.getTargetPoints().size();
			System.out.println("Number of points in the range is " + nbPoints + ".");
			for (int i =0; i<nbPoints;i++) {
				System.out.println( KDT.getTargetPoints().get(i).toString());
			}
			long duration =  timeRecorder(startTime);

			System.out.println("Finish querys. " + "Takes " + duration + " milliseconds.");
			System.out.println("Number of points in the range is " + nbPoints + ".");
		}


		if (type == 2) {

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
			long startTime = System.currentTimeMillis();		
			if (samplePoint.pointIntheCube()) {

				sr = 5;
			}
			else {

				sr = samplePoint.maxCoor() - 1000 + 5;
				System.out.println(sr);
			}
			Point targetPoint = KDT.NearestPoint(samplePoint,sr);
			System.out.println("Target nearest point is " + targetPoint.toString());
			long duration =  timeRecorder(startTime);
			System.out.println("Finish querys. " + "Takes " + duration + " million seconds.");
		}
		

	}

	public static long timeRecorder(long startTime) {

		long endTime = System.currentTimeMillis();
		return (endTime - startTime);

	}
	
	public static void memoryRecorder() {

		long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
		long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
		long usedMemory = totalMemory - freeMemory;
		
		System.out.println("Total memory : " + totalMemory + "MB" );
		System.out.println("Free memory : " + freeMemory + "MB");
		System.out.println("Used memory : " + usedMemory + "MB");
	}











}
