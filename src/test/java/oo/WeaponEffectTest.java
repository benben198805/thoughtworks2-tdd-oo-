package oo;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ben on 15-12-2.
 */
public class WeaponEffectTest {
    private WeaponEffect effect;

    @Before
    public void setUp()
    {
        effect=new WeaponEffect("毒性伤害",1,0.6f);
    }

    @Test
    public void should_return_effect_name_when_get_effect_name()
    {
        String effectName=effect.getEffectName();
        Assert.assertEquals("毒性伤害",effectName);
    }

    @Test
    public void should_return_effect_round_when_get_effect_name()
    {
        int effectRound=effect.getEffectRound();
        Assert.assertEquals(1,effectRound);
    }

    @Test
    public void should_return_effect_random_activeation_when_get_effect_name()
    {
        float effectRandomActivation=effect.getRandomActivation();
        assertThat(effectRandomActivation, is(0.6f));
    }


    @Test
    public void should_return_effect_round_when_add_effect_round()
    {
        effect.addEffectRound(2);
        int effectRound=effect.getEffectRound();
        assertThat(effectRound, is(3));
    }


    @Test
    public void should_return_positive_effect_round_when_add_negative_effect_round()
    {
        effect.addEffectRound(-9);
        int effectRound=effect.getEffectRound();
        assertThat(effectRound, is(0));
    }

}