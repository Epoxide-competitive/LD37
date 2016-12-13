package xyz.epoxide.ld37.input.keybind;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntityWisp;

public class KeyDebug extends KeyBind {
    
    public KeyDebug(int keycode) {
        super(keycode);
    }
    
    @Override
    public void onPressed() {
    	LD37.INSTANCE.world.spawnEntity(new EntityWisp(LD37.INSTANCE.world), LD37.INSTANCE.entityPlayer.getX(), LD37.INSTANCE.entityPlayer.getY());
    }
}
