package oo;

/**
 * Created by ben on 15-12-2.
 */
public class WeaponEffect {
    private String effectName;
    private int effectRound;
    private float randomActivation;

    public WeaponEffect(String effectName, int effectRound, float randomActivation) {
        this.effectName = effectName;
        this.effectRound = effectRound;
        this.randomActivation = randomActivation;
    }

    public String getEffectName() {
        return effectName;
    }

    public int getEffectRound() {
        return effectRound;
    }

    public void setEffectRound(int round) {
        this.effectRound=round;
    }

    public float getRandomActivation() {
        return randomActivation;
    }
}
