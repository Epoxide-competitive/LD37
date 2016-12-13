package xyz.epoxide.ld37.input.keybind;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.utils.Direction;

public class KeyMovement extends KeyBind {
	public Direction direction = Direction.STILL;
	
    public KeyMovement(int keycode, Direction direction) {
        super(keycode);
        this.direction = direction;
    }
    
    @Override
    public void onUpdate (float delta) {
        
        switch (this.direction.x) {
            case -1: {
            	if (LD37.INSTANCE.entityPlayer.getMotionX() > -8.0f){
	                LD37.INSTANCE.entityPlayer.addMotionX(-80.0f*delta);
	                break;
            	}
            }
            case 1: {
            	if (LD37.INSTANCE.entityPlayer.getMotionX() < 8.0f){
	                LD37.INSTANCE.entityPlayer.addMotionX(80.0f*delta);
	                break;
            	}
            }
        }
    }
}
