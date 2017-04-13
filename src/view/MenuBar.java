/*
 * Phuc Tran
 * Assignment 6 part A - Tetris
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * create menu for game frame.
 * @author Phuc Tran
 * @version 12/03/2015
 */
public class MenuBar extends JMenuBar {

    /**
     * UID.
     */
    private static final long serialVersionUID = -111659266189879540L;
    

    /**
     * constructor.
     * add menu to frame.
     * @param theFrame the main Frame.
     */
    public MenuBar(final JFrame theFrame) {
        super();
        add(createFileMenu(theFrame));
        //add(createOptionMenu());
        add(createHelpMenu(theFrame));
        
    }

    
    /**
     * create file menu with its button and action.
     * @param theFrame the main frame.
     * @return the file menu.
     */
    private JMenu createFileMenu(final JFrame theFrame) {
        final JMenu file = new JMenu("File");
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenuItem endGame = new JMenuItem("End Game");
        final JMenuItem quit = new JMenuItem("Quit");
        
        newGame.setEnabled(false);
        
        // action of quit button
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                    WindowEvent.WINDOW_CLOSING));                
            }            
        });
        
        // action of new game button        
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final int width = 10;
                final int height = 20;
                endGame.setEnabled(true);
                GUIBoard.getBoard().newGame(width, height, null);
                GUIBoard.GAME_TIMER.restart();
            }           
        });
        
        // action of end game button       
        endGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                endGame.setEnabled(false);
                newGame.setEnabled(true);
                GUIBoard.GAME_TIMER.stop();
                JOptionPane.showMessageDialog(theFrame, "GAME END !!!",
                                          "Tetris TCSS 305", JOptionPane.INFORMATION_MESSAGE);
            }           
        });
        
        // set hot key for each button.
        file.setMnemonic(KeyEvent.VK_F);
        newGame.setMnemonic(KeyEvent.VK_N);
        endGame.setMnemonic(KeyEvent.VK_E);
        quit.setMnemonic(KeyEvent.VK_Q);
        
        file.add(newGame);
        file.addSeparator();
        file.add(endGame);
        file.addSeparator();
        file.add(quit);
        
        return file;
    }

    // i don't use this option menu for now.
    // but i think i will use it later to improve my tetris.
//    /**
//     * create option menu with its button and action.
//     * @return the option menu.
//     */
//    private JMenu createOptionMenu() {
//        final JMenu option = new JMenu("Options");
//        final JMenuItem setKey = new JMenuItem("Set Keys");
//        final JMenuItem setWindowSize = new JMenu("Set Window Size");
//        
//        //set window size option button
//        final JRadioButtonMenuItem size1 = new JRadioButtonMenuItem("Small");
//        final JRadioButtonMenuItem size2 = new JRadioButtonMenuItem("Medium (default)");
//        final JRadioButtonMenuItem size3 = new JRadioButtonMenuItem("Large");
//        final ButtonGroup sizeGroup = new ButtonGroup();
//        
//        setKey.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                // TODO Auto-generated method stub 
//            }   
//        });
//        
//        
//        size1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                
//                
//            }
//            
//        });
//        
//        size2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                // TODO Auto-generated method stub
//                
//            }
//            
//        });
//        
//        size3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                // TODO Auto-generated method stub
//                
//            }
//            
//        });
//        
//        sizeGroup.add(size1);
//        size1.setSelected(true);
//        sizeGroup.add(size2);
//        sizeGroup.add(size3);
//        sizeGroup.clearSelection();
//        setWindowSize.add(size1);
//        setWindowSize.add(size2);
//        setWindowSize.add(size3);
//        
//        
//        option.add(setKey);
//        option.addSeparator();
//        option.add(setWindowSize);
//        
//        return option;
//    }
    
    /**
     * create Help menu with its button and action.
     * @param theFrame the main frame.
     * @return the option menu.
     */
    private JMenu createHelpMenu(final JFrame theFrame) {
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem keyHelp = new JMenuItem("Key helps");
        final JMenuItem about = new JMenuItem("About...");
        
        keyHelp.addActionListener(theEvent -> JOptionPane.showMessageDialog(theFrame,
                                  "Key help: "
                                  + "\n   Move Left: Left Arrow"
                                  + "\n   Move Right: Right Arrow"
                                  + "\n   Drop One Line: Down Arrow"
                                  + "\n   Hard Drop: Up Arrow"
                                  + "\n   Rotate: Space", "Key help",
                                  JOptionPane.INFORMATION_MESSAGE));
        
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(theFrame,
                              "TCSS 305 Tetris, Autumn 2015", "About The Game...",
                              JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(keyHelp);
        helpMenu.add(about);
        
        return helpMenu;
        
    }

}
