package it.marco.gooseGame;

public interface Space {

	public MoveResult additionalAction(int index, int addend, Player player, Board board);

	public String getName();

	default public boolean canMoveAgain() {
		return false;
	}
}
