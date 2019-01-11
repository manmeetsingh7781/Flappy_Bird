package Flappy_Bird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game extends JComponent implements variables , ActionListener, MouseListener {


    private static int pole_width = 30;
    private static long number_of_poles = 9999L;

    private int playerY = (screen_height/2)-20;
    private int bkX = 0;
    private int bkX2 = screen_width;
    private int score = 0;
    private static double gravity = 0.01, gravitySpeed = 0;

    private Rectangle bird;
    private Timer timer;
    private Poles Pole;
    private int  moving_x_of_poles = screen_width,  random_position_for_placing_downside ,  random_position_for_placing_upside;
    private boolean doesWon = false;
    private static int level = 1;
    private BufferedImage bk = null , bk2 = null;


    Game(){
        int delay = 15;

        Pole = new Poles(number_of_poles);

        random_position_for_placing_downside = screen_width/4 + pole_width;
        random_position_for_placing_upside = screen_width/4 - pole_width;


        timer = new Timer(delay, this);
        this.addMouseListener(this);

        timer.start();
        try {
            bk = ImageIO.read(new File("src\\Flappy_Bird\\bk.png"));
            bk2 = ImageIO.read(new File("src\\Flappy_Bird\\bk2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    public void paint(Graphics g){
        score += 1/0.9;
        // Draw the screen


        int bkMoving_speed = 1;
        bkX -= bkMoving_speed;
            bkX2 -= bkMoving_speed;
        Rectangle backPanel = new Rectangle(bkX, 0, screen_width * 2, screen_height);
        Rectangle backPanel2 = new Rectangle(bkX2, 0, screen_width * 2, screen_height);

            g.drawImage(bk, bkX,0,  screen_width*2,  screen_height, null);
            g.drawImage(bk2, bkX2,0,  screen_width*2,  screen_height, null);
            if(backPanel.x <= -(screen_width*2))  {
               bkX = screen_width;
            }

            if(backPanel2.x <= -(screen_width*2)) {
                bkX2 = screen_width;
            }



        // Increasing thier speed
        // Poles
        int moving_speedX = 10;
        this.moving_x_of_poles -= moving_speedX;

        gravitySpeed += gravity;

        double moving_speedY = 0.5d;
        this.playerY += (int) moving_speedY + (int) gravitySpeed;

        drawPoles((Graphics2D) g);

        drawMessege(g, ""+score, Color.white, screen_width/2, 40);

        // Winning Circle
        if(doesWon) {
            win((Graphics2D) g);
        }


    }


    private void drawPoles(Graphics2D g) {
        playerY += gravity;
        drawBird(g);
        // Upside Poles
        for (int h = 0; h < Pole.elements.size(); h++) {


            int height =  Pole.elements.get(h);
            g.setColor(Color.green);


            // Upside fake Poles

            // For intersection between player position
            Rectangle upSidePoles = new Rectangle(h * random_position_for_placing_upside + moving_x_of_poles, 0, pole_width, height);


            //Real poles
            g.fill(upSidePoles);


            // checking if last pole has been gone out of view
            if (upSidePoles.x < -random_position_for_placing_upside * number_of_poles) {
                doesWon = true;
                timer.stop();
            }



            // downSide  Poles
            Rectangle downSidePoles = new Rectangle(h * random_position_for_placing_downside + moving_x_of_poles, screen_height - height, pole_width, height);

           // Real poles
            g.fill(downSidePoles);

            // Pole X if gone then win is true
            if (downSidePoles.x < -random_position_for_placing_downside * number_of_poles) {
                doesWon = true;
                timer.stop();
            }

            // Bird collision with poles
            if(upSidePoles.intersects(bird) || downSidePoles.intersects(bird)){
                timer.stop();
            }
        }

    }





    private void drawBird(Graphics2D g)
    {
        try {
               BufferedImage bird_image = ImageIO.read(new File("C:\\Users\\Honey Singh\\IdeaProjects\\Java_Course_Udamy\\src\\Flappy_Bird\\bird.png"));
            // Player
            int playerX = (screen_width / 2) - 20;
            int player_Width = 25;
            int player_Height = 20;
            // Game
               bird = new Rectangle(playerX, playerY, player_Width, player_Height);
               g.drawImage(bird_image, playerX, playerY, player_Width, player_Height, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        // Start framing
        timer.start();



        // Redraw every milliseconds
        repaint();

    }

    private static void accelerate( double val ) {
        gravity = val;
    }

    private void drawMessege(Graphics g, String messege, Color col, int x, int y){
        g.setColor(col);
        g.setFont(new Font("Times new Roman", Font.BOLD, 32));
        g.drawString(messege, x, y);
    }



    @Override
    public void mousePressed(MouseEvent e) {
        accelerate(-0.2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        accelerate(0.2);
    }



    @Override
    public void mouseClicked(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("ENTERED");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("EXIT");
    }



    private void win(Graphics2D g) {
        if (doesWon) {
            drawMessege(g, "Level " + level + " has been Passed", Color.green, 200, 200);
            level += 1;
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
