package oo;

/**
 * Created by ben on 15-12-2.
 */
public class NoWeapon extends Weapon{
    public NoWeapon() {
        super(null, 0);
    }

    @Override
    public String weaponAttach() {
        return "";
    }

    @Override
    public int getWeaponDamage() {
        return 0;
    }


    @Override
    public WeaponEffect getWeaponEffect() {
        return new WeaponEffect("",0,0);
    }

}
