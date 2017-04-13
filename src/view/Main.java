/**
 * 
 */
package view;

import java.awt.EventQueue;



/**
 * main class.
 * @author phuc tran
 * @version 11 21 2015
 *
 */
public final class Main {
    
    /**
     * 
     */
    private Main() {
        throw new IllegalStateException();
    }

    /**
     * run the program.
     * @param theArgs is main parameter
     */
    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GameWindow run = new GameWindow(); 
                // run the program
                run.setup();
            }
        });

    }

}
