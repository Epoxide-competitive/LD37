package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.ld37.LD37;

public class KeyJump extends KeyBind {
    
    public KeyJump(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onUpdate(float delta) {
    	if (LD37.INSTANCE.entityPlayer.onGround()){
	        LD37.INSTANCE.entityPlayer.setOnGround(false);
	    	LD37.INSTANCE.entityPlayer.setMotionY(20f);
    	}
    }
}
