package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;

import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;

public class KeyBind extends Registerable<KeyBind> {
    
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<KeyBind>();
    
    private int keycode;
    
    public KeyBind(int keycode) {
        
        this.keycode = keycode;
    }
    
    public static KeyBind registerKeyBind (String id, KeyBind bind) {
        
        bind.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(bind);
        return bind;
    }
    
    public boolean isPressed () {
        
        return Gdx.input.isKeyPressed(this.getKeyCode());
    }
    
    public void onPressed () {
        
    }
    
    public void onReleased () {
        
    }
    
    public void onUpdate () {
        
    }
    
    public int getKeyCode () {
        
        return keycode;
    }
    
    public void setKeyCode (int key) {
        
        this.keycode = key;
    }
}