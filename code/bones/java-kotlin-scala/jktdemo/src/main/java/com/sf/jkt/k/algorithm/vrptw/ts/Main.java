package com.sf.jkt.k.algorithm.vrptw.ts;

import static com.sf.jkt.k.algorithm.vrptw.ts.InitAndPrint.*;
import static com.sf.jkt.k.algorithm.vrptw.ts.TS.*;

public class Main {
	public static void main (String arg[]) {

		long begintime = System.nanoTime();

		ReadIn();
		Construction();
		TabuSearch();
		Output();
		CheckAns();

		long endtime = System.nanoTime();
		double usedTime= (endtime - begintime)/(1e9);
		System.out.println();
		System.out.println("Total run time :"+usedTime+"s");
	}
}
