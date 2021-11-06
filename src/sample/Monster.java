package sample;

import java.util.Scanner;

public class Monster {
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int hp_st;
    private int skill;
    private String imgPath;
    public Monster(String name, int hp, int atk, int def, String imgPath){
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.hp_st = hp;
        this.skill = 0;
        this.imgPath = imgPath;
    }

    public void attack(Monster other){
        if (atk > other.def) {
            if ((other.hp - (atk - other.def))<=0){
                other.hp = 0;
            }
            else if((other.hp - (atk - other.def))>0){
                other.hp = other.hp - (atk - other.def);
            }
        }
    }

    public void heal(int your_hp){
        if(your_hp+hp > hp_st){
            hp = hp_st;
        }
        else {
            hp += your_hp;
        }
    }

    public String getImgPath(){
        return imgPath;
    }

    public int check_hp(){
        return hp;
    }

    public int getHp_st(){
        return hp_st;
    }

    public int setSkill(int k)
    {
        return skill = k;
    }

    public int check_skill(){
        return skill;
    }

    @Override
    public String toString() {
        return name;
    }
}
