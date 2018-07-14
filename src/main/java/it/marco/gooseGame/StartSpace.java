package it.marco.gooseGame;

public class StartSpace implements Space {

	@Override
	public MoveResult additionalAction(int index, int addend, Player player, Board board) {
		return new MoveResult(0, "");
	}

	@Override
	public String getName() {
		return "Start";
	}
}
