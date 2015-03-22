
package gof;

public class FieldSetup {

	private GofCalc _gof;

	public FieldSetup(GofCalc gof) {
		_gof = gof;
	}

	public void letterH(int xStart, int yStart) {
		for (int i = 0; i < 11; i++) {
			_gof.setField(xStart, yStart + i, true);
			_gof.setField(xStart + 6, yStart + i, true);
		}
		for (int i = 0; i < 6; i++) {
			_gof.setField(xStart + i, yStart + 5, true);
		}
	}

	public void testRun1(int x, int y) {
		_gof.setField(x + 5, y, true);
		_gof.setField(x + 5, y + 2, true);
		_gof.setField(x + 4, y + 2, true);
		_gof.setField(x + 3, y + 4, true);
		_gof.setField(x + 2, y + 4, true);
		_gof.setField(x + 1, y + 4, true);
		_gof.setField(x + 2, y + 6, true);
		_gof.setField(x + 1, y + 6, true);
		_gof.setField(x, y + 6, true);
		_gof.setField(x + 1, y + 7, true);
	}

	public void testRun2(int x, int y) {
		_gof.setField(x, y + 5, true);
		_gof.setField(x + 2, y + 5, true);
		_gof.setField(x + 2, y + 4, true);
		_gof.setField(x + 4, y + 3, true);
		_gof.setField(x + 4, y + 2, true);
		_gof.setField(x + 4, y + 1, true);
		_gof.setField(x + 6, y + 2, true);
		_gof.setField(x + 6, y + 1, true);
		_gof.setField(x + 6, y, true);
		_gof.setField(x + 7, y + 1, true);
	}

	public void testRun(int x, int y) {
		for (int i = 0; i < 8; i++) {
			_gof.setField(x + i, y, true);
		}
		for (int i = 9; i < 14; i++) {
			_gof.setField(x + i, y, true);
		}
		for (int i = 17; i < 20; i++) {
			_gof.setField(x + i, y, true);
		}
		for (int i = 26; i < 33; i++) {
			_gof.setField(x + i, y, true);
		}
		for (int i = 34; i < 39; i++) {
			_gof.setField(x + i, y, true);
		}
	}

	public void pseudoRandom(int startX, int startY) {
		_gof.setField(startX + 3, startY, true);
		_gof.setField(startX + 3, startY + 1, true);
		_gof.setField(startX + 2, startY + 5, true);
		_gof.setField(startX + 6, startY, true);
		_gof.setField(startX + 5, startY + 2, true);

	}

	public void caro(int x, int y) {
		this.rectangle(x - 4, y - 4, 9);
		_gof.setField(x - 1, y, true);
		_gof.setField(x, y - 1, true);
		_gof.setField(x, y + 1, true);
		_gof.setField(x + 1, y, true);
	}

	public void horizontalSpaceship(int xStart, int yStart) {
		_gof.setField(xStart, yStart, true);
		_gof.setField(xStart - 1, yStart, true);
		_gof.setField(xStart - 2, yStart, true);
		_gof.setField(xStart - 3, yStart, true);
		_gof.setField(xStart - 4, yStart + 1, true);
		_gof.setField(xStart - 4, yStart + 3, true);
		_gof.setField(xStart, yStart + 1, true);
		_gof.setField(xStart, yStart + 2, true);
		_gof.setField(xStart - 1, yStart + 3, true);
	}

	public void verticalSpaceship(int xStart, int yStart) {
		_gof.setField(xStart, yStart, true);
		_gof.setField(xStart + 1, yStart, true);
		_gof.setField(xStart + 2, yStart, true);
		_gof.setField(xStart, yStart + 1, true);
		_gof.setField(xStart, yStart + 2, true);
		_gof.setField(xStart, yStart + 3, true);
		_gof.setField(xStart + 1, yStart + 4, true);
		_gof.setField(xStart + 3, yStart + 4, true);
		_gof.setField(xStart + 3, yStart + 1, true);
	}

	public void rectangle(int xStart, int yStart, int sideLength) {
		for (int i = 0; i < sideLength; i++) {
			_gof.setField(i + xStart, yStart, true);
			_gof.setField(i + xStart, yStart + sideLength - 1, true);
		}
		for (int i = 1; i < (sideLength - 1); i++) {
			_gof.setField(xStart, i + yStart, true);
			_gof.setField(xStart + sideLength - 1, i + yStart, true);
		}
	}

	public void flyer(int x, int y) {
		_gof.setField(x + 6, y + 1, true);
		_gof.setField(x + 6, y + 2, true);
		_gof.setField(x + 4, y, true);
		_gof.setField(x + 4, y + 3, true);

		_gof.setField(x, y + 1, true);
		_gof.setField(x, y + 2, true);
		_gof.setField(x + 2, y, true);
		_gof.setField(x + 2, y + 3, true);
	}

	public void random(double possibility) {
		for (int i = 0; i < GofMain.FIELD_X; i++) {
			for (int j = 0; j < GofMain.FIELD_Y; j++) {
				_gof.setField(i, j, Math.random() > (1 - possibility));
			}
		}
	}
}
