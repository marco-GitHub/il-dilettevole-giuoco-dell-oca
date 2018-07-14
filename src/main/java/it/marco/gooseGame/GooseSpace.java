package it.marco.gooseGame;

public class GooseSpace implements Space {
	
	private final int index;

	public GooseSpace(int index) {
		this.index = index;
	}

	@Override
	public MoveResult additionalAction(int index, int addend, Player player, Board board) {
		int destIndex = index + addend;
		return new MoveResult(destIndex, ". " + player.getName() + " moves again and goes to " + board.getSpaces()[destIndex].getName());
	}

	@Override
	public String getName() {
		return index + ", The Goose";
	}
	
	@Override
	public boolean canMoveAgain() {
		return true;
	}

}
