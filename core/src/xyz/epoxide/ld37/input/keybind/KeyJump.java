package xyz.epoxide.ld37.input.keybind;

import xyz.epoxide.ld37.LD37;

public class KeyJump extends KeyBind {
    
    public KeyJump(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onPressed() {
    	if (LD37.INSTANCE.entityPlayer.onGround() || LD37.INSTANCE.entityPlayer.maxJumps > 1 && LD37.INSTANCE.entityPlayer.jumps > 0){
    		LD37.INSTANCE.entityPlayer.jumps --;
	        LD37.INSTANCE.entityPlayer.setOnGround(false);
	    	LD37.INSTANCE.entityPlayer.setMotionY(20f);
    	}
    }
}
