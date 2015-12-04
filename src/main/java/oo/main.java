package oo;

/**
 * Created by ben on 15-12-2.
 */
public class main {
    public static void main(String[] args){
        Game game=new Game(new ConsolePrinter());
        Warrior xiaoXin;
        Warrior daXiong;
        try{
            xiaoXin=new Warrior("小新",100,2,"骑士",1,new Weapon("动感光波",20,"长",new WeaponEffect("毒性伤害",3,1.8f)));
            daXiong=new Warrior("大熊",100,1,"战士",1,new Weapon("拳头",10,"中",new WeaponEffect("击晕伤害",3,1.3f)));

            game.fight(xiaoXin,daXiong);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
