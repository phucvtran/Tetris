/*
 * Phuc Tran
 * Assignment 6 part A - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * the information panel.
 * @author Phuc Tran
 * @version 12/03/2015
 */
public class InfoPanel extends JPanel implements Observer {

    /**
     * UID.
     */
    private static final long serialVersionUID = 4691417946172572337L;
    
    /** the info panel width.*/
    private static final int INFO_WIDTH = 250;  
    
    /** the font size. */
    private static final int FONT_SIZE = 15;
    
    /** the Y coordinate of the text.*/
    private static final int Y_COORD = 200;
    
    /** time delay for level up.*/
    private static final int LEVEL_UP_DELAY = 200;
    
    /** the same board from GUIBoard class.*/
    private final Board myBoard =  GUIBoard.getBoard();
    
    /** line clear count.*/
    private int myLineCleared;
    

    /**
     * setup the panel.
     */
    public InfoPanel() {
        super();
        myBoard.addObserver(this); //keep updating.
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(INFO_WIDTH, GUIBoard.GUI_HEIGHT));
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics g = (Graphics) theGraphics;
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.drawLine(0, 0, 0, getHeight());
        drawPreviewPiece(g);
        //drawHelpPanel(g);
        drawScore(g);

    }
    
    /**
     * draw the tetris preview piece.
     * @param theGraphics The graphics context to use for painting.
     */
    private void drawPreviewPiece(final Graphics theGraphics) {
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        final Piece piece = myBoard.getNextPiece();
        final int[][] pieceCoord = ((AbstractPiece) piece).getBoardCoordinates();
        final Block block = ((AbstractPiece) piece).getBlock();
        
        //draw next piece
        for (int i = 0; i < pieceCoord.length; i++) {
            GUIBoard.setBlockColor(g2d, block);
            g2d.fill3DRect(setScale(pieceCoord[i][0])-10,
                               -1 * setScale(pieceCoord[i][1]) + getHeight() + 125
                                - GUIBoard.BLOCK_SIZE,
                                GUIBoard.BLOCK_SIZE, GUIBoard.BLOCK_SIZE, true);
            
            g2d.setColor(Color.white);
            g2d.draw3DRect(setScale(pieceCoord[i][0])-10,
                           -1 * setScale(pieceCoord[i][1]) + getHeight() + 125
                            - GUIBoard.BLOCK_SIZE,
                            GUIBoard.BLOCK_SIZE, GUIBoard.BLOCK_SIZE, true);
            
            //g2d.fill(shape);
        }
    }
    
    /**
     * draw the help panel.
     * @param theGraphics The graphics context to use for painting.
     */
    private void drawHelpPanel(final Graphics theGraphics) {
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        // the title string size is bigger
        g2d.setFont(new Font("", Font.BOLD, FONT_SIZE * 2));
        g2d.setColor(Color.red);
        
        // the x-coordinate = 3 Block_size = 60
        drawString(g2d, "Key Help", GUIBoard.BLOCK_SIZE * (2 + 1),
                   // this y-coordinate = 175.
                   50 * (2 + 1) + GUIBoard.BLOCK_SIZE / 2);
        
        g2d.setFont(new Font("", Font.PLAIN, FONT_SIZE));
        g2d.setColor(Color.BLUE);
        
        // assume the X-Coordinate of these string = BLOCK_SIZE = 30
        drawString(g2d, "Move Left: Left Arrow", GUIBoard.BLOCK_SIZE, Y_COORD);
        
        drawString(g2d, "Move Right: Right Arrow", 
                   GUIBoard.BLOCK_SIZE, Y_COORD + GUIBoard.BLOCK_SIZE);
        
        drawString(g2d, "Drop One Line: Down Arrow",
                   GUIBoard.BLOCK_SIZE, Y_COORD + GUIBoard.BLOCK_SIZE * 2);
        
        drawString(g2d, "Hard Drop: Up Arrow", GUIBoard.BLOCK_SIZE,
                   Y_COORD + GUIBoard.BLOCK_SIZE * (2 + 1));
        
        drawString(g2d, "Rotate: Space", GUIBoard.BLOCK_SIZE,
                   Y_COORD + GUIBoard.BLOCK_SIZE * (2 + 2));
        
        drawString(g2d, "Pause Game: P", GUIBoard.BLOCK_SIZE,
                   Y_COORD + GUIBoard.BLOCK_SIZE * (2 + 2 + 1));
        
        drawString(g2d, "Resume Game: R", GUIBoard.BLOCK_SIZE,
                   Y_COORD + GUIBoard.BLOCK_SIZE * (2 + 2 + 2));
        
        // the TETRIS logo.
        g2d.setFont(new Font("", Font.ITALIC, FONT_SIZE * 2 + FONT_SIZE));
        g2d.setColor(Color.GREEN);
        
        // X coordinate of this logo is 60 = 4 * font Size.
        drawString(g2d, "TETRIS", (2 + 2) * FONT_SIZE,
                   GUIBoard.GUI_HEIGHT - 50);
        

    }
    
    /**
     * draw score board for the game.
     * @param theGraphics The graphics context to use for painting.
     */
    private void drawScore(final Graphics theGraphics) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
     // the title string size is bigger
        g2d.setFont(new Font("", Font.BOLD, FONT_SIZE * 2));
        g2d.setColor(Color.red);
        
        // the x-coordinate = 3 Block_size = 60
        drawString(g2d, "Score", GUIBoard.BLOCK_SIZE * (2 + 1),
                   // this y-coordinate = 450, next string will be 30 pixel below this string.
                   Y_COORD * 2 + GUIBoard.BLOCK_SIZE + GUIBoard.BLOCK_SIZE);
        
        g2d.setFont(new Font("", Font.PLAIN, FONT_SIZE + FONT_SIZE / 2));
        g2d.setColor(Color.BLACK);
        
        drawString(g2d, "Current Level: " + myLineCleared / 2, // clear 2 lines to level up.
                   GUIBoard.BLOCK_SIZE, Y_COORD * 2 
                   + GUIBoard.BLOCK_SIZE+ GUIBoard.BLOCK_SIZE * 2);
        
        drawString(g2d, "Lines To Next Level: "
                   + (2 - (myLineCleared % 2)), // count down line.
                   GUIBoard.BLOCK_SIZE, Y_COORD * 2 
                   + GUIBoard.BLOCK_SIZE + GUIBoard.BLOCK_SIZE * (2 + 1));
        
        drawString(g2d, "Line Cleared: " + myLineCleared, 
                   GUIBoard.BLOCK_SIZE, Y_COORD * 2 
                   + GUIBoard.BLOCK_SIZE + GUIBoard.BLOCK_SIZE * (2 + 2));
        
        // I choose the score to be 30 for each line clear
        drawString(g2d, "Score: " + myLineCleared * GUIBoard.BLOCK_SIZE, 
                   GUIBoard.BLOCK_SIZE, Y_COORD * 2 
                   + GUIBoard.BLOCK_SIZE + GUIBoard.BLOCK_SIZE * (2 + 2 + 1));
    }
    
    /**
     * this method help to draw text on the panel.
     * @param theString the text
     * @param theX x coordinate
     * @param theY y coordinate
     * @param theGraphics The graphics context to use for painting.
     */
    private void drawString(final Graphics theGraphics, final String theString,
                            final int theX, final int theY) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawString(theString, theX, theY);
    }
    
    /**
     * level up behavior.
     */
    private void levelAction() {
        // level up will makes the piece drop faster.
        if (myLineCleared / 2 == 1) {
            GUIBoard.GAME_TIMER.setDelay(GUIBoard.MOVE_DELAY - LEVEL_UP_DELAY); // 400
        } else if (myLineCleared / 2 == 2) {
            GUIBoard.GAME_TIMER.setDelay(GUIBoard.MOVE_DELAY / 2); // 300
        } else if (myLineCleared / 2 == 2 + 1) { // level 3
            GUIBoard.GAME_TIMER.setDelay(GUIBoard.MOVE_DELAY - LEVEL_UP_DELAY * 2); // 200
        } else if (myLineCleared / 2 == 2 + 2) { // level 4
            GUIBoard.GAME_TIMER.setDelay((GUIBoard.MOVE_DELAY / 2) - LEVEL_UP_DELAY); // 100
            
        // level 5 and above the time delay will be very low (same for these level). 
        } else if (myLineCleared / 2 >= (2 + 2 + 1)) {
            // this delay is 30 same with BLOCK_SIZE value.
            GUIBoard.GAME_TIMER.setDelay(GUIBoard.BLOCK_SIZE);
        }
    }
    

    
    /**
     * set scale of the board.
     * @param theNumber the actual coordinate
     * @return the coordinate in GUI.
     */
    private int setScale(final int theNumber) {
        return theNumber * GUIBoard.BLOCK_SIZE;
    }
    

    @Override
    public void update(final Observable theObservable, final Object theObject) {
        if (theObservable instanceof Board) {
            
            // go through list of frozen block row.
            for (int i = 0; i < ((Board) theObservable).getFrozenBlocks().size(); i++) {
                
                boolean clear = true;
                final Block[] blocks = ((Board) theObservable).getFrozenBlocks().get(i);
                
                // check each frozen block row for empty block.
                // it means the line is unable to be cleared.
                for (final Block block : blocks) {
                    if (block == Block.EMPTY) {
                        clear = false;
                        break;
                    }
                }
                
                if (clear) {
                    myLineCleared = myLineCleared + 1;
                    break;
                }
            }
        }
        levelAction();
        repaint();
        
    }



}
