import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Sabishinzou on 03.05.14.
 */
public class GamePlay {
    public static void playXhumanOai(GameField gameField, Human human, AiSecondPlayer ai) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("New Game!");
        gameField.showGameField();
        System.out.println("Your turn, Human:");
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        human.move(gameField, i-1, j-1);
        gameField.showGameField();
        GameField.Coordinate first_turn = gameField.last_paste;

            while (gameField.hasEmptyCells()) {
                System.out.println("AI's turn:");
                GameField.Coordinate rule1 = gameField.findPlaceForLast(ai.sign);
                GameField.Coordinate rule2 = gameField.findPlaceForLast(human.sign);
                if ((rule1 != null)||(rule2 != null)){
                    if (rule1 != null) {
                        ai.move(gameField, rule1.row, rule1.column);
                        gameField.showGameField();
                        System.out.println("I WON YOU stupid Human!!!");
                        break;
                    }
                    else {
                        ai.move(gameField, rule2.row, rule2.column);
                        gameField.showGameField();
                    }
                }
                else {
                    if ((first_turn.column == 1)&&(first_turn.row == 1))
                        ai.strategyHumFirstPasteCenter(gameField);
                    else
                        ai.strategyHumFirstPasteCorner(gameField);
                    gameField.showGameField();
                }
                System.out.println("Your turn again, Human:");
                i = scanner.nextInt();
                j = scanner.nextInt();
                human.move(gameField, i-1, j-1);
                gameField.showGameField();
            }

        if (!gameField.hasEmptyCells())
            System.out.println("DRAW.");
        System.out.println("Game Ended. Thanks for playing.");
    }
}
