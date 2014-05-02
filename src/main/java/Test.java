/**
 * Created by Sabishinzou on 02.05.14.
 */
public class Test {
    //чтобы сделать ход, нужно ввести через Энтр номер строки(сверху вниз считать) и столбца(слева направо)
    //интерфейс не доработан, потому не вводить повторяющиеся ходы(если клетка занята), вылезет исключение
    //ничего кроме цифр от 1 до 3 лучше не вводить
    public static void main(String[] args) throws Exception {
        GameField.Coordinate c;
        GameField gameField = new GameField();
        Human h = new Human('x');
        AiSecondPlayer ai = new AiSecondPlayer();
        GamePlay.playXhumanOai(gameField,h,ai);

    }

}
