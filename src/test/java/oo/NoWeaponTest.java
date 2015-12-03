package oo;

import oo.NoWeapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ben on 15-12-2.
 */
public class NoWeaponTest {
    private NoWeapon noWeapon;
    @Before
    public void setUp() throws Exception {
        noWeapon=new NoWeapon();
    }


    @Test
    public void should_return_null_when_player_without_weapon() throws Exception {
        String result=noWeapon.weaponAttach();

        Assert.assertEquals("",result);
    }

    @Test
    public void should_return_0_weapon_damage_when_player_without_weapon() throws Exception {
        int damage=noWeapon.getWeaponDamage();

        Assert.assertEquals(0,damage);

    }

    @Test
    public void should_return_null_weapon_effect_when_player_without_weapon() throws Exception {
        WeaponEffect weaponEffect=noWeapon.getWeaponEffect();

        assertTrue(weaponEffect.getEffectName()==""&&weaponEffect.getEffectRound()==0&&weaponEffect.getRandomActivation()==0);

    }
}