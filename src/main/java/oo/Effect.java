package oo;

import static java.lang.String.format;

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

    public int getDoubleEffectDamage(int hurt){
        return effectName=="全力一击"&&hurt>0?hurt*2:0;
    }

    public String getEffectResult(String victimName){
        String effectState="";
        boolean isReturnEffect=true;
        switch (effectName){
            case "毒性伤害":
                effectState="中毒";
                break;
            case "火焰伤害":
                effectState="被火焰吞噬";
                break;
            case "冰冻伤害":
                effectState="被冰冻";
                break;
            case "击晕伤害":
                effectState="晕倒";
                break;
            default:
                isReturnEffect=false;
                break;
        }
        String effectResult=isReturnEffect?format("%s%s了,", victimName,effectState):"";
        return effectResult;
    }
}
