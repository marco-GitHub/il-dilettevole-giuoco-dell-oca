package it.marco.gooseGame;

public class Board {

	private final Space[] spaces;

	private Board(int boardLenght) {
		this.spaces = new Space[boardLenght];
	}

	public static Board createBoard(int boardLenght) {
		Board board = new Board(boardLenght);
		
		for(int i = 0 ; i < boardLenght; i++) {
			
			switch(i) {
			case 0:
				board.spaces[i] = new StartSpace();
				break;
			case 6:
				board.spaces[i] = new BridgeSpace();
				break;
			case 5: 
			case 9:
			case 14:
			case 18:
			case 23:
			case 27:
				board.spaces[i] = new GooseSpace(i);
				break;
			default:
				board.spaces[i] = new RegularSpace(i);
			}
			
		}
		
		return board;
	}

	public Space[] getSpaces() {
		return spaces;
	}

}
