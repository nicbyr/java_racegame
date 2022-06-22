/**Sets the squres on the RaceTrack to offroadm or onroad*/
public class PlaceSquares
{
    private int realWidth, realHeight;
    private SquareTypes[][] squareType;

    public PlaceSquares(int width, int height, SquareTypes[][] squareType) {
	this.squareType = squareType;
	this.realWidth = width + 2;
	this.realHeight = height + 2;
    }

    protected void placeSquares(int track) {
	if (track == 2) {
	    setRoad();
	} else if (track == 1) {
	    setRoad();
	    changeSquares();
	}
    }

    private void setRoad() {
	for (int col = 0; col < realWidth; col++) {
	    for (int row = 0; row < realHeight; row++) {
		if (setOutSide(col, row)) {
		    squareType[col][row] = SquareTypes.OUTSIDE;
		} else if (setOuterOffRoad(col, row) || setInnerOffRoad(col, row)) {
		    squareType[col][row] = SquareTypes.OFFROAD;
		} else {
		    squareType[col][row] = SquareTypes.ONROAD;
		}
	    }
	}
    }

    /** We change some squaretypes manually to be able to design the track exactly as we want.
     * 	The magical numbers represents columns and rows of the squaretypes that we changed manually.*/
    private void changeSquares() {
	for (int col = 7; col < 21; col++) {
	    for (int row = 5; row < 10; row++) {
		if (col == 7 && row > 6 && row < 10 || col > 9 && col < 19 && row == 7) {
		    squareType[col][row] = SquareTypes.OFFROAD;
		} else {
		    squareType[col][row] = SquareTypes.ONROAD;
		}
	    }
	}
	for (int col = 9; col < 16; col++) {
	    for (int row = 1; row < 6; row++) {
		if (col == 9 || col == 10 && row > 0 && row < 6) {
		    squareType[col][row] = SquareTypes.OFFROAD;
		} else if (col > 10 && col < 17 && row == 1) {
		    squareType[col][row] = SquareTypes.ONROAD;
		}
	    }
	}
	squareType[10][6] = SquareTypes.OFFROAD;
	squareType[13][3] = SquareTypes.OFFROAD;
	squareType[14][3] = SquareTypes.OFFROAD;
	squareType[7][4] = SquareTypes.ONROAD;
	squareType[8][4] = SquareTypes.ONROAD;
	squareType[11][4] = SquareTypes.ONROAD;
	squareType[12][4] = SquareTypes.ONROAD;
    }

    private boolean setOuterOffRoad(int col, int row) {
	return (col == 1 || col == realWidth - 2) || (row == 1 || row == realHeight - 2);
    }

    private boolean setInnerOffRoad(int col, int row) {
	return (col > 3 && col < realWidth - 4) && (row > 3 && row < realHeight - 4);
    }

    private boolean setOutSide(int col, int row) {
	return col == 0 || col == realWidth - 1 || row == 0 || row == realHeight - 1;
    }
}
