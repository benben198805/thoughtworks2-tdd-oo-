package oo;

import oo.Warrior;
import oo.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ben on 15-12-2.
 */
public class WarriorTest {
    @Test
    public void should_return_warrior_when_create_warrior_with_weapon() throws Exception {
        Weapon weapon=new Weapon("木棒",2);
        Warrior warrior=new Warrior("张三",100,1,"战士",1,weapon);

        Assert.assertEquals(warrior.getProfession(), "战士");
        Assert.assertEquals(warrior.getWeapon(),weapon);
        Assert.assertEquals(warrior.getArmorDefense(), 1);
    }


    @Test
    public void should_return_weapon_and_player_damage_when_use_weapon() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",0,new Weapon("木棒",2));

        int damage=warrior.getDamage();

        Assert.assertEquals(3,damage);
    }

    @Test
    public void should_return_weapon_and_player_damage_when_without_weapon() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",0,new NoWeapon());

        int damage=warrior.getDamage();

        Assert.assertEquals(1,damage);
    }

    @Test
    public void should_return_profession_when_player_was_warrior() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",0,new NoWeapon());

        String profession=warrior.getProfession();

        Assert.assertEquals("战士",profession);
    }

    @Test
    public void should_return_profession_when_player_was_set_knight() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",0,new NoWeapon());

        warrior.setProfession("骑士");
        String profession=warrior.getProfession();

        Assert.assertEquals("骑士",profession);
    }
    @Test
    public void should_return_how_much_damage_be_attacked_without_armor_when_be_attacked() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",0,new Weapon("木棒",2));
        Player victim=new Player("李四",100,1);

        String result=warrior.beAttacked(victim.getDamage());

        Assert.assertEquals("张三受到了1点伤害，张三剩余生命：99",result);
    }

    @Test
    public void should_return_how_much_damage_be_attacked_with_armor_when_be_attacked() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",2,new Weapon("木棒",2));
        Player victim=new Player("李四",100,1);

        String result=warrior.beAttacked(victim.getDamage());

        Assert.assertEquals("张三受到了0点伤害，张三剩余生命：100",result);
    }

    @Test
    public void should_return_how_much_damage_be_attacked_with_armorDefense_more_than_damage_when_be_attacked() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",1,new Weapon("木棒",2));
        Player victim=new Player("李四",100,1);

        String result=warrior.beAttacked(victim.getDamage());

        Assert.assertEquals("张三受到了0点伤害，张三剩余生命：100",result);
    }

    @Test
    public void should_return_warrior_without_weapon_attack_normal() throws Exception {
        Warrior warrior=new Warrior("张三",100,2,"战士",1,new NoWeapon());
        Player victim=new Player("李四",100,1);

        String result=warrior.attack(victim);

        Assert.assertEquals("战士张三攻击了普通人李四，李四受到了2点伤害，李四剩余生命：98",result);
    }

    @Test
    public void should_return_warrior_with_weapon_attack_normal() throws Exception {
        Warrior warrior=new Warrior("张三",100,2,"战士",1,new Weapon("木棒",1));
        Player victim=new Player("李四",100,1);

        String result=warrior.attack(victim);

        Assert.assertEquals("战士张三用木棒攻击了普通人李四，李四受到了3点伤害，李四剩余生命：97",result);
    }

    @Test
    public void should_return_warrior_with_weapon_attack_warrior() throws Exception {
        Warrior warrior=new Warrior("张三",100,4,"战士",1,new Weapon("钉耙",3));
        Warrior victim=new Warrior("李四",100,2,"战士",1,new Weapon("木棒",1));

        String result=warrior.attack(victim);

        Assert.assertEquals("战士张三用钉耙攻击了战士李四，李四受到了6点伤害，李四剩余生命：94",result);
    }

    @Test
    public void should_return_normal_hurt_by_triple_damage() throws Exception {
        Warrior warrior=new Warrior("张三",100,1,"战士",1,new Weapon("钉耙",2,new WeaponEffect("全力一击",1,10.0f)));
        Player victim=new Player("李四",100,2);

        String result=warrior.attack(victim);

        Assert.assertEquals("战士张三用钉耙攻击了普通人李四，张三发动了全力一击,李四受到了9点伤害，李四剩余生命：91",result);
    }

    @Test
    public void should_return_knigth_when_create_knight_successfully() throws Exception {
        Weapon weapon=new Weapon("长矛",10,"长",new WeaponEffect("",0,0.0f));
        Warrior knight=new Warrior("王五",100,1,"骑士",0,weapon);

        String knightProfession=knight.getProfession();
        String knightWeaponLength=knight.getWeapon().getWeaponLength();
        Assert.assertEquals(knightProfession,"骑士");
        Assert.assertEquals(knightWeaponLength,"长");
    }

    @Test
    public void should_return_fighter_when_create_knight_fail() throws Exception {
        Weapon weapon=new Weapon("长矛",10,"长",new WeaponEffect("",0,0.0f));
        Warrior knight;
        String exception="";
        try {
            knight=new Warrior("王五",100,1,"战士",0,weapon);
        }
        catch (Exception e)
        {
            exception=e.toString();
        }

        Assert.assertEquals(exception,"java.lang.Exception: 武器不匹配");
    }


    @Test
    public void should_return_attack_with_effect_when_warrior_has_match_weapon() throws Exception {
        Weapon weapon=new Weapon("长矛",10,"长",new WeaponEffect("全力一击",1,100.0f));
        Warrior knight=new Warrior("王五",100,2,"骑士",0,weapon);
        Player victim=new Player("张三",100,1);

        String result=knight.attack(victim);

        Assert.assertEquals("骑士王五用长矛攻击了普通人张三，王五发动了全力一击,张三受到了36点伤害，张三剩余生命：64",result);
    }

    @Test
    public void should_return_attack_with_effect_when_warrior_has_not_match_weapon() throws Exception {
        Weapon weapon=new Weapon("长矛",10,"中",new WeaponEffect("全力一击",1,10.0f));
        Warrior knight=new Warrior("王五",100,2,"骑士",0,weapon);
        Player victim=new Player("张三",100,1);

        String result=knight.attack(victim);

        Assert.assertEquals("骑士王五用长矛攻击了普通人张三，张三受到了12点伤害，张三剩余生命：88",result);
    }







}