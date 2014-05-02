import org.omg.PortableServer._ServantActivatorStub;

import java.util.Stack;

/**
 * Created by Sabishinzou on 27.04.14.
 */
public class GameField {
    //довольно очевидно что это по сути игоровое поле куда вводятся крестики и нолики
    //не стал ничего приукрашивать и усложнять, поле это просто массив 3 на 3, крестик это символ x, нолик o, пустая клетка _.

    public char[][] field;
    public Coordinate last_paste;

    public class Coordinate { //вышло небрежно, но просто нужна была подобная структура для удобства
        public int row;
        public int column;

        public Coordinate() {
            coordInit(-1, -1);
        }

        public void coordInit(int i, int j) {
            this.row = i;
            this.column = j;
        }

        @Override
        public String toString() {
            return this.row + "," + this.column;
        }
    }

    public GameField(){
        this.field = this.initGameField();
        this.last_paste = new Coordinate();
    }

    public void showGameField() {
        System.out.println("GameField:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private char[][] initGameField(){
        char field[][] = new char[3][];
        for (int i = 0; i < 3; i++) {
            field[i] = new char[3];
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
        return field;
    }

    public char valOfCell(Coordinate c){
        return field[c.row][c.column];
    }

    public void paste(char sign, Coordinate c) throws Exception {
        if ((sign != 'x')&&(sign != 'o')) {
            if (!this.isOccupied(c)){
                this.field[c.row][c.column] = sign;
                last_paste.coordInit(c.row,c.column);
            }
            else
                throw new Exception("Cell is busy!");
        }
        else
            throw new Exception("Illegal symbol!");
    }

    public void paste(char sign, int i, int j) throws Exception {
        if ((sign == 'x')||(sign == 'o')) {
            if (field[i][j] == '_') {
                this.field[i][j] = sign;
                last_paste.coordInit(i,j);
            }
            else
                throw new Exception("Cell is busy!");
        }
        else
            throw new Exception("Illegal symbol!");
    }

    public boolean isOccupied(Coordinate c) {
        if (this.valOfCell(c) == '_')
            return false;
        else return true;
    }

    public boolean hasEmptyCells() {
        boolean b = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.field[i][j] == '_')
                    b = true;
            }
        }
        return b;
    }

    public Coordinate findPlaceForLast(char sign) {
        //возвражает координату клетки для образования линии из 3-х sign(x or o) если нет возвращает null
        //нужно для поиска своего выйгрышного хода или выйгрышного хода соперника(чтобы помешать)
        //по сути просто перибираем все пустые клетки где можно построить 3 полряд
        Coordinate win_cell = new Coordinate();
        Coordinate c = new Coordinate();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c.coordInit(i, j);
                if (!this.isOccupied(c)) {
                    if (isWinCell(c, sign)) {
                        win_cell.coordInit(c.row,c.column);
                    }
                }
            }
        }
        if (win_cell.row != -1)
            return win_cell;
        else return null;
    }

    private boolean isWinCell(Coordinate cell, char sign) {
    //наверное можно проще, чем проверять для каждого вида клетки всевозможные линии, но так было быстрее (да и эффективность не пострадала) чем изобретать велосипед
        boolean b = false;
        switch (cell.row){
            case 0: {
                switch (cell.column){
                    case 0: {
                        if (((this.field[0][1] == sign)&&(this.field[0][2] == sign))
                                ||((this.field[1][0] == sign)&&(this.field[2][0] == sign))
                                ||((this.field[1][1] == sign)&&(this.field[2][2] == sign)))
                            b = true;
                        break;
                    }
                    case 1: {
                        if (((this.field[0][0] == sign)&&(this.field[0][2] == sign))
                                ||((this.field[1][1] == sign)&&(this.field[2][1] == sign)))
                            b = true;
                        break;
                    }
                    case 2: {
                        if (((this.field[0][0] == sign)&&(this.field[0][1] == sign))
                                ||((this.field[1][2] == sign)&&(this.field[2][2] == sign))
                                ||((this.field[1][1] == sign)&&(this.field[2][0] == sign)))
                        b = true;
                        break;
                    }
                    default: break;
                }
                break;
            }
            case 1: {
                switch (cell.column){
                    case 0: {
                        if (((this.field[1][1] == sign)&&(this.field[1][2] == sign))
                                ||((this.field[0][0] == sign)&&(this.field[2][0] == sign)))
                            b = true;
                        break;
                    }
                    case 1: {
                        if (((this.field[0][0] == sign)&&(this.field[2][2] == sign))
                                ||((this.field[0][1] == sign)&&(this.field[2][1] == sign))
                                ||((this.field[1][0] == sign)&&(this.field[1][2] == sign))
                                ||((this.field[0][2] == sign)&&(this.field[2][0] == sign)))
                            b = true;
                        break;
                    }
                    case 2: {
                        if (((this.field[0][2] == sign)&&(this.field[2][2] == sign))
                                ||((this.field[1][0] == sign)&&(this.field[1][1] == sign)))
                        b = true;
                        break;

                    }
                    default: break;
                }
                break;
            }
            case 2: {
                switch (cell.column){
                    case 0: {
                        if (((this.field[2][1] == sign)&&(this.field[2][2] == sign))
                                ||((this.field[0][0] == sign)&&(this.field[1][0] == sign))
                                ||((this.field[1][1] == sign)&&(this.field[0][2] == sign)))
                            b = true;
                        break;
                    }
                    case 1: {
                        if (((this.field[0][1] == sign)&&(this.field[1][1] == sign))
                                ||((this.field[2][0] == sign)&&(this.field[2][2] == sign)))
                            b = true;
                        break;
                    }
                    case 2: {
                        if (((this.field[0][2] == sign)&&(this.field[1][2] == sign))
                                ||((this.field[2][0] == sign)&&(this.field[2][1] == sign))
                                ||((this.field[1][1] == sign)&&(this.field[0][0] == sign)))
                        b = true;
                        break;
                    }
                    default: break;

                }
                break;
            }
            default: break;
        }
        return b;
    }

}
