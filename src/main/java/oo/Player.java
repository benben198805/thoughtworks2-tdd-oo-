package oo;

import static java.lang.String.format;

public class Player {
    private String name;
    private int blood;
    private int damage;
    private Effect effect;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.effect=new Effect("",0,0);
    }

    public String getName() {
        return name;
    }

    public int getBlood() {
        return blood;
    }
    public void setBlood(int blood) {
        this.blood=blood;
    }

    public int getDamage() {
        return damage;
    }


    public String getProfession(){
        return "普通人";
    }

    public String attack(Player victim) {
        String result="";

        boolean isStun=effect.getEffectName()=="击晕伤害";
        boolean isFreeze=effect.getEffectName()=="冰冻伤害"&&effect.getEffectRound()==3;

        result=checkEffectState();

        if(!isStun&&!isFreeze)
        {
            result+=format("%s%s攻击了%s%s，%s",this.getProfession(), name,victim.getProfession(), victim.name, victim.beAttacked(damage));
        }
        return result;
    }

    public String beAttacked(int damage) {
        String effectResult="";

        switch (effect.getEffectName()){
            case "毒性伤害":
                effectResult=format("%s中毒了,", name);
                break;
            case "火焰伤害":
                effectResult=format("%s被火焰吞噬了,", name);
                break;
            case "冰冻伤害":
                effectResult=format("%s被冰冻了,", name);
                break;
            case "击晕伤害":
                effectResult=format("%s晕倒了,", name);
                break;
            case "全力一击":
                damage=damage*3;
                break;
            default:
                break;
        }

        blood -= damage;

        return format("%s受到了%d点伤害，%s%s剩余生命：%d",
                name, damage,effectResult, name, blood);
    }

    public boolean isAlive() {
        return blood >= 0;
    }

    public Effect getEffect(){
        return effect;
    }

    public void beEffected(WeaponEffect weaponEffect)
    {
        if(this.effect.getEffectName()=="")
        {
            this.effect=new Effect(weaponEffect.getEffectName(),weaponEffect.getEffectRound(),2);
        }
        else if(this.effect.getEffectName()==weaponEffect.getEffectName())
        {
            this.effect.addEffectRound(weaponEffect.getEffectRound());
        }
    }


    public String checkEffectState(){
        String effectResult="";
        switch (effect.getEffectName()){
            case "毒性伤害":
            case "火焰伤害":
                if(blood-2<=0)
                {
                    blood=0;
                }
                else
                {
                    blood-=2;
                }
                effectResult=format("%s受到%d点%s, %s剩余生命：%d\n",name,2,effect.getEffectName(),name,blood);
                effect.addEffectRound(-1);
                break;
            case "冰冻伤害":
                if(effect.getEffectRound()==3)
                {
                    effectResult=format("%s被冰冻了, 无法攻击\n",name);
                }
                effect.addEffectRound(-1);
                break;
            case "击晕伤害":
                effect.addEffectRound(-1);
                effectResult=format("%s晕倒了，无法攻击, 眩晕还剩：%d轮", name,effect.getEffectRound());
                break;
            default:
                break;
        }

        if(effect.getEffectRound()==0){
            effect=new Effect("",0,0);
        }
        return effectResult;
    }

}
