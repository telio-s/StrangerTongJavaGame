package sample;

public class GameController {
    private Monster m1, m2;
    private int turn; // -1 , 1
    private int round;


    public GameController(Monster m1, Monster m2){
        this.m1 = m1;
        this.m2 = m2;
        this.turn = 1;
        this.round = 2;

    }
    public void attackState(){
        if(this.turn == 1 ){
            m1.attack(m2);
        }
        else {
            m2.attack(m1);
        }
        this.changeTurn();

    }
    public void healState(String a){
        if (this.turn == 1){
            m1.heal(Integer.valueOf(a));
            m1.setSkill(1);
        }
        else {
            m2.heal(Integer.valueOf(a));
            m2.setSkill(1);
        }
        this.changeTurn();
    }
    public boolean isWin(){
        if (m1.check_hp() == 0 || m2.check_hp() == 0){
            return true;
        }
        return false;
    }
    public Monster getWinMonster(){
        if (m1.check_hp() >= m2.check_hp()){
            return m1;
        }
        return m2;
    }
    private void changeTurn(){
        this.turn *= -1;
        this.round+=1;
        if(m1.check_skill() == 1 && this.turn == 1) {
            this.round+=1;
            this.turn *= -1;
            m1.setSkill(0);
        } else if (m2.check_skill() == 1 && this.turn == -1) {
            this.round+=1;
            this.turn *= -1;
            m2.setSkill(0);
        } else if (m1.check_skill() == 1 && m2.check_skill() == 1) {
            this.round+=1;
            this.round+=1;
            this.turn *= -1;
            this.turn *= -1;
            m1.setSkill(0);
            m2.setSkill(0);
        }
    }

    public int getTurn() {
        if (this.turn == 1) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public int getRound() {
        int i = round / 2;
        return i;

    }

}
