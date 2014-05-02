/**
 * Created by Sabishinzou on 27.04.14.
 */
public abstract class Player {
    public char sign;


    public Player(char sign){
        this.sign = sign;
    }
    public void move(GameField gf, int i, int j) throws Exception {
        gf.paste(this.sign, i, j);
    }
}