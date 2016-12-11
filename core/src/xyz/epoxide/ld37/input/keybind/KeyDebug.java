package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntitySprout;
import xyz.epoxide.ld37.entity.EntitySword;

public class KeyDebug extends KeyBind {
    
    public KeyDebug(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onPressed() {
    	LD37.INSTANCE.world.spawnEntity(new EntitySprout(LD37.INSTANCE.world), LD37.INSTANCE.entityPlayer.getX(), LD37.INSTANCE.entityPlayer.getY());
    }
}
