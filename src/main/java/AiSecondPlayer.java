/**
 * Created by Sabishinzou on 03.05.14.
 */
public class AiSecondPlayer extends Player implements iStrategies{
    //это компьютер ноликов, т.е. делающий второй ход
    //компьютера крестиков не будет, но по сути то же самое
    public AiSecondPlayer() {
        super('o');
    }

    @Override
    public void strategyHumFirstPasteCenter(GameField gameField) throws Exception {
        boolean cheked = false;
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0: {
                    if ((gameField.field[0][0] == '_')&&(!cheked)) {
                        this.move(gameField, 0, 0);
                        cheked = true;
                    }
                    break;
                }
                case 1: {
                    if ((gameField.field[0][2] == '_')&&(!cheked)) {
                        this.move(gameField, 0, 2);
                        cheked = true;
                    }
                    break;
                }
                case 2: {
                    if ((gameField.field[2][0] == '_')&&(!cheked)) {
                        this.move(gameField, 2, 0);
                        cheked = true;
                    }
                    break;
                }
                case 3: {
                    if ((gameField.field[2][2] == '_')&&(!cheked)) {
                        this.move(gameField, 2, 2);
                        cheked = true;
                    }
                    break;
                }
                default: break;
            }
        }
        if (!cheked) {
            for (int i = 0; i < 4; i++) {
                switch (i){
                    case 0: {
                        if (gameField.field[0][1] == '_') {
                            this.move(gameField, 0, 1);
                            cheked = true;
                        }
                        break;
                    }
                    case 1: {
                        if (gameField.field[1][0] == '_') {
                            this.move(gameField, 1, 0);
                            cheked = true;
                        }
                        break;
                    }
                    case 2: {
                        if (gameField.field[1][2] == '_') {
                            this.move(gameField, 1, 2);
                            cheked = true;
                        }
                        break;
                    }
                    case 3: {
                        if (gameField.field[2][1] == '_') {
                            this.move(gameField, 2, 1);
                            cheked = true;
                        }
                        break;
                    }
                    default: break;
                }
            }
        }
    }

    @Override
    public void strategyHumFirstPasteCorner(GameField gameField) throws Exception {
        boolean cheked = false;
        if (gameField.field[1][1] == '_') {
            this.move(gameField,1,1);
            cheked = true;
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0: {
                    if ((gameField.field[0][0] == 'x')&&(!cheked)) {
                        this.move(gameField, 2, 2);
                        cheked = true;
                    }
                    break;
                }
                case 1: {
                    if ((gameField.field[0][2] == 'x')&&(!cheked)) {
                        this.move(gameField, 2, 0);
                        cheked = true;
                    }
                    break;
                }
                case 2: {
                    if ((gameField.field[2][0] == 'x')&&(!cheked)) {
                        this.move(gameField, 0, 2);
                        cheked = true;
                    }
                    break;
                }
                case 3: {
                    if ((gameField.field[2][2] == 'x')&&(!cheked)) {
                        this.move(gameField, 0, 0);
                        cheked = true;
                    }
                    break;
                }
                default: break;
            }
        }
        if (!cheked)
            this.strategyHumFirstPasteCenter(gameField);
    }


}
