package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;

public class KeyBind extends Registerable<KeyBind> {

    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<KeyBind>();

    private static final KeyBind W = registerKeyBind("w", new KeyMovement(Input.Keys.W));
    private static final KeyBind A = registerKeyBind("a", new KeyMovement(Input.Keys.A));
    private static final KeyBind S = registerKeyBind("s", new KeyMovement(Input.Keys.S));
    private static final KeyBind D = registerKeyBind("d", new KeyMovement(Input.Keys.D));

    private static final KeyBind JUMP = registerKeyBind("jump", new KeyJump());

    private int keycode;

    public KeyBind(int keycode) {

        this.keycode = keycode;
    }

    public static KeyBind registerKeyBind(String id, KeyBind bind) {

        bind.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(bind);
        return bind;
    }

    public boolean isPressed() {

        return Gdx.input.isKeyPressed(this.getKeyCode());
    }

    public void onPressed() {

    }

    public void onReleased() {

    }

    public void onUpdate(float delta) {

    }

    public int getKeyCode() {

        return keycode;
    }

    public void setKeyCode(int key) {

        this.keycode = key;
    }
}