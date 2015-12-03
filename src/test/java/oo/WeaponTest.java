package oo;

import oo.Weapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by ben on 15-12-2.
 */
public class WeaponTest {
    @Test
    public void should_return_weaponName_when_use_weapon() throws Exception {
        Weapon weapon=new Weapon("木棒",2);

        String result=weapon.weaponAttach();

        assertEquals("用木棒", result);
    }

    @Test
    public void should_return_weapon_damage_when_use_weapon() throws Exception {
        Weapon weapon=new Weapon("木棒",2);

        int damage=weapon.getWeaponDamage();

        assertEquals(2,damage);
    }


    @Test
    public void should_return_weapon_effect_when_use_weapon_with_effect() throws Exception {
        WeaponEffect weaponEffect=new WeaponEffect("毒性伤害",1,0.6f);
        Weapon weapon=new Weapon("木棒",2,weaponEffect);

        WeaponEffect attackWeaponEffect=weapon.getWeaponEffect();

        assertTrue(
                (attackWeaponEffect.getEffectName()==""&&attackWeaponEffect.getEffectRound()==0)||
                        (attackWeaponEffect.getEffectName()=="毒性伤害"&&attackWeaponEffect.getEffectRound()==1));
    }

    @Test
    public void should_return_weaponLength_when_use_weapon() throws Exception {
        WeaponEffect weaponEffect=new WeaponEffect("毒性伤害",1,0.6f);
        Weapon weapon=new Weapon("木棒",2,"中",weaponEffect);

        String result=weapon.getWeaponLength();

        assertEquals("中", result);
    }



}