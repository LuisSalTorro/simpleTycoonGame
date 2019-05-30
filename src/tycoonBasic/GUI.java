package tycoonBasic;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.GridLayout;

public class GUI extends JFrame{ //JPanel
    private JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    private final int WIN_WIDTH = 450, WIN_HEIGHT = 450;

     public GUI(){
         Play play;

         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         play = new Play();
         Player player = play.getPlayer();
         setLayout(new GridLayout(4,2));
         setTitle(player.getName());

         buildPanel(player);//goes to the method that creates the panel

         add(panel);
         add(panel2);
         add(panel3);
         add(panel4);
         add(panel5);
         add(panel6);
         add(panel7);
         add(panel8);
         setSize(WIN_WIDTH,WIN_HEIGHT);
         setVisible(true);
    }

    /**
     * creates the panel
     */

    public void buildPanel(Player player) {
        JLabel playerName = new JLabel(player.getName());
        JLabel moraleLabel = new JLabel("Morale: 0");
        JLabel dateLable = new JLabel(player.getDateSTR());
        JLabel workers = new JLabel("Workers: 0");

        JLabel randomSHitLOL = new JLabel("Hey bros hows it going?");
        JLabel randomSHitLOL2 = new JLabel("Hey bros hows it going?");

        panel = new JPanel(new GridLayout(3,0));
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();

       // panel.add(playerName);
        panel.add(dateLable);
        panel.add(workers);
        panel.add(moraleLabel);

        panel2.add(randomSHitLOL);
        panel3.add(randomSHitLOL2);


//        panel4.add();
//        panel5.add();
//        panel6.add();
//
//        panel7.add();
//        panel8.add();
    }

}
