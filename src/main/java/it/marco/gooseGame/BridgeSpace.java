package it.marco.gooseGame;

public class BridgeSpace implements Space {
	
	private static final int destIndex = 12;

	@Override
	public MoveResult additionalAction(int index, int addend, Player player, Board board) {
		return new MoveResult(destIndex, ". " + player.getName() + " jumps to " + destIndex);
	}

	@Override
	public String getName() {
		return "The Bridge";
	}

}
