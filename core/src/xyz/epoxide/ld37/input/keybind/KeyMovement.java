package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Input;

import xyz.epoxide.ld37.LD37;

public class KeyMovement extends KeyBind {
    public KeyMovement(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onUpdate (float delta) {
        
        switch (this.getKeyCode()) {
            case Input.Keys.A: {
                LD37.INSTANCE.entityPlayer.addMotionX(-2 * delta);
                break;
            }
            case Input.Keys.D: {
                LD37.INSTANCE.entityPlayer.addMotionX(2 * delta);
                break;
            }
        }
    }
}
