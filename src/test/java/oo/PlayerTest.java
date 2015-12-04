package oo;

import com.sun.org.apache.xerces.internal.xs.StringList;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldCreatePlayerSuccessfully() {
        Player player = new Player("王二麻子", 100, 10);

        assertThat(player.getName(), is("王二麻子"));
        assertThat(player.getBlood(), is(100));
        assertThat(player.getDamage(), is(10));
    }

    @Test
    public void shouldCanAttackOtherPlayer() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        attacker.attack(victim);

        assertThat(victim.getBlood(), is(80 - attacker.getDamage()));
    }

    @Test
    public void shouldReturnWhoAttackVictimAndHowMuchVictimBleedAndHowMuchBloodLeft() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
    }




    @Test
    public void should_return_attacker_state_when_attacker_with_poison_attack_palyer() {
        Player attacker = new Player("张三", 100, 10);
        attacker.beEffected(new WeaponEffect("毒性伤害",1,0.0f));
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("张三受到2点毒性伤害, 张三剩余生命：98\n普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：60"));
    }

    @Test
    public void should_return_attacker_state_when_attacker_with_fire_attack_palyer() {
        Player attacker = new Player("张三", 100, 10);
        attacker.beEffected(new WeaponEffect("火焰伤害",1,0.0f));
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("张三受到2点火焰伤害, 张三剩余生命：98\n普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：60"));
    }

    @Test
    public void should_return_attacker_state_when_attacker_with_freeze_attack_palyer() {
        Player attacker = new Player("张三", 100, 10);
        attacker.beEffected(new WeaponEffect("冰冻伤害",3,0.0f));
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("张三被冰冻了, 无法攻击\n"));
        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：60"));
    }


    @Test
    public void should_return_attacker_state_when_attacker_with_stun_attack_palyer() {
        Player attacker = new Player("张三", 100, 10);
        attacker.beEffected(new WeaponEffect("击晕伤害",2,0.0f));
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("张三晕倒了，无法攻击, 眩晕还剩：1轮"));
        assertThat(attacker.attack(victim), is("张三晕倒了，无法攻击, 眩晕还剩：0轮"));
        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
    }





    @Test
    public void shouldAliveWhenBloodIs0() {
        Player survivor = new Player("王二麻子", 0, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldAliveWhenBloodGreatThan0() {
        Player survivor = new Player("王二麻子", 1, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldDeadWhenBloodLessThan0() {
        Player dead = new Player("王二麻子", -1, 0);

        assertThat(dead.isAlive(), is(false));
    }


    @Test
    public void should_return_normalPeople_when_get_palyer_profession(){
        Player player = new Player("张三", 100, 1);

        String profession=player.getProfession();

        assertEquals("普通人",profession);
    }

    @Test
    public void should_return_new_blood_when_set_palyer_blood(){
        Player player = new Player("张三", 50, 1);

        player.setBlood(100);
        int blood=player.getBlood();

        assertEquals(100,blood);
    }



    @Test
    public void should_return_effect_when_be_effected_by_weapon(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("毒性伤害",1,0.6f);

        player.beEffected(weaponEffect);
        WeaponEffect playWeaponEffect=player.getWeaponEffect();
        boolean isEqual=playWeaponEffect.getEffectName()=="毒性伤害"&&playWeaponEffect.getEffectRound()==1;

        assertTrue(isEqual);
    }

    @Test
    public void should_return_effect_when_be_effected_by_same_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffectFrist=new WeaponEffect("毒性伤害",1,0.6f);
        WeaponEffect weaponEffectSecond=new WeaponEffect("毒性伤害",2,0.6f);

        player.beEffected(weaponEffectFrist);
        player.beEffected(weaponEffectSecond);
        WeaponEffect playWeaponEffect=player.getWeaponEffect();
        boolean isEqual=playWeaponEffect.getEffectName()=="毒性伤害"&&playWeaponEffect.getEffectRound()==3;

        assertTrue(isEqual);
    }

    @Test
    public void should_return_effect_when_be_effected_by_different_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffectFrist=new WeaponEffect("毒性伤害",1,0.6f);
        WeaponEffect weaponEffectSecond=new WeaponEffect("火焰伤害",2,0.6f);

        player.beEffected(weaponEffectFrist);
        player.beEffected(weaponEffectSecond);
        WeaponEffect playWeaponEffect=player.getWeaponEffect();
        boolean isEqual=playWeaponEffect.getEffectName()=="毒性伤害"&&playWeaponEffect.getEffectRound()==1;

        assertTrue(isEqual);
    }

    @Test
    public void should_return_effect_when_be_effected_by_poison_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("毒性伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.beAttacked(1);

        Assert.assertEquals("张三受到了1点伤害，张三中毒了,张三剩余生命：49",result);
    }


    @Test
    public void should_return_effect_when_be_effected_by_fire_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("火焰伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.beAttacked(1);

        Assert.assertEquals("张三受到了1点伤害，张三被火焰吞噬了,张三剩余生命：49",result);
    }

    @Test
    public void should_return_effect_when_be_effected_by_freeze_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("冰冻伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.beAttacked(1);

        Assert.assertEquals("张三受到了1点伤害，张三被冰冻了,张三剩余生命：49",result);
    }

    @Test
    public void should_return_effect_when_be_effected_by_stun_weapon_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("击晕伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.beAttacked(1);

        Assert.assertEquals("张三受到了1点伤害，张三晕倒了,张三剩余生命：49",result);
    }

    @Test
    public void should_return_check_effect_state_when_be_poison_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("毒性伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.checkEffectState();

        Assert.assertEquals("张三受到2点毒性伤害, 张三剩余生命：48\n",result);
    }

    @Test
    public void should_return_check_effect_state_when_be_fire_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("火焰伤害",1,0.6f);
        player.beEffected(weaponEffect);

        String result=player.checkEffectState();

        Assert.assertEquals("张三受到2点火焰伤害, 张三剩余生命：48\n",result);
    }

    @Test
    public void should_return_check_effect_state_when_be_freeze_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("冰冻伤害",3,0.6f);
        player.beEffected(weaponEffect);

        String result=player.checkEffectState();

        Assert.assertEquals("张三被冰冻了, 无法攻击\n",result);

        result=player.checkEffectState();

        Assert.assertEquals("",result);

        result=player.checkEffectState();

        Assert.assertEquals("",result);
    }

    @Test
    public void should_return_check_effect_state_when_be_stun_effect(){
        Player player = new Player("张三", 50, 1);
        WeaponEffect weaponEffect=new WeaponEffect("击晕伤害",3,0.6f);
        player.beEffected(weaponEffect);

        String result=player.checkEffectState();

        Assert.assertEquals("张三晕倒了，无法攻击, 眩晕还剩：2轮",result);

        result=player.checkEffectState();

        Assert.assertEquals("张三晕倒了，无法攻击, 眩晕还剩：1轮",result);
    }

}