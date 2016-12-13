package xyz.epoxide.ld37.input.keybind;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntityBullet;
import xyz.epoxide.ld37.entity.EntityGun;

public class KeyGun extends KeyBind {
    
    public KeyGun(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onPressed() {
    	if (LD37.INSTANCE.entityPlayer.getAnimationType() != 2){
	    	LD37.INSTANCE.entityPlayer.setAnimationStage(0);
	    	LD37.INSTANCE.entityPlayer.setAnimationType(3);
	    	LD37.INSTANCE.entityPlayer.setAnimationFrames(5);
	    	LD37.INSTANCE.entityPlayer.setCycleSpeed(0.05f);
	    	float sign = LD37.INSTANCE.entityPlayer.getDirection().x;
	    	if (sign == 0){
	    		sign = -1;
	    	}
	    	LD37.INSTANCE.world.spawnEntity(new EntityGun(LD37.INSTANCE.world,LD37.INSTANCE.entityPlayer), LD37.INSTANCE.entityPlayer.getX(), LD37.INSTANCE.entityPlayer.getY());
	    	LD37.INSTANCE.world.spawnEntity(new EntityBullet(LD37.INSTANCE.world,sign*30.0f), LD37.INSTANCE.entityPlayer.getX()+sign*1.0f, LD37.INSTANCE.entityPlayer.getY());
	    }
    }
}
