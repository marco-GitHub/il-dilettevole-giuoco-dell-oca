package it.marco.gooseGame;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		try {

			IlDilettevoleGiuocoDelloca game = new IlDilettevoleGiuocoDelloca();

			while (!game.isFinished()) {

				if (!scan.hasNext())
					return;

				try {

					String cmd = scan.next();

					Player currentPlayer = null;

					if ("add".equals(cmd) && scan.hasNext() && "player".equals(scan.next())) {

						String playerName = scan.nextLine().trim();
						
						if(playerName == null || "".equals(playerName))
							throw new IllegalArgumentException();

						Player player = new Player(playerName);

						if (game.getPlayers().contains(player)) {

							System.out.println(player.getName() + " already existing player");
							continue;

						} else {

							game.getPlayers().add(player);
							currentPlayer = player;
							System.out.println("players: " + game.printPlayers());

						}

					} else if ("move".equals(cmd) && scan.hasNext()) {

						Player player = new Player(scan.next());

						if (!game.getPlayers().contains(player)) {

							throw new IllegalArgumentException("Player " + player.getName() + "not exists!!!");

						} else {

							currentPlayer = game.getPlayers().stream().filter(e -> e.getName().equals(player.getName()))
									.findFirst().get();

							String diceValuesArg = scan.nextLine();

							if (diceValuesArg == null || diceValuesArg.trim().equals("")) {

								game.play(currentPlayer);

							} else {

								String[] diceValues = diceValuesArg.split(",");
								int firstDiceValue = Integer.parseInt(diceValues[0].trim());
								int secondDiceValue = Integer.parseInt(diceValues[1].trim());

								if (firstDiceValue > IlDilettevoleGiuocoDelloca.DICE_SIDES || firstDiceValue < 1
										|| secondDiceValue > IlDilettevoleGiuocoDelloca.DICE_SIDES
										|| secondDiceValue < 1)
									throw new IllegalArgumentException(
											"Bad dice value" + firstDiceValue + ", " + secondDiceValue);

								game.play(currentPlayer, firstDiceValue, secondDiceValue);

							}
						}

					} else {

						throw new IllegalArgumentException("Invalid input!!!");

					}

				} catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e) {

					System.out.println("Input not valid, Please provide a valid one");
					continue;

				}

			}

		} catch (Exception e) {

			System.out.println("The goose is dead; kill all developers");
			e.printStackTrace();

		} finally {

			scan.close();

		}

	}

}
