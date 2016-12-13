package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.utils.Direction;

public class KeyBind extends Registerable<KeyBind> {
    
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<KeyBind>();
    
    public static final KeyBind W = registerKeyBind("w", new KeyMovement(Input.Keys.W, Direction.UP));
    public static final KeyBind A = registerKeyBind("a", new KeyMovement(Input.Keys.A, Direction.LEFT));
    public static final KeyBind S = registerKeyBind("s", new KeyMovement(Input.Keys.S, Direction.DOWN));
    public static final KeyBind D = registerKeyBind("d", new KeyMovement(Input.Keys.D, Direction.RIGHT));
    public static final KeyBind UP = registerKeyBind("up", new KeyMovement(Input.Keys.UP, Direction.UP));
    public static final KeyBind LEFT = registerKeyBind("left", new KeyMovement(Input.Keys.LEFT, Direction.LEFT));
    public static final KeyBind DOWN = registerKeyBind("down", new KeyMovement(Input.Keys.DOWN, Direction.DOWN));
    public static final KeyBind RIGHT = registerKeyBind("right", new KeyMovement(Input.Keys.RIGHT, Direction.RIGHT));
    
    public static final KeyBind SPACE = registerKeyBind("space", new KeyJump(Input.Keys.SPACE));
    public static final KeyBind Z = registerKeyBind("z", new KeyJump(Input.Keys.Z));
    public static final KeyBind J = registerKeyBind("j", new KeyJump(Input.Keys.J));
    public static final KeyBind X = registerKeyBind("x", new KeySword(Input.Keys.X));
    public static final KeyBind K = registerKeyBind("k", new KeySword(Input.Keys.K));
    
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
    
    public void onUpdate (float delta) {
        
    }
    
    public int getKeyCode () {
        
        return keycode;
    }
    
    public void setKeyCode (int key) {
        
        this.keycode = key;
    }
}