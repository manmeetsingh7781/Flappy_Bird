package Flappy_Bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JPanel implements variables , ActionListener{
    private Poles Pole;
    private int speedX = 10, speedY, playerX, playerY, player_Width, player_Height;
    private int pole_height_upside = 200, pole_width_upside = 10;
    private int pole_height_downside= 200, pole_width_downside = 10, level = 1;
    private Timer timer;
    private  Random random_height = new Random();
    private int moving_x_down = 120,   moving_x = screen_width;
    private int random_position_upside , rand_up_height;
    private boolean doesWon = false;




    public Game(int up, int down){
        int delay = 50;
        Pole = new Poles(poles, 2);
        random_position_upside = random_height.nextInt(150);

        rand_up_height = random_height.nextInt(random_height.nextInt(400));
        if(rand_up_height > 180){
            pole_height_upside = rand_up_height;
        } else {
            rand_up_height = random_height.nextInt(random_height.nextInt(400));
        }

        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g){

        if(random_position_upside < 50){
            random_position_upside = random_height.nextInt(150);
        }
        // Draw the screen
        g.setColor(Color.black);

        // Coloring the view every interval
        g.fillRect(0,0, screen_width, screen_height);

        // Increasing thier speed
        moving_x -= speedX;



            // using 2D for intersections between player Axs
            draw((Graphics2D) g);

        if(doesWon) {
            win((Graphics2D) g);
        }
    }

    private void draw(Graphics2D g) {

            for (int up = 0; up < Pole.map.length; up++) {

                for (int down = 0; down < Pole.map[0].length; down++) {
                    if (Pole.map[up][down] > 0) {


//                    // For intersection between player position
                        Rectangle upSidePoles, downSidePoles;

                        // Upside fake Poles
                        upSidePoles = new Rectangle(up * random_position_upside + moving_x + Pole.map[up][down], Pole.map[up][down], pole_width_upside, pole_height_upside + (up * 2));

                        // Downside fake poles
                        downSidePoles = new Rectangle(up * 160 + moving_x_down + Pole.map[up][down], screen_height - pole_height_upside + Pole.map[up][down], pole_width_upside, pole_height_upside - down);

                        // UP side real poles
                        g.setColor(Color.white);
                        g.fillRect(up * random_position_upside + moving_x + Pole.map[up][down], Pole.map[up][down], pole_width_upside, pole_height_upside + (up * 2));

                        // Down side real poles
                        g.setColor(Color.white);
                        g.fillRect(up * 160 + moving_x_down + Pole.map[up][down], screen_height - pole_height_upside + Pole.map[up][down], pole_width_upside, pole_height_upside + down * 2);

                        // checking if last pole has been gone out of view
                        if (upSidePoles.x < -random_position_upside * poles) {
                            doesWon = true;
                            timer.stop();

                        }


                    }
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