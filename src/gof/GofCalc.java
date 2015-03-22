
package gof;



public class GofCalc {

	private boolean[][] _field = new boolean[GofMain.FIELD_X][GofMain.FIELD_Y];
	private boolean[][] _changeMatrix = new boolean[GofMain.FIELD_X][GofMain.FIELD_Y];

	public GofCalc() {
	}

	public void recalculateField() {
		boolean[][] nextGenField = new boolean[GofMain.FIELD_X][GofMain.FIELD_Y];
		for (int i = 0; i < GofMain.FIELD_X; i++) {
			for (int j = 0; j < GofMain.FIELD_Y; j++) {
				nextGenField[i][j] = calcLifeHood(i, j);
				if (nextGenField[i][j] == _field[i][j]) {
					_changeMatrix[i][j] = false;
				}
				else {
					_changeMatrix[i][j] = true;
				}
			}
		}
		_field = nextGenField;
	}

	private boolean calcLifeHood(int xCoord, int yCoord) {
		int leftBound = previousIndex(xCoord, GofMain.FIELD_X);
		int rightBound = followingIndex(xCoord, GofMain.FIELD_X);
		int upperBound = previousIndex(yCoord, GofMain.FIELD_Y);
		int lowerBound = followingIndex(yCoord, GofMain.FIELD_Y);

		int lifeNeighbourCount = 0;

		lifeNeighbourCount += integerAliveStatus(leftBound, upperBound);
		lifeNeighbourCount += integerAliveStatus(leftBound, yCoord);
		lifeNeighbourCount += integerAliveStatus(leftBound, lowerBound);
		lifeNeighbourCount += integerAliveStatus(rightBound, upperBound);
		lifeNeighbourCount += integerAliveStatus(rightBound, yCoord);
		lifeNeighbourCount += integerAliveStatus(rightBound, lowerBound);
		lifeNeighbourCount += integerAliveStatus(xCoord, upperBound);
		lifeNeighbourCount += integerAliveStatus(xCoord, lowerBound);

		if (_field[xCoord][yCoord]) {// field is alive
			return GofMain.liveCellRules[lifeNeighbourCount];
		}

		if (GofMain.GARDEN_EDEN) {
			if (xCoord > GofMain.EDEN_X_START && // 
					xCoord < GofMain.EDEN_X_END && // 
					yCoord > GofMain.EDEN_Y_START && //
					yCoord < GofMain.EDEN_Y_END) {// 
				return GofMain.edenDeadCellRules[lifeNeighbourCount];
			}
		}
		return GofMain.deadCellRules[lifeNeighbourCount];
	}

	private int integerAliveStatus(int x, int y) {
		return _field[x][y] == true ? 1 : 0;
	}

	private int previousIndex(int currentIndex, int space) {
		return currentIndex == 0 ? space - 1 : currentIndex - 1;
	}

	private int followingIndex(int currentIndex, int space) {
		return currentIndex >= (space - 1) ? 0 : currentIndex + 1;
	}

	public void setField(int i, int j, boolean isAlive) {
		if (_field[i][j] != isAlive) {
			_changeMatrix[i][j] = true;
		}
		else {
			_changeMatrix[i][j] = false;
		}
		_field[i][j] = isAlive;
	}

	//	public void printField() {
	//		StringBuilder builder = new StringBuilder();
	//		builder.append((char) 0xDA);
	//		for (int i = 0; i < GofMain.FIELD_SIZE; i++) {
	//			builder.append((char) 0xC4);
	//		}
	//		builder.append((char) 0xBF);
	//		builder.append("\n");
	//
	//		for (int i = 0; i < GofMain.FIELD_SIZE; i++) {
	//			int secondRow = i + 1;
	//			builder.append((char) 0xB3);
	//
	//			for (int j = 0; j < GofMain.FIELD_SIZE; j++) {
	//				boolean upperCell = _field[i][j];
	//				boolean lowerCell = false;
	//				if (secondRow < GofMain.FIELD_SIZE) {
	//					lowerCell = _field[secondRow][j];
	//				}
	//				if (upperCell && lowerCell) {
	//					builder.append((char) 0xDB);
	//				}
	//				else if (upperCell) {
	//					builder.append((char) 0xDF);
	//				}
	//				else if (lowerCell) {
	//					builder.append((char) 0xDC);
	//				}
	//				else {
	//					builder.append(" ");
	//				}
	//			}
	//			i++;
	//			builder.append((char) 0xB3);
	//			builder.append("\n");
	//		}
	//
	//		builder.append((char) 0xC0);
	//		for (int i = 0; i < GofMain.FIELD_SIZE; i++) {
	//			builder.append((char) 0xC4);
	//		}
	//		builder.append((char) 0xD9);
	//		builder.append("\n");
	//
	//		System.out.println(builder.toString());
	//	}

	public boolean[][] getField() {
		return _field;
	}

	public boolean[][] getChangedMatrix() {
		return _changeMatrix;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < GofMain.FIELD_X; i++) {
			for (int j = 0; j < GofMain.FIELD_Y; j++) {
				if (_field[i][j]) {
					builder.append("X");
				}
				else {
					builder.append(" ");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
