/**
 * Created by Sabishinzou on 27.04.14.
 */
public interface iStrategies {
//его можно расшиирить на все варианты игры, но будет здесь только для ноликов
    /*
    Если крестики сделали первый ход в центр, до конца игры ходить в любой угол, а если это невозможно — в любую клетку.
    Если крестики сделали первый ход в угол, ответить ходом в центр. Следующим ходом занять угол, противоположный первому ходу крестиков, а если это невозможно — пойти на сторону.
     */

    void strategyHumFirstPasteCenter(GameField gameField) throws Exception;
    void strategyHumFirstPasteCorner(GameField gameField) throws Exception;

    //void strategyHumFirstPasteOnside(GameField gameField);
    //Если крестики сделали первый ход на сторону, ответить ходом в центр и ....
    // эту последнюю часть стратегии довольно долго описывать и уже двух предыдущих достаточно чтобы компьютер никогда не проиграл
    //так что оставлю вот так, не думаю что это такой уж большое упущение
}