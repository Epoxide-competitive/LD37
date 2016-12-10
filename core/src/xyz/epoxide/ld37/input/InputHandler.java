package xyz.epoxide.ld37.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import xyz.epoxide.ld37.input.keybind.KeyBind;

public class InputHandler implements InputProcessor {
    
    @Override
    public boolean keyDown(int keycode) {
        
        for (KeyBind key : KeyBind.REGISTRY) {
            
            if (key.getKeyCode() == keycode) {
                
                key.onPressed();
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        
        for (KeyBind key : KeyBind.REGISTRY) {
            
            if (key.getKeyCode() == keycode) {
                
                key.onReleased();
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
