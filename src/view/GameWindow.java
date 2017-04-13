/*
 * Phuc Tran
 * Assignment 6 part A - Tetris
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



/**
 * this class create the window for tetris game.
 * @author Phuc Tran
 * @version 12/03/2015
 */
public class GameWindow {
    
    /**
     * the main frame.
     */
    private final JFrame myFrame;
    
    /**
     * the game Panel.
     */
    private final GUIBoard myPanel;
    
    /** the info panel.*/
    private final InfoPanel myInfoPanel;
    
    /**
     * initialize the field.
     */
    public GameWindow() {
        myFrame = new JFrame("Tetris");       
        myPanel = new GUIBoard();
        myInfoPanel = new InfoPanel();
    
    }
    
    /**
     * set up game window.
     */
    public void setup() {

        final MenuBar menu = new MenuBar(myFrame);
        
        myFrame.setIconImage(new ImageIcon("image/tetrisIcon.png").getImage());
        // add menu and panel to frame.
        myFrame.setJMenuBar(menu);
        myFrame.add(myPanel, BorderLayout.WEST);
        myFrame.add(myInfoPanel, BorderLayout.CENTER);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        
        myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), 
                                             myFrame.getHeight()));
        myFrame.setResizable(false);
        
        // have the window appear in the middle of screen.
        /** A ToolKit. */
        final Toolkit kit = Toolkit.getDefaultToolkit();
        
        myFrame.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - myFrame.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - myFrame.getHeight() / 2));
        
        myFrame.setVisible(true);
    }
    

}
