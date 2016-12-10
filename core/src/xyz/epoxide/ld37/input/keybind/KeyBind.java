package xyz.epoxide.ld37.input.keybind;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;

public class KeyBind extends Registerable<KeyBind> {
    
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<KeyBind>();
    
    public static KeyBind A = registerKeyBind("a", new KeyBind(Input.Keys.H));
    public static KeyBind B = registerKeyBind("b", new KeyBind(Input.Keys.G));
    
    private int keycode;
    
    public KeyBind(int key) {
        
    }
    
    public static KeyBind registerKeyBind(String id, KeyBind bind) {
        
        bind.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(bind);
        return bind;
    }
    
    public boolean isPressed() {
        
        return Gdx.input.isButtonPressed(this.getKeyCode());
    }
    
    public void onPressed() {
        
        Gdx.app.log("Info", this.getIdentifier() + " was pressed");
    }
    
    public void onReleased() {
        
        Gdx.app.log("Info", this.getIdentifier() + " was released");
    }
    
    public void onUpdate() {
        
        if (this == A)
            Gdx.app.log("Info", this.getIdentifier() + " is repeating");
    }

    public int getKeyCode () {
        
        return keycode;
    }

    public void setKeyCode (int key) {
        
        this.keycode = key;
    }
}