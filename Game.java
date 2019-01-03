package Flappy_Bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;


public class Game extends JComponent implements variables , ActionListener, MouseListener {


    // Poles
    private  static int moving_speedX = 10, moving_speedY, pole_width = 10;
    private static long number_of_poles = 9L;

    // Player
    private int playerX = (screen_width/2)-20, playerY = (screen_height/2)-20, player_Width = 20, player_Height = 20;
    private  double gravity  = 1d;

    // Game
    Rectangle bird, upSidePoles, downSidePoles;
    private Timer timer;
    private Poles Pole;
    private int  moving_x_of_poles = screen_width,  random_position_for_placing_downside ,  random_position_for_placing_upside;
    private boolean doesWon = false;


    public Game(){
        int delay = 10;

        Pole = new Poles(number_of_poles);

        random_position_for_placing_downside = screen_width/3;
        random_position_for_placing_upside = screen_width/4;


        timer = new Timer(delay, this);
        this.addMouseListener(this);

        timer.start();

    }

    public void paint(Graphics g){

        // Draw the screen
        g.setColor(Color.black);

        // Coloring the view every interval
        g.fillRect(0,0, screen_width, screen_height);

        // Increasing thier speed
        moving_x_of_poles -= moving_speedX;




        drawPoles((Graphics2D) g);
        drawBird((Graphics2D)  g);


        // Winning Circle
//        if(doesWon) {
//            win((Graphics2D) g);
//        }

    }


    private void drawPoles(Graphics2D g) {
        playerY += gravity;
        // Upside Poles
        for (int h = 0; h < Pole.elements.size(); h++) {


            int height =  Pole.elements.get(h);
            g.setColor(Color.cyan);


            // Upside fake Poles

            // For intersection between player position
            upSidePoles = new Rectangle(h * random_position_for_placing_upside + moving_x_of_poles, 0, pole_width, height);


            //Real poles
            g.fill(upSidePoles);


            // checking if last pole has been gone out of view
            if (upSidePoles.x < -random_position_for_placing_upside * number_of_poles) {
                doesWon = true;
                //timer.stop();
            }

        }

        // Downside Poles
        for (int h = 0; h < Pole.elements.size(); h++) {

            int height =  Pole.elements.get(h);
            g.setColor(Color.cyan);

            // downSide  Poles
            downSidePoles = new Rectangle(h * random_position_for_placing_downside + moving_x_of_poles, screen_height - height, pole_width, height);

           // Real poles
            g.fill(downSidePoles);


            if (downSidePoles.x < -random_position_for_placing_downside * number_of_poles) {
                doesWon = true;
                //timer.stop();

            }
        }


    }



    public void drawBird(Graphics2D g)
    {
        g.setColor(Color.red);
        g.drawOval(playerX, playerY, player_Width, player_Height);
    }

    public void actionPerformed(ActionEvent e) {
        // Start framing
        timer.start();



        // Redraw every milliseconds
        repaint();

    }


    private void drawMessege(Graphics g, String messege, int size, Color col, int x, int y){
        g.setColor(col);
        g.setFont(new Font("Times new Roman", Font.BOLD, size));
        g.drawString(messege, x, y);
    }



   public static void addKeyboardListener(JComponent component, int keyCode, boolean keyRelease,String id, ActionListener actionListener)
   {

           // How and where to get the input from
           InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

           // Get the data
           ActionMap actionMap = component.getActionMap();

           // Put the data and bind it with the method
           inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, keyRelease), id);

           // Create a method to use it when pressed the key
           actionMap.put(id, new AbstractAction() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   actionListener.actionPerformed(e);
               }
           });

   }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gravity += 1f;
    }







    @Override
    public void mouseClicked(MouseEvent e) {
        gravity -= 1f;
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("ENTERED");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("EXIT");
    }
}


//    private void win(Graphics2D g){
//        if(doesWon){
//            drawMessege(g, "Level " + level + " has been Passed", 32, Color.green, 200, 200);
//            level+=1;
//            timer.stop();
//        }
//    }

   /*
                    *
                    * // if the scene has been gone start the moving_x all over again
                        In case if we want to respawn the poles in the same level

                     // Settting up their random padding position between each others
                     random_position_upside = random_height.nextInt(300);

                    // Setting up random height
                    rand_up_height = random_height.nextInt(random_height.nextInt(200));

                    // Checking if the height is over 80 then apply else do it again
                    if(rand_up_height > 80){
                        pole_height_upside = rand_up_height;
                    }

                    * */


                    /*DRAW
                    *  for(int pole_height = 0; pole_height < Pole.heights.length; pole_height++) {

            for (int up = 0; up < Pole.map.length; up++) {
                for (int down = 0; down < Pole.map[0].length; down++) {
                    if (Pole.map[up][down] > 0) {






                        // Downside fake poles

                        // UP side real poles
                        g.setColor(Color.white);
                        g.drawRect(
                                up * random_position_upside + moving_x + Pole.map[up][down],
                                Pole.map[up][down],
                                pole_width_upside,
                                pole_height);



                        // Down side real poles
//                        g.setColor(Color.white);
//                        g.fillRect(up * 160 + moving_x_down + Pole.map[up][down], screen_height - pole_height_upside + Pole.map[up][down], pole_width_upside, pole_height_upside + down * 2);


                    }

                    }
                }
            }
                    *
                    * */

    //addKeyboardListener(this, KeyEvent.VK_SPACE, true,"UP", (event) ->{
//            gravity = gravity-2f;
//
//        });
//
//
//
//        addKeyboardListener(this, KeyEvent.VK_SPACE, false,"DOWN", (event) ->{
//            gravity = gravity+2f;
//        });
