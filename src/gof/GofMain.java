package gof;

public class GofMain {

	public static final int FIELD_X = 300;
	public static final int FIELD_Y = 220;
	public static final int TIMER_INTERVAL = 30;
	public static final int ZOOM_FACTOR = 3;

	public static final boolean GARDEN_EDEN = true;
	public static final int EDEN_X_START = 50;
	public static final int EDEN_X_END = 60;
	public static final int EDEN_Y_START = 40;
	public static final int EDEN_Y_END = 50;

	public static void main(String[] args) throws Exception {
		GofCalc gof = new GofCalc();
		initField(gof);

		new GofDialog(gof).run();
	}

	private static void initField(GofCalc gof) {
		// for (int i = 30; i < 80; i++) {
		// field[i][FIELD_SIZE / 2] = true;
		// }
		FieldSetup fs = new FieldSetup(gof);
		// fs.letterH(100, 100);
		// fs.rectangle(100, 60, 100);
		fs.random(0.01);

		// fs.testRun(130, 150);
		// for (int i = 0; i < 7; i++) {
		// int x = 10 + (int) (Math.random() * (FIELD_X - 20));
		// int y = 10 + (int) (Math.random() * (FIELD_Y - 20));
		// fs.horizontalSpaceship(x, y);
		// }
		// for (int i = 0; i < 7; i++) {
		// int x = 10 + (int) (Math.random() * (FIELD_X - 20));
		// int y = 10 + (int) (Math.random() * (FIELD_Y - 20));
		// fs.verticalSpaceship(x, y);
		// }

		fs.flyer(96, 30);
		// fs.caro(50, 50);
		// fs.pseudoRandom(48, 50);
	}

	public static final boolean[] edenDeadCellRules = new boolean[] {
	//
			false, // 0 neighbours
			false, // 1 neighbours
			true, // 2 neighbours
			true, // 3 neighbours
			false, // 4 neighbours
			false, // 5 neighbours
			false, // 6 neighbours
			false, // 7 neighbours
			false // 8 neighbours
	};

	public static final boolean[] deadCellRules = new boolean[] {// 
	//
			false, // 0 neighbours
			false, // 1 neighbours
			false, // 2 neighbours
			true, // 3 neighbours
			false, // 4 neighbours
			false, // 5 neighbours
			false, // 6 neighbours
			false, // 7 neighbours
			false // 8 neighbours
	};

	public static final boolean[] liveCellRules = new boolean[] {// 
	//
			false, // 0 neighbours
			false, // 1 neighbours
			true, // 2 neighbours
			true, // 3 neighbours
			false, // 4 neighbours
			false, // 5 neighbours
			false, // 6 neighbours
			false, // 7 neighbours
			false // 8 neighbours
	};
}
