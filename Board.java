
public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private String[][] f = new String[NUM_OF_ROW + 1][NUM_OF_COLUMNS * 2 + 1];
	

	public Board() {

	}

	public void printBoard() {

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS * 2 + 1; j++) {
				System.out.print(f[i][j]);
			}
			System.out.println();
		}
	}

	public boolean isFull(int j) {
		if (f[0][j] != " ") {
			return true;
		}
		return false;
	}

	public boolean containsWin() {
		return containsHorizontal() || containsDiagonal() || containsVertical();
	}

	public boolean containsHorizontal() {
		for (int i = 0; i < NUM_OF_ROW; i++) { // looping over each row from 0 - 5
			for (int j = 0; j < NUM_OF_COLUMNS; j += 2) {// looping over each column from 0 - 15 (includes dividers (|))

				if ((f[i][j + 1] != " ") && (f[i][j + 3] != " ") && (f[i][j + 5] != " ") && (f[i][j + 7] != " ")) {

					if (f[i][j + 1].equals(f[i][j + 3]) && f[i][j + 3].equals(f[i][j + 5])

							&& f[i][j + 5].equals(f[i][j + 7])) {
						// check that all groups of four are actually equal
						return true;
					}
				}

			}

		}
		return false;
	}

	public boolean containsVertical() {
		for (int i = 1; i < (NUM_OF_COLUMNS * 2) + 1; i += 2) { // must include columns that only have lines and miss
																// them by only iterating over odd numbers thus += 2 and
																// i = 1
			for (int j = 0; j < 7 % 4; j++) { // iterate 3 times, because there are three sets of winning options in
												// each column
				if ((f[j][i] != " ") && (f[j + 1][i] != " ") && (f[j + 2][i] != " ") && (f[j + 3][i] != " ")) {// check
																												// to
																												// make
																												// sure
																												// spots
																												// aren't
																												// empty
					if (f[j][i].equals(f[j + 1][i]) && f[j + 1][i].equals(f[j + 2][i])
							&& f[j + 2][i].equals(f[j + 3][i])) {
						// check that all groups of four are actually equal
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean containsDiagonal() {
		for (int i = 0; i < 3; i++) { // three lines that could contain four diagonally, starting from leftmost column
			for (int j = 1; j < 9; j = j + 2) { //need to start at an odd number to hit gaps...goes to nine to touch the last index with j+6 or (13)
				if ((f[i][j] != " ") && (f[i + 1][j + 2] != " ") && (f[i + 2][j + 4] != " ")
						&& (f[i + 3][j + 6] != " ")) {
					if ((f[i][j].equals(f[i + 1][j + 2])) && (f[i][j].equals(f[i + 2][j + 4])) //makes sure that all the symbols are the same 
							&& (f[i][j].equals(f[i + 3][j + 6]))) {
						return true;
					}

				}

			}

		}
		for (int i = 0; i < 3; i++) {// checks all rows with possible diagonal wins

			for (int j = 7; j < NUM_OF_COLUMNS * 2 + 1; j += 2) { // starts on the middle row and checks to the right for all diagonals
												// stemming from 4 columns

				if ((f[i][j] != " ") && (f[i + 1][j - 2] != " ") && (f[i + 2][j - 4] != " ")
						&& (f[i + 3][j - 6] != " ")) {
					if ((f[i][j]).equals(f[i + 1][j - 2]) && (f[i][j]).equals(f[i + 2][j - 4])
							&& (f[i][j]).equals(f[i + 3][j - 6])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isTie() {
		for (int i = 1; i < 15; i += 2) {
			if (f[0][i].equals(" ")) {
				return false;
			}
		}
		return true;

	}

	public void reset() {
		for (int i = 0; i < NUM_OF_ROW + 1; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS * 2 + 1; j++) {
				if (j % 2 == 0)
					f[i][j] = "|"; // every even value becomes a line
				else
					f[i][j] = " "; // every odd value becomes one of the spaces between the columns
				if (i == 6) {
					if (j % 2 == 0) f[i][j] = " "; // the bottom of the grid
					if (j % 2 != 0) f[i][j] = "-";// also bottom of the grid
					f[i][0] = "|"; // tried to make it look like the little stands on a connect four board 
					f[i][14] = "|";
						

				}
			}
		}
	}

	public void addChip(int column, char symbol) {

		for (int i = 5; i >= 0; i--) {
			if (f[i][column] == " ") {
				f[i][column] = String.valueOf(symbol);
				break;

			}
		}
	}



	public int winningColumn() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS + 3; j += 2) {
				if ((f[i][j + 1] != " ") && (f[i][j + 3] != " ") && (f[i][j + 5] != " ")) {
					if (f[i][j + 1].equals(f[i][j + 3]) && f[i][j + 3].equals(f[i][j + 5])) {
						if ((j == 0) && (f[i][j + 7] == " ")) {
							return j + 7;
						}

						else if ((j != 8) && (j != 0)) {
							if ((f[i][j - 1] != " ") && f[i][j + 7].equals(" ")) {
								return j + 7;
							}
							if (f[i][j - 1].equals(" ") && f[i][j + 7].equals(" ")) {
								return j + 7;
							}
							if (f[i][j - 1].equals(" ") && f[i][j + 7] != " ") {
								return j - 1;
							}

						} else if (j != 0) {
							if (f[i][j - 1].equals(" ")) {
								return j - 1;
							}
						}

					}
				}
			}
		}
		return 0;
	}

	public int winningGap() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j += 2) {
				if (f[i][j + 1] != " " && f[i][j + 3].equals(" ") && f[i][j + 5] != " " && f[i][j + 7] != " "
						&& f[i][j + 1].equals(f[i][j + 5]) && f[i][j + 5].equals(f[i][j + 7])) {
					return j + 3;
				}
				if (f[i][j + 1] != " " && f[i][j + 3] != " " && f[i][j + 5].equals(" ") && f[i][j + 7] != " "
						&& f[i][j + 1].equals(f[i][j + 3]) && f[i][j + 3].equals(f[i][j + 7])) {
					return j + 5;
				}
			}
		}
		return 0;
	}

	public int winningVertical() {
		for (int i = 1; i < (NUM_OF_COLUMNS * 2) + 1; i += 2) {
			for (int j = 0; j < 4; j++) {
				if ((f[j][i] != " ") && (f[j + 1][i] != " ") && (f[j + 2][i] != " ")) {
					if (f[j][i].equals(f[j + 1][i]) && f[j + 1][i].equals(f[j + 2][i])) {
						if (j != 0) {
							if (f[j - 1][i].equals(" "))
								return i;
						}
					}
				}
			}
		}
		return 0;
	}

	public int winningDiagonalLeft() { // add gap checks for this function
		for (int i = 0; i < 3; i++) { // three lines that could contain four diagonally, starting from leftmost column
			for (int j = 1; j < 9; j += 2) {
				if ((f[i][j] != " ") && (f[i + 1][j + 2] != " ")
						&& (f[i + 2][j + 4] != " " && f[i][j].equals(f[i + 1][j + 2])
								&& f[i + 2][j + 4].equals(f[i][j]))
						|| (f[i + 1][j + 2] != " ") && (f[i + 2][j + 4] != " ") && f[i + 3][j + 6] != " "
								&& f[i + 2][j + 4].equals(f[i + 1][j + 2]) && f[i + 2][j + 4].equals(f[i + 3][j + 6])) {
					if (j == 1 && f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (j == 1 && f[i][j] != " " && f[i + 3][i + 6].equals(" ")) {
						return j + 6;
					} else if ((i == 2) && (f[i][j].equals(" ")) && (f[i + 1][j] != " ")) {
						return j;
					} else if (i == 2 && f[i][j] != " " && f[i + 3][j + 6].equals(" ")) {
						return j + 6;
					} else if (i == 0 && f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (i == 0 && f[i][j] != " " && f[i + 3][j + 6].equals(" ") && f[i + 4][j + 6] != " ") {
						return j - 6;
					} else if (f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (f[i][j] != " " && f[i + 3][j + 6].equals(" ") && f[i + 4][j + 6] != " ") {
						return j + 6;
					}

				}

			}

		}
		return 0;
	}

	public int winningDiagonalRight() {

		for (int i = 0; i < 3; i++) {// checks all rows with possible diagonal wins
			for (int j = 7; j < 15; j += 2) { // starts on the middle row and checks to the right for all diagonals
				if ((f[i][j] != " ") && (f[i + 1][j - 2] != " ")
						&& (f[i + 2][j - 4] != " " && f[i][j].equals(f[i + 1][j - 2])
								&& f[i][j].equals(f[i + 2][j - 4]))
						|| (f[i + 1][j - 2] != " ") && (f[i + 2][j - 4] != " ") && (f[i + 3][j - 6] != " ")
								&& f[i + 1][j - 2].equals(f[i + 2][j - 4]) && f[i + 1][j - 2].equals(f[i + 3][j - 6])) {
					if (j == 13 && f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (j == 13 && f[i][j] != " " && f[i + 3][j - 6].equals(" ")) {
						return j - 6;
					} else if ((i == 2) && (f[i][j].equals(" ")) && (f[i + 1][j] != " ")) {
						return j;
					} else if (i == 2 && f[i][j] != " " && f[i + 3][j - 6].equals(" ")) {
						return j - 6;
					} else if (i == 0 && f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (i == 0 && f[i][j] != " " && f[i + 3][j - 6].equals(" ") && f[i + 4][j - 6] != " ") {
						return j - 6;
					} else if (f[i][j].equals(" ") && f[i + 1][j] != " ") {
						return j;
					} else if (f[i][j] != " " && f[i + 3][j - 6].equals(" ") && f[i + 4][j - 6] != " ") {
						return j - 6;
					}

				}

			}
		}
		return 0;
	}

	public int diagonalGapLeft() {
		for (int i = 0; i < 3; i++) { // three lines that could contain four diagonally, starting from leftmost column
			for (int j = 1; j < 9; j += 2) {
				if (f[i][j] != " " && f[i + 1][j + 2].equals(" ") && f[i + 2][j + 4] != " " && f[i + 3][j + 6] != " "
						&& f[i][j].equals(f[i + 2][j + 4]) && f[i + 3][j + 6].equals(f[i + 2][j + 4])) {
					return j + 2;
				}
				else if (f[i][j] != " " && f[i + 1][j + 2] != " " && f[i + 2][j + 4].equals(" ") && f[i + 3][j + 6] != " "
						&& f[i][j].equals(f[i + 1][j + 2]) && f[i + 3][j + 6].equals(f[i + 1][j + 2])) {
							return j + 4;

			}
		}
		
	}
	return 0;
}

	public int diagonalGapRight() {

		for (int i = 0; i < 3; i++) {// checks all rows with possible diagonal wins
			for (int j = 7; j < 15; j += 2) {
				if ((f[i][j] != " ") && (f[i + 1][j - 2].equals(" ")) && (f[i + 2][j - 4] != " ")
						&& (f[i + 3][j - 6] != " ") && (f[i][j]).equals(f[i + 2][j - 4])
						&& (f[i][j]).equals(f[i + 3][j - 6])) {
							return j - 2;
						}
						else if ((f[i][j] != " ") && (f[i + 1][j - 2] != " ") && (f[i + 2][j - 4].equals(" "))
						&& (f[i + 3][j - 6] != " ") && (f[i][j]).equals(f[i + 1][j - 2])
						&& (f[i + 1][j - 2]).equals(f[i + 3][j - 6])) {
							return j - 4;
						}
					}
		
	}
	return 0;
}

public int winningColumn(String symbol) {
	for (int i = 0; i < NUM_OF_ROW; i++) {
		for (int j = 0; j < NUM_OF_COLUMNS + 3; j += 2) {
			if ((f[i][j + 1].equals(symbol)) && (f[i][j + 3].equals(symbol)) && (f[i][j + 5].equals(symbol))) {
					if ((j == 0) && (f[i][j + 7] == " ")) {
						return j + 7;
					}

					else if ((j != 8) && (j != 0)) {
						if ((f[i][j - 1].equals(symbol)) && f[i][j + 7].equals(" ")) {
							return j + 7;
						}
						if (f[i][j - 1].equals(" ") && f[i][j + 7].equals(" ")) {
							return j + 7;
						}
						if (f[i][j - 1].equals(" ") && f[i][j + 7].equals(symbol)) {
							return j - 1;
						}

					} else if (j != 0) {
						if (f[i][j - 1].equals(" ")) {
							return j - 1;
						}
					}

				
			}
		}
	}
	return 0;
}

public int winningGap(String symbol) {

	for (int i = 0; i < NUM_OF_ROW; i++) {
		for (int j = 0; j < NUM_OF_COLUMNS; j += 2) {
			if (f[i][j + 1].equals(symbol)&& f[i][j + 3].equals(" ") && f[i][j + 5].equals(symbol) && f[i][j + 7].equals(symbol)
					&& f[i][j + 1].equals(f[i][j + 5]) && f[i][j + 5].equals(f[i][j + 7])) {
				return j + 3;
			}
			if (f[i][j + 1].equals(symbol) && f[i][j + 3].equals(symbol) && f[i][j + 5].equals(" ") && f[i][j + 7].equals(symbol)
					&& f[i][j + 1].equals(f[i][j + 3]) && f[i][j + 3].equals(f[i][j + 7])) {
				return j + 5;
			}
		}
	}
	return 0;
}
public int winningVertical(String symbol) {
	for (int i = 1; i < (NUM_OF_COLUMNS * 2) + 1; i += 2) {
		for (int j = 0; j < 4; j++) {
			if ((f[j][i].equals(symbol)) && (f[j + 1][i].equals(symbol)) && (f[j + 2][i].equals(symbol))) {
				if (f[j][i].equals(f[j + 1][i]) && f[j + 1][i].equals(f[j + 2][i])) {
					if (j != 0) {
						if (f[j - 1][i].equals(" "))
							return i;
					}
				}
			}
		}
	}
	return 0;
}
public int winningDiagonalLeft(String symbol) { // add gap checks for this function
	for (int i = 0; i < 3; i++) { // three lines that could contain four diagonally, starting from leftmost column
		for (int j = 1; j < 9; j += 2) {
			if ((f[i][j].equals(symbol)) && (f[i + 1][j + 2].equals(symbol))
					&& (f[i + 2][j + 4].equals(symbol) && f[i][j].equals(f[i + 1][j + 2])
							&& f[i + 2][j + 4].equals(f[i][j]))
					|| (f[i + 1][j + 2].equals(symbol)) && (f[i + 2][j + 4].equals(symbol)) && f[i + 3][j + 6].equals(symbol)
							&& f[i + 2][j + 4].equals(f[i + 1][j + 2]) && f[i + 2][j + 4].equals(f[i + 3][j + 6])) {
				if (j == 1 && f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (j == 1 && f[i][j].equals(symbol) && f[i + 3][i + 6].equals(" ")) {
					return j + 6;
				} else if ((i == 2) && (f[i][j].equals(" ")) && (f[i + 1][j].equals(symbol))) {
					return j;
				} else if (i == 2 && f[i][j].equals(symbol) && f[i + 3][j + 6].equals(" ")) {
					return j + 6;
				} else if (i == 0 && f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (i == 0 && f[i][j].equals(symbol) && f[i + 3][j + 6].equals(" ") && f[i + 4][j + 6].equals(symbol)) {
					return j - 6;
				} else if (f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (f[i][j].equals(symbol) && f[i + 3][j + 6].equals(" ") && f[i + 4][j + 6].equals(symbol)) {
					return j + 6;
				}

			}

		}

	}
	return 0;
}
public int winningDiagonalRight(String symbol) {

	for (int i = 0; i < 3; i++) {// checks all rows with possible diagonal wins
		for (int j = 7; j < 15; j += 2) { // starts on the middle row and checks to the right for all diagonals
			if ((f[i][j].equals(symbol)) && (f[i + 1][j - 2].equals(symbol))
					&& (f[i + 2][j - 4].equals(symbol) && f[i][j].equals(f[i + 1][j - 2])
							&& f[i][j].equals(f[i + 2][j - 4]))
					|| (f[i + 1][j - 2].equals(symbol)) && (f[i + 2][j - 4].equals(symbol)) && (f[i + 3][j - 6].equals(symbol))
							&& f[i + 1][j - 2].equals(f[i + 2][j - 4]) && f[i + 1][j - 2].equals(f[i + 3][j - 6])) {
				if (j == 13 && f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (j == 13 && f[i][j].equals(symbol) && f[i + 3][j - 6].equals(" ")) {
					return j - 6;
				} else if ((i == 2) && (f[i][j].equals(" ")) && (f[i + 1][j].equals(symbol))) {
					return j;
				} else if (i == 2 && f[i][j].equals(symbol) && f[i + 3][j - 6].equals(" ")) {
					return j - 6;
				} else if (i == 0 && f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (i == 0 && f[i][j].equals(symbol) && f[i + 3][j - 6].equals(" ") && f[i + 4][j - 6].equals(symbol)) {
					return j - 6;
				} else if (f[i][j].equals(" ") && f[i + 1][j].equals(symbol)) {
					return j;
				} else if (f[i][j].equals(symbol) && f[i + 3][j - 6].equals(" ") && f[i + 4][j - 6].equals(symbol)) {
					return j - 6;
				}

			}

		}
	}
	return 0;
}

public int diagonalGapLeft(String symbol) {
	for (int i = 0; i < 3; i++) { // three lines that could contain four diagonally, starting from leftmost column
		for (int j = 1; j < 9; j += 2) {
			if (f[i][j].equals(symbol) && f[i + 1][j + 2].equals(" ") && f[i + 2][j + 4].equals(symbol) && f[i + 3][j + 6].equals(symbol)
					&& f[i][j].equals(f[i + 2][j + 4]) && f[i + 3][j + 6].equals(f[i + 2][j + 4])) {
				return j + 2;
			}
			else if (f[i][j].equals(symbol) && f[i + 1][j + 2].equals(symbol) && f[i + 2][j + 4].equals(" ") && f[i + 3][j + 6].equals(symbol)
					&& f[i][j].equals(f[i + 1][j + 2]) && f[i + 3][j + 6].equals(f[i + 1][j + 2])) {
						return j + 4;

		}
	}
	
}
return 0;
}
public int diagonalGapRight(String symbol) {

	for (int i = 0; i < 3; i++) {// checks all rows with possible diagonal wins
		for (int j = 7; j < 15; j += 2) {
			if ((f[i][j].equals(symbol)) && (f[i + 1][j - 2].equals(" ")) && (f[i + 2][j - 4].equals(symbol))
					&& (f[i + 3][j - 6].equals(symbol)) && (f[i][j]).equals(f[i + 2][j - 4])
					&& (f[i][j]).equals(f[i + 3][j - 6])) {
						return j - 2;
					}
					else if ((f[i][j].equals(symbol)) && (f[i + 1][j - 2].equals(symbol)) && (f[i + 2][j - 4].equals(" "))
					&& (f[i + 3][j - 6].equals(symbol)) && (f[i][j]).equals(f[i + 1][j - 2])
					&& (f[i + 1][j - 2]).equals(f[i + 3][j - 6])) {
						return j - 4;
					}
				}
	
}
return 0;
}
}
