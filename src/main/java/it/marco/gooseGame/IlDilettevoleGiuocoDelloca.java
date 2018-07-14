package it.marco.gooseGame;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IlDilettevoleGiuocoDelloca {
	
	private final static int BOARDLENGHT = 64;
	private final static int LASTSPACEINDEX = BOARDLENGHT - 1;
	public final static int DICE_SIDES = 6;
	
	private List<Player> players;
	private Board board;	
	private boolean finished;
	
	private static final String rollTemplate = "{0} rolls {1}, {2}.";
	private static final String moveTemplate = " {0} moves from {1} to {2}";
	
	public IlDilettevoleGiuocoDelloca() {
		this.board = Board.createBoard(BOARDLENGHT);
		this.players = new ArrayList<>();
		
		System.out.println("////////////////////////////////////////////////////////////////////");
		System.out.println("///Welcome to 'Il Dilettevole Giuoco Dell'oca'. Ready to start!!!///");
		System.out.println("////////////////////////////////////////////////////////////////////");
	}

	public boolean isFinished() {
		return this.finished;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		player.setIndex(0);
		players.add(player);
	}
	
	public String printPlayers() {
		return String.join(", ", players.stream().map(Player::getName).collect(Collectors.toList()));
	}

	public void play(Player player) {
		
		int diceRollOne = (int) (Math.random() * DICE_SIDES) + 1;
		int diceRollTwo = (int) (Math.random() * DICE_SIDES) + 1;
		
		play(player, diceRollOne, diceRollTwo);
		
	}

	public void play(Player player, int firstDiceValue, int secondDiceValue) {
		
		int startIndex = player.getIndex();
		int destIndex= player.getIndex() + firstDiceValue + secondDiceValue;
		
		StringBuilder sb = new StringBuilder(MessageFormat.format(rollTemplate, player.getName(), firstDiceValue, secondDiceValue));
		
		if(destIndex == LASTSPACEINDEX) {
			
			finished = true;
			player.setIndex(destIndex);
			sb.append(MessageFormat.format("{0} moves from {1} to {2}. {0} Wins!!", player.getName(), startIndex, LASTSPACEINDEX));
			
		}
		else if (destIndex > LASTSPACEINDEX) {
			
			destIndex =  LASTSPACEINDEX - (destIndex - LASTSPACEINDEX);
			player.setIndex(destIndex);
			sb.append(MessageFormat.format("{0} moves from {1} to {2}. {0} bounces! {0} returns to {3}", player.getName(), startIndex, LASTSPACEINDEX, destIndex));
			
		}
		else {
			
			Space destSpace =  board.getSpaces()[destIndex];
			
			sb.append(MessageFormat.format(moveTemplate, player.getName(), startIndex, destSpace.getName()));
			
			do {
				
				MoveResult mr = destSpace.additionalAction(destIndex, firstDiceValue + secondDiceValue, player, board);
				
				sb.append(mr.getMessage());
				
				destIndex = mr.getNewIndex();
				destSpace = board.getSpaces()[destIndex];
				
			} while (board.getSpaces()[destIndex].canMoveAgain());
			
			Optional<Player> playerAtDestIndex= getPlayerAtIndex(destIndex);
			
			player.setIndex(destIndex);
			
			if(playerAtDestIndex.isPresent()) {
				
				playerAtDestIndex.get().setIndex(startIndex);
				
				sb.append(". On " + destIndex + " there is " + playerAtDestIndex.get().getName() + ", who returns to " + startIndex);
				
			}
		}
		
		System.out.println(sb.toString());	
		
	}

	private Optional<Player> getPlayerAtIndex(int index) {
		return players.stream().filter(p-> p.getIndex() == index).findFirst();
	}

	
	
}
