package Flappy_Bird;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class Poles implements variables {
    public int[][] map;
    public ArrayList<Integer> elements;
    private int random_result;



    Poles( long number_of_elements ) {
        elements = new ArrayList<>();
        for (int height = 0; height < number_of_elements; height++) {
            random_result = new SplittableRandom().nextInt((screen_height/3), (screen_height/2)-10);
            elements.add(random_result);
        }
    }

}

