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
    private Effect effectPoison;
    private Effect effectFire;
    private Effect effectFrozen;
    private Effect effectStun;
    private Effect effectTriple;
    @Before
    public void setUp() {
        effect=new Effect("火焰伤害",1,1);
        effectPoison=new Effect("毒性伤害",3,2);
        effectFire=new Effect("火焰伤害",3,2);
        effectFrozen=new Effect("冰冻伤害",3,0);
        effectStun=new Effect("击晕伤害",3,0);
        effectTriple=new Effect("全力一击",1,0);
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



    @Test
    public void should_return_double_hurt_when_use_get_triple_with_powerAttack()
    {
        int doubleDamage=effectTriple.getDoubleEffectDamage(2);

        assertThat(doubleDamage,is(4));
    }

    @Test
    public void should_return_zero_hurt_when_use_get_triple_without_powerAttack()
    {
        int doubleDamage=effect.getDoubleEffectDamage(2);

        assertThat(doubleDamage,is(0));
    }

    @Test
    public void should_return_zero_hurt_when_give_negative_use_get_triple_with_powerAttack()
    {
        int doubleDamage=effect.getDoubleEffectDamage(-2);

        assertThat(doubleDamage,is(0));
    }

    @Test
    public void should_return_string_when_effect_is_poison()
    {
        String result=effectPoison.getEffectResult("李四");

        assertThat(result,is("李四中毒了,"));
    }

    @Test
    public void should_return_string_when_effect_is_fire()
    {
        String result=effectFire.getEffectResult("李四");

        assertThat(result,is("李四被火焰吞噬了,"));
    }

    @Test
    public void should_return_string_when_effect_is_frozen()
    {
        String result=effectFrozen.getEffectResult("李四");

        assertThat(result,is("李四被冰冻了,"));
    }

    @Test
    public void should_return_string_when_effect_is_stun()
    {
        String result=effectStun.getEffectResult("李四");

        assertThat(result,is("李四晕倒了,"));
    }

    @Test
    public void should_return_string_when_effect_is_other()
    {
        String result=effectTriple.getEffectResult("李四");

        assertThat(result,is(""));
    }
}