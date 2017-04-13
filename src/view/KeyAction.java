/*
 * Phuc Tran
 * Assignment 6 part A - Tetris
 */
package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;

/**
 * create key Action.
 * @author Phuc Tran
 * @version 12/03/2015
 */
public class KeyAction implements KeyListener {
    
    /**
     * the Board.
     */
    private final Board myBoard;
    

    /**
     * constructor.
     * @param theBoard the Game Board (back end)
     */
    public KeyAction(final Board theBoard) {
        myBoard = theBoard;
    }
    

    /**
     * add action when key pressed.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            myBoard.moveLeft();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            myBoard.moveRight();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            myBoard.moveDown();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            myBoard.rotate();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_UP) {
            myBoard.hardDrop();
        }        
    }


    @Override
    public void keyReleased(final KeyEvent theEvent) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void keyTyped(final KeyEvent theEvent) {
        // TODO Auto-generated method stub
        
    }



}
