package oo;

/**
 * Created by ben on 15-12-4.
 */
public class Effect {
    private String effectName;
    private int effectRound;
    private int effectDamage;

    public Effect(String effectName, int effectRound, int effectDamage) {
        this.effectName = effectName;
        this.effectRound = effectRound;
        this.effectDamage = effectDamage;
    }


    public String getEffectName() {
        return effectName;
    }

    public int getEffectRound() {
        return effectRound;
    }

    public int getEffectDamage() {
        return effectDamage;
    }

    public void addEffectRound(int effectRound) {
        int round=getEffectRound()+effectRound;
        this.effectRound=round>0?round:0;
    }
}
