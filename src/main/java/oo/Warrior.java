package oo;

import static java.lang.String.format;

/**
 * Created by ben on 15-12-2.
 */
public class Warrior extends Player{
    private String profession;
    private int armorDefense;
    private Weapon weapon;


    public Warrior(String name, int blood, int damage,String profession,int armorDefense,Weapon weapon) throws Exception {
        super(name, blood, damage);
        this.profession=profession;
        this.armorDefense=armorDefense;

        boolean isAssassinWeapon=profession=="刺客"&&(weapon.getWeaponLength()=="短"||weapon.getWeaponLength()=="中"||weapon.getWeaponLength()==null);
        boolean isFighter=profession=="战士"&&(weapon.getWeaponLength()=="中"||weapon.getWeaponLength()==null);
        boolean isKnightWeapon=profession=="骑士"&&(weapon.getWeaponLength()=="长"||weapon.getWeaponLength()=="中"||weapon.getWeaponLength()==null);

        if(isAssassinWeapon||isFighter||isKnightWeapon)
        {
            this.weapon=weapon;
        }
        else
        {
            throw new Exception("武器不匹配");
        }
    }

    public int getDamage(){
        return super.getDamage()+weapon.getWeaponDamage();
    }

    public String getProfession(){
        return profession;
    }

    public int getArmorDefense() {
        return armorDefense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setProfession(String profession){
        this.profession= profession;
    }


    public String attack(Player victim){
        boolean isStun=this.getEffect().getEffectName()=="击晕伤害";
        boolean isFreeze=this.getEffect().getEffectName()=="冰冻伤害"&&this.getEffect().getEffectRound()==3;

        String result=checkEffectState();



        WeaponEffect attackEffect=new WeaponEffect("",0,0.0f);

        boolean isAssassinWeapon=profession=="刺客"&&weapon.getWeaponLength()=="短";
        boolean isFighter=profession=="战士"&&weapon.getWeaponLength()=="中";
        boolean isKnightWeapon=profession=="骑士"&&weapon.getWeaponLength()=="长";

        if(isAssassinWeapon||isFighter||isKnightWeapon) {
            attackEffect=weapon.getWeaponEffect();
        }

        victim.beEffected(attackEffect);

        String tripleDamageResult="";
        if(attackEffect.getEffectName()=="全力一击"){
            tripleDamageResult=format("%s发动了全力一击,",this.getName());
        }

        if(!isStun&&!isFreeze)
        {
            result+=format("%s%s%s攻击了%s%s，%s%s",
                    this.getProfession(),
                    this.getName(),
                    weapon.weaponAttach(),
                    victim.getProfession(),
                    victim.getName(),
                    tripleDamageResult,
                    victim.beAttacked(getDamage()));
        }

        return result;
    }

    public String beAttacked(int damage) {


        int hurt=damage-armorDefense>0?damage-armorDefense:0;

        String effectResult="";

        switch (this.getEffect().getEffectName()){
            case "毒性伤害":
                effectResult=format("%s中毒了,", this.getName());
                break;
            case "火焰伤害":
                effectResult=format("%s被火焰吞噬了,", this.getName());
                break;
            case "冰冻伤害":
                effectResult=format("%s被冰冻了,", this.getName());
                break;
            case "击晕伤害":
                effectResult=format("%s晕倒了,", this.getName());
                break;
            case "全力一击":
                hurt=hurt*3;
                break;
            default:
                break;
        }

        this.setBlood(this.getBlood() - hurt);

        return format("%s受到了%d点伤害，%s%s剩余生命：%d",
                this.getName(), hurt,effectResult, this.getName(), this.getBlood());
    }




}
