package Flappy_Bird;

public class Poles implements variables {
    public int[][] map;

    Poles(int up, int down)
    {
        map = new int[up][down];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = 1;
            }
        }
    }
}
