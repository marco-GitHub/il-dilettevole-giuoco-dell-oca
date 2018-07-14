package it.marco.gooseGame;

public class MoveResult {

	private final int newIndex;
	private final String message;

	public MoveResult(int newIndex, String message) {

		this.newIndex = newIndex;
		this.message = message;

	}

	public int getNewIndex() {
		return newIndex;
	}

	public String getMessage() {
		return message;
	}

}
