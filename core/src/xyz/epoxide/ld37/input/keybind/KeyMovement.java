package xyz.epoxide.ld37.input.keybind;

import com.badlogic.gdx.Input;
import xyz.epoxide.ld37.LD37;

public class KeyMovement extends KeyBind {
    public KeyMovement(int keycode) {
        super(keycode);
    }

    @Override
    public void onUpdate(float delta) {
        switch (this.getKeyCode()) {
            case Input.Keys.W: {
                LD37.INSTANCE.entityPlayer.addPosition(0, 1 * delta);
                break;
            }
            case Input.Keys.A: {
                LD37.INSTANCE.entityPlayer.addPosition(-1 * delta, 0);
                break;
            }
            case Input.Keys.S: {
                LD37.INSTANCE.entityPlayer.addPosition(0, -1 * delta);
                break;
            }
            case Input.Keys.D: {
                LD37.INSTANCE.entityPlayer.addPosition(1 * delta, 0);
                break;
            }
        }
    }
}
