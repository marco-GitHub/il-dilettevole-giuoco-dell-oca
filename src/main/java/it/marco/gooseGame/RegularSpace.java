package it.marco.gooseGame;

public class RegularSpace implements Space {

	private final int index;

	public RegularSpace(int index) {
		this.index = index;
	}

	@Override
	public MoveResult additionalAction(int index, int addend, Player player, Board board) {
		return new MoveResult(this.index, "");
	}

	@Override
	public String getName() {
		return "" + index;
	}

}
