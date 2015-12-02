package oo;

import static java.lang.String.format;

public class Player {
    private String name;
    private int blood;
    private int damage;
    private WeaponEffect weaponEffect;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.weaponEffect=new WeaponEffect("",0,0);
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

        boolean isStun=weaponEffect.getEffectName()=="击晕伤害";
        boolean isFreeze=weaponEffect.getEffectName()=="冰冻伤害"&&weaponEffect.getEffectRound()==3;

        result=checkEffectState();

        if(!isStun&&!isFreeze)
        {
            result+=format("%s%s攻击了%s%s，%s",this.getProfession(), name,victim.getProfession(), victim.name, victim.beAttacked(damage));
        }
        return result;
    }

    public String beAttacked(int damage) {
        String effectResult="";

        switch (weaponEffect.getEffectName()){
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

    public WeaponEffect getWeaponEffect(){
        return weaponEffect;
    }

    public void beEffected(WeaponEffect weaponEffect)
    {
        if(this.weaponEffect.getEffectName().length()==0)
        {
            this.weaponEffect=weaponEffect;
        }
        else if(this.weaponEffect.getEffectName()==weaponEffect.getEffectName())
        {
            this.weaponEffect.addEffectRound(weaponEffect.getEffectRound());
        }
    }


    public String checkEffectState(){
        String effectResult="";
        switch (weaponEffect.getEffectName()){
            case "毒性伤害":
                blood-=2;
                effectResult=format("%s受到%d点毒性伤害, %s剩余生命：%d\n",name,2,name,blood);
                weaponEffect.addEffectRound(-1);
                break;
            case "火焰伤害":
                blood-=2;
                effectResult=format("%s受到%d点火焰伤害, %s剩余生命：%d\n",name,2,name,blood);
                weaponEffect.addEffectRound(-1);
                break;
            case "冰冻伤害":
                if(weaponEffect.getEffectRound()==3)
                {
                    effectResult=format("%s被冰冻了, 无法攻击\n",name);
                }
                weaponEffect.addEffectRound(-1);
                break;
            case "击晕伤害":
                weaponEffect.addEffectRound(-1);
                effectResult=format("%s晕倒了，无法攻击, 眩晕还剩：%d轮\n", name,weaponEffect.getEffectRound());
                break;
            default:
                break;
        }

        if(weaponEffect.getEffectRound()<=0){
            weaponEffect=new WeaponEffect("",0,0.0f);
        }
        return effectResult;
    }

}
