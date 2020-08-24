import java.io.*;
import java.util.Scanner;

public class Simulation {
	public static Job getJob(Scanner in) {
	     String[] s = in.nextLine().split(" ");
	     int a = Integer.parseInt(s[0]);
	     int d = Integer.parseInt(s[1]);
	     return new Job(a, d);
	}
	
	public static int numJobs(Scanner sc) {
		int m = Integer.parseInt(sc.nextLine());
		return m;
	}
	
	public static void main(String [] args) throws IOException{
		Scanner sc = null;
		PrintWriter rpt = null;
		PrintWriter trc = null;
		
		int m = 0;
		Job j = null;
		int time = 0;
		Queue initialQueue = new Queue();
		Queue storedQueue = new Queue();
		Queue displayQueue = new Queue();
		Queue completedQueue = new Queue();
		Queue[] processorQueues = null;
		boolean unprocessed_jobs_remain = true;
	
		try {
			if(args.length != 1) {
				System.out.println("Usage: Simulation infile");
				System.exit(1);
			}
			sc = new Scanner(new File(args[0]));
			rpt = new PrintWriter(new FileWriter(args[0] + ".rpt"));
			trc = new PrintWriter(new FileWriter(args[0] + ".trc"));
		}catch(FileNotFoundException e) {
			System.out.println("Caught Exception " + e);
			System.exit(1);
		}
		
		m = numJobs(sc);
		//System.out.println(m);
		
		for(int i = 1; i <= m; i++) {
			j = getJob(sc);
			initialQueue.enqueue(j);
		}
		
		trc.println("Trace file: " + (args[0] + ".trc"));
		trc.println(m + " Jobs:");
		trc.println(initialQueue.toString());
		trc.println();

		rpt.println("Report file: " + (args[0] + ".rpt"));
		rpt.println(m + " Jobs:");
		rpt.println(initialQueue.toString());
		rpt.println();
		rpt.println("***********************************************************");
		
		for(int n = 1; n < m; n++) {
			int totalWait = 0;
			int maxWait = 0;
			double avgWait = 0.00;
			
			for(int i = 1; i <= initialQueue.length(); i++) {
				j = (Job)initialQueue.dequeue();
				j.resetFinishTime();
               
                storedQueue.enqueue(j);
                displayQueue.enqueue(j);
				initialQueue.enqueue(j);
								
			}
			
			int processors = n;
			processorQueues = new Queue[n+2];
			processorQueues[0] = storedQueue;
			processorQueues[n+1] = completedQueue;
			for(int i = 1; i < n+1; i++) {
				processorQueues[i] = new Queue();
			}
			
			trc.println("*****************************");
			if(processors == 1) {
				trc.println(processors + " processor:");
			}else {
				trc.println(processors + " processor:");
			}
			trc.println("*****************************");
			
			trc.println("time=" + time);
			trc.println("0:" + displayQueue.toString());
			for(int i = 1; i <= processors; i++) {
				trc.println(i + ": " + processorQueues[i]);
			}
			
			while(completedQueue.length() < m) {
				int compFinal = Integer.MAX_VALUE;
				int last = 1;
				int comp = -1;
				int length = -1;
				int finalLength = -1;
				Job compare = null;
			
			
				if(!storedQueue.isEmpty()) {
					compare = (Job)storedQueue.peek();
					compFinal = compare.getArrival();
					last = 0;
				}
				
				for(int i = 1; i <= processors; i++) {
					if(processorQueues[i].length() != 0) {
						compare = (Job)processorQueues[i].peek();
						comp = compare.getFinish();
					}
					if(comp != -1 && comp < compFinal) {
						compFinal = comp;
						last = i;
					}
					time = compFinal;
				}
				
				if(last == 0) {
					int temp = 1;
					finalLength = processorQueues[temp].length();
					for(int i = 1; i <= processors; i++) {
						length = processorQueues[i].length();
						if(length < finalLength) {
							finalLength = length;
							temp = i;
						}
					}
					
					compare = (Job)storedQueue.dequeue();
					displayQueue.dequeue();
					processorQueues[temp].enqueue(compare);
					if(processorQueues[temp].length() == 1) {
						compare = (Job)processorQueues[temp].peek();
						compare.computeFinishTime(time);
					}
				}else {
					compare = (Job)processorQueues[last].dequeue();
					completedQueue.enqueue(compare);
					//displayQueue.enqueue(compare);
					int tempWait = compare.getWaitTime();
					if(tempWait > maxWait) {
						maxWait = tempWait;
					}
					totalWait += tempWait;
					
					if(processorQueues[last].length() >=1) {
						compare = (Job)processorQueues[last].peek();
						compare.computeFinishTime(time);
					}
				}
				
				trc.println();
				trc.println("time="+ time);
				trc.println("0: " + displayQueue.toString() + completedQueue);
				for(int i = 1; i < processors+1; i++) {
					trc.println(i + ": " + processorQueues[i]);
				}
			}
			
			avgWait = ((double)totalWait/m);
			avgWait = (double)Math.round(avgWait*100)/100;
			String averageWait = String.format("%.2f", avgWait);
			trc.println();
			if (processors == 1)
				rpt.println(processors + " processor: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + averageWait);
			else
				rpt.println(processors + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + averageWait);

			time = 0;
			completedQueue.dequeueAll();
		}
		
		sc.close();
		rpt.close();
		trc.close();
	}
}
