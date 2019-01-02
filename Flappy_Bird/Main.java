package Flappy_Bird;

import javax.swing.*;

public class Main  implements variables {

    //  The Main frame
    private static JFrame frame = new JFrame();

    // Game Frame
    private static Game game = new Game(poles, 10);

    public static void main(String[] args) {

        // Setting  visibility of the frame
        frame.setVisible(true);

        // Setting Dimensions
        frame.setSize(screen_width, screen_height);

        // Title of the Main Frame
        frame.setTitle("Flappy Bird");

        // The close method
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding game Panel into the frame
        frame.add(game);

        // Setting image Icon
        ImageIcon img_icon = new ImageIcon("src\\bricks-game.jpg");

        // Getting the image preview from the ImageIcon and setting it up
        frame.setIconImage(img_icon.getImage());

        // Setting Window resizable to false
        frame.setResizable(false);
    }
}
