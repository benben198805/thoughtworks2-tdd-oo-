package oo;

/**
 * Created by ben on 15-12-2.
 */
public class Weapon {
    public String weaponName;
    public int weaponDamage;
    public String weaponLength;
    public WeaponEffect weaponEffect;

    public Weapon(String weaponName, int weaponDamage) {
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.weaponEffect = new WeaponEffect("",0,0);
    }

    public Weapon(String weaponName, int weaponDamage, WeaponEffect weaponEffect) {
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.weaponEffect = weaponEffect;
        this.weaponLength="中";

    }

    public Weapon(String weaponName, int weaponDamage,String weaponLength, WeaponEffect weaponEffect) {
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.weaponEffect = weaponEffect;
        this.weaponLength=weaponLength;
    }


    public String weaponAttach() {
        return "用"+weaponName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public WeaponEffect getWeaponEffect(){
        double probability=Math.random()*weaponEffect.getRandomActivation();
        return probability>1?weaponEffect:new WeaponEffect("",0,0);
    }

    public String getWeaponLength() {
        return weaponLength;
    }
}
