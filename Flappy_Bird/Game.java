package Flappy_Bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JPanel implements variables , ActionListener{
    private Poles Pole;
    private int speedX = 10, speedY, playerX, playerY, player_Width, player_Height;
    private int pole_width = 10;
    private  static int level = 1;
    private Timer timer;


    private int  moving_x = screen_width;
    private int random_position_upside , rand_up_height;
    private boolean doesWon = false;
    public  static  int dynamic_poles = 2;
    int number_of_poles_for_Height = dynamic_poles;

    // For intersection between player position
    private Rectangle upSidePoles, downSidePoles;



    public Game(){
        int delay = 50;

        dynamic_poles += 10;
        Pole = new Poles(number_of_poles_for_Height);

        random_position_upside = random_number.nextInt(150);
        timer = new Timer(delay, this);


        timer.start();

    }

    public void paint(Graphics g){

        if(random_position_upside < 90){
            random_position_upside = random_number.nextInt(200);
        }
        // Draw the screen
        g.setColor(Color.black);

        // Coloring the view every interval
        g.fillRect(0,0, screen_width, screen_height);

        // Increasing thier speed
        moving_x -= speedX;


        draw((Graphics2D) g);

        if(doesWon) {
            win((Graphics2D) g);
        }

    }

    private void draw(Graphics2D g) {

        // Upside Poles
        for (int h = 0; h < Pole.heights.length; h++) {


            int height =  Pole.heights[h];
            g.setColor(Color.cyan);


            // Upside fake Poles
            upSidePoles = new Rectangle(h * (screen_width/3) + moving_x,  0 ,pole_width, height);
            g.drawRect(h * (screen_width/3) + moving_x, 0, pole_width, height);

            // checking if last pole has been gone out of view
            if (upSidePoles.x < -(screen_width/3) * dynamic_poles) {
                doesWon = true;
                timer.stop();
                System.out.println("STOPPED BY UPSIDE");

            }

        }


        // Downside Poles
        for (int h = 0; h < Pole.heights.length; h++) {
            int height = Pole.heights[h];
            g.setColor(Color.cyan);

            // downSide fake Poles
            downSidePoles = new Rectangle(h * random_position_upside + moving_x, screen_height - height, pole_width, height);
            g.drawRect(h * random_position_upside + moving_x, screen_height - height, pole_width, height);

            if (downSidePoles.x < -random_position_upside * dynamic_poles) {
                doesWon = true;
                timer.stop();
                System.out.println("STOPPED BY DOWNSIDE");

            }
        }










    }




    public void actionPerformed(ActionEvent e) {
        // Start framing
        timer.start();

        // Redraw every milliseconds
        repaint();

    }

    private void drawMessege(Graphics g, String messege,int size, Color col, int x, int y){
        g.setColor(col);
        g.setFont(new Font("Times new Roman", Font.BOLD, size));
        g.drawString(messege, x, y);
    }

    private void win(Graphics2D g){
        if(doesWon){
            drawMessege(g, "Level " + level + " has been Passed", 32, Color.green, 200, 200);
            level+=1;
            timer.stop();
        }
    }



}


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