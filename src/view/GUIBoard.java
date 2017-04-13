/*
 * Phuc Tran
 * Assignment 6 part A - Tetris
 */
package view;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * create the game.
 * @author Phuc Tran
 * @version 12/03/2015
 */
public class GUIBoard extends JPanel implements Observer {

       
    /** the timer for game animation.*/
    protected static Timer GAME_TIMER;
    
    /** the block size is shared to infoPanel class.*/
    protected static final int BLOCK_SIZE = 30;
    
    /** the board's Height is shared to infoPanel class.*/
    protected static final int GUI_HEIGHT = 590;
    
    
    /** the distance window to board is shared to infoPanel class.*/
//    protected static final int BORDER_DISTANCE = 50;
    
    /** The default delay (in milliseconds) for the move timer. */
    protected static final int MOVE_DELAY = 600;

    /** the 10x20 board is shared to infoPanel class.*/
    private static final Board BOARD = new Board(10, 20, null);
    
    /** move key.*/
    private static final KeyAction MOVE_KEY = new KeyAction(BOARD);

    /**
     * UID.
     */
    private static final long serialVersionUID = 5830830267603605621L;
    
    /** the board's width.*/
    private static final int GUI_WIDTH = 300;
    
    /** to check if game is pause.*/
    private boolean myIsPause;
    
    
    
    /**
     * setup the panel.
     */
    public GUIBoard() {
        super();
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));
        BOARD.addObserver(this);
        setupTimer();
        setupKeyListener();
        
    }
    
    
    /**
     * setup the timer.
     */
    private void setupTimer() {
        GAME_TIMER = new Timer(MOVE_DELAY, new MoveListener());
        GAME_TIMER.setInitialDelay(0);
        GAME_TIMER.start();
    }
    
    /**
     * get board.
     * @return board
     */
    public static Board getBoard() {
        return BOARD;
    }
    
    /**
     * setup key listener.
     */
    private void setupKeyListener() {
        addKeyListener(MOVE_KEY);
        addKeyListener(new PauseAction());
        addKeyListener(new ResumeAction());
        setFocusable(true);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics g = (Graphics) theGraphics;
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        //g.fillRect(0, getHeight() -10 , getWidth(), getHeight());
        //drawBorder(g);
        drawPiece(g);
        //drawGrid(g);
        g.setColor(Color.white);

    }
    
    /**
     * draw the board and separator. 
     * @param theGraphics The graphics context to use for painting.
     */
    public void drawBorder(final Graphics theGraphics) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final Rectangle2D box = new Rectangle2D.Double();
        
        
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.black);
        
        //center point of the box.
        final Point center = new Point(GUI_WIDTH / 2, GUI_HEIGHT / 2);
        
        // the corner point. this point is changeable to set the size of board.  
        final Point corner = new Point(center.x + 150, center.y + 300);

        box.setFrameFromCenter(center, corner);
        g2d.fill(box);
        
    }
    
    /**
     * draw the tetris piece.
     * @param theGraphics The graphics context to use for painting.
     */
    public void drawPiece(final Graphics theGraphics) {
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // get info from board class to draw piece.
        final Piece piece = BOARD.getCurrentPiece();
        final List<Block[]> frozenBlock = BOARD.getFrozenBlocks();
        final int[][] pieceCoord = ((AbstractPiece) piece).getBoardCoordinates();
        final Block block = ((AbstractPiece) piece).getBlock();

//        final RoundRectangle2D shape = new RoundRectangle2D.Double();
//        final int round = 20;
        
//        // draw moving block.
//        for (int i = 0; i < pieceCoord.length; i++) {
//            g2d.setColor(Color.RED);
//            shape.setRoundRect(setScale(pieceCoord[i][0]),
//                               -1 * setScale(pieceCoord[i][1]) + GUI_HEIGHT
//                                - BLOCK_SIZE,
//                            BLOCK_SIZE, BLOCK_SIZE, round, round);
//            
//            g2d.fill(shape);
//        }
//        
//        // draw frozen block
//        for (int i = 0; i < frozenBlock.size(); i++) {
//            final Block[] array = frozenBlock.get(i); //array of frozen block
//            for (int j = 0; j < array.length; j++) { 
//                if (array[j] != Block.EMPTY) {
//                    shape.setRoundRect(setScale(j),
//                                       -1 * setScale(i) + GUI_HEIGHT 
//                                        - BLOCK_SIZE
//                                 , BLOCK_SIZE, BLOCK_SIZE, round, round);
//                    g2d.fill(shape);
//                }
//            }
//        }
        
        
        // draw moving block.
        for (int i = 0; i < pieceCoord.length; i++) {
            setBlockColor(g2d, block);
            g2d.fill3DRect(setScale(pieceCoord[i][0]),
                               -1 * setScale(pieceCoord[i][1]) + getHeight()
                                - BLOCK_SIZE,
                            BLOCK_SIZE, BLOCK_SIZE, true);
            
            g2d.setColor(Color.white);
            g2d.draw3DRect(setScale(pieceCoord[i][0]),
                           -1 * setScale(pieceCoord[i][1]) + getHeight()
                            - BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE, true);
            

        }
        
        // draw frozen block
        for (int i = 0; i < frozenBlock.size(); i++) {
            final Block[] array = frozenBlock.get(i); //array of frozen block    
            for (int j = 0; j < array.length; j++) { 
                if (array[j] != Block.EMPTY) {  
                    setBlockColor(g2d, array[j]);
                    g2d.fill3DRect(setScale(j),
                                       -1 * setScale(i) + getHeight()
                                        - BLOCK_SIZE
                                 , BLOCK_SIZE, BLOCK_SIZE, true);
                    
                    g2d.setColor(Color.white);
                    g2d.draw3DRect(setScale(j),
                                   -1 * setScale(i) + getHeight()
                                    - BLOCK_SIZE
                             , BLOCK_SIZE, BLOCK_SIZE, true);
                }
            }
        }
    }
    
    /**
     * draw the grid.
     * @param theGraphics The graphics context to use for painting.
     */
    public void drawGrid(final Graphics theGraphics) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final Line2D line = new Line2D.Double();
        g2d.setColor(Color.lightGray.brighter().brighter().brighter());
        g2d.setStroke(new BasicStroke(1));
        
        for (int i = BLOCK_SIZE;
                        i < getHeight(); i = i + BLOCK_SIZE) {
            line.setLine(i, 0 , i, getHeight());
            g2d.draw(line);
        }
        
        for (int i = BLOCK_SIZE; 
                        i < getHeight(); i = i + BLOCK_SIZE) {
            line.setLine(0, i, getWidth() , i);
            g2d.draw(line);
        }
    }
    
    /**
     * A class that sets up color for pieces when they are moving down.
     * @param theBlock the Block with distinct color
     * @param theGraphics the Graphics
     */
    public static void setBlockColor(final Graphics theGraphics, final Block theBlock) {  
        final Graphics2D g = (Graphics2D) theGraphics;
        if (theBlock == Block.Z) {
            g.setColor(Color.MAGENTA);
        } else if (theBlock == Block.I) {
            g.setColor(Color.CYAN);
        } else if (theBlock == Block.O) {
            g.setColor(Color.RED);
        } else if (theBlock == Block.J) {
            g.setColor(Color.PINK);
        } else if (theBlock == Block.L) {
            g.setColor(Color.BLUE);
        } else if (theBlock == Block.S) {
            g.setColor(Color.GREEN);
        } else if (theBlock == Block.T) {
            g.setColor(Color.YELLOW);
        }
       
    }
    
    /**
     * Game over animation.
     */
    public void gameOver() {
        
        if (BOARD.isGameOver()) {   
            // stop the timer to prevent memory overflow
            GAME_TIMER.stop();
            
            final ImageIcon icon = new ImageIcon("images/cry.png");
            JOptionPane.showMessageDialog(new JFrame(), "Game Over!",
                                          "Tetris Message", 0, icon); 
        }

    }
    
    /**
     * set scale of the board.
     * @param theNumber the actual coordinate
     * @return the coordinate in GUI.
     */
    private int setScale(final int theNumber) {
        return theNumber * BLOCK_SIZE;
    }
    
    /**set Pause for game.
     * @param theIsPause the Game is pause*/
    private void setPause(final boolean theIsPause) {
        myIsPause = theIsPause;            
    }
    
    /**
     * pause and resume the game action.
     */
    private void pauseGame() {
        if (myIsPause) {
            GAME_TIMER.stop();
            removeKeyListener(MOVE_KEY);           
        } else {
            GAME_TIMER.start();
            addKeyListener(MOVE_KEY);     
        }
    }
    
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        repaint(); 
    }
    /**
     * Pause Key Action inner class.
     * @author phuc Tran
     * @version 12 11 2015
     */
    private class PauseAction extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                myIsPause = true;
                pauseGame(); 
                setPause(true);
                repaint();
               
            }
        }
    }
    
    /**
     * Resume Key Action inner class.
     * @author phuc tran
     * @version 12 11 2015
     */
    private class ResumeAction extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_R) {
                myIsPause = false;
                pauseGame(); 
                setPause(false);
                repaint();
               
            }
        }
    }
    
    /**
     * inner class.
     * timer moves 1 delay mean board moves 1 step.
     */
    private class MoveListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            gameOver();
            BOARD.step();
        }
        
    }

}
