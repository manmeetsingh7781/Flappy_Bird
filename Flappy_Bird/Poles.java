package Flappy_Bird;

import java.util.SplittableRandom;

public class Poles implements variables {
    public int[][] map;
    public int[] heights;
    private int random_result;

    Poles( int heights_of_upside) {
        heights = new int[heights_of_upside];
        for (int height = 0; height < heights.length; height++) {
            random_result = new SplittableRandom().nextInt(100, 300);
            heights[height] = random_result;
        }
    }

}

