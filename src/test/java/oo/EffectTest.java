package oo;

import junit.framework.Assert;
import oo.Effect;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ben on 15-12-4.
 */
public class EffectTest {
    private Effect effect;
    @Before
    public void setUp() {
        effect=new Effect("火焰伤害",1,1);
    }

    @Test
    public void should_return_effect_when_create_effect_successfully() throws Exception {
        String effectName=effect.getEffectName();
        int effectRound=effect.getEffectRound();
        int effectDamage=effect.getEffectDamage();

        Assert.assertEquals("火焰伤害",effectName);
        Assert.assertEquals(1,effectRound);
        Assert.assertEquals(1,effectDamage);

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