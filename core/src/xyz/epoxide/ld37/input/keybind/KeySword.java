package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntitySword;

public class KeySword extends KeyBind {
    
    public KeySword(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onPressed() {
    	if (LD37.INSTANCE.entityPlayer.getAnimationType() != 2){
	    	LD37.INSTANCE.entityPlayer.setAnimationStage(0);
	    	LD37.INSTANCE.entityPlayer.setAnimationType(2);
	    	LD37.INSTANCE.entityPlayer.setAnimationFrames(9);
	    	LD37.INSTANCE.entityPlayer.setCycleSpeed(0.025f);
	    	LD37.INSTANCE.world.spawnEntity(new EntitySword(LD37.INSTANCE.world,LD37.INSTANCE.entityPlayer), LD37.INSTANCE.entityPlayer.getX(), LD37.INSTANCE.entityPlayer.getY());
    	}
    }
}
