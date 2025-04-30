package main.java.game.view;

import main.java.game.model.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends APanel {


}

class HUD extends APanel{

}



class InputController {
    // WASD - move | R - reload | SPACE - shoot
    public KeyListener moveController = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };


    public KeyListener reloadController = new KeyAdapter() {

    };
}