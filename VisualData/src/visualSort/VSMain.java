package visualSort;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class VSMain extends JPanel implements Runnable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread animator;
    private final int DELAY = 5;
    private int[] sorts;
    public VSMain() {
        super();
        setBackground(Color.BLACK);
        setFocusable(true);
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        sorts = new int[(int)d.getWidth()/2];
        for(int i = 0; i < sorts.length; i++){
            sorts[i] = (int)(Math.random()*(d.getHeight()-10));
        }
        setPreferredSize(d);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < sorts.length; i++){
            g2d.drawRect(i,(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight(), 2, sorts[i]);
        }
    }
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while(true){
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if(sleep < 0){
                sleep = 2;
            }
            try{
                Thread.sleep(sleep);
            } catch (InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this,msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}

    
