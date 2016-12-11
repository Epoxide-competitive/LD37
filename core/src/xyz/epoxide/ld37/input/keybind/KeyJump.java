package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.ld37.LD37;

public class KeyJump extends KeyBind {
    
    public KeyJump() {
        super(Input.Keys.SPACE);
    }
    
    @Override
    public void onPressed() {
        LD37.INSTANCE.entityPlayer.addMotionY(20.0f*Gdx.graphics.getDeltaTime());
    }
}
