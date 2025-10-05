public class Maps {
    
    /*
        Tiles
        @ - chip starting point
        X - wall tile
        # - chips
        ' ' - blank tile
        W - water tile
        F - fire tile
        ^ - force up tile
        < - force left tile
        > - force right tile
        v - force down tile
        E - exit tile
        
        Doors
        R - red door
        B - blue door 

        Items
        r - red key
        b - blue key
        _ - flippers
        L - fire boots
        
    */

    private char[][] map;
    private int chips;

    public Maps(char[][] map, int chips) {
        this.map = map;
        this.chips = chips;
    }
    
    // doors and keys only
    public static Maps generateMap1() {
        char[][] grid = {
            {'X','X','X','X','X','X','X','X','X'},
            {'X',' ','b','X','E','X','#',' ','X'},
            {'X','R','X','X',' ','X','X','B','X'},
            {'X',' ',' ',' ',' ',' ',' ',' ','X'},
            {'X',' ',' ',' ','@','#',' ',' ','X'},
            {'X','#','X','X','X','X','X','#','X'},
            {'X',' ','X',' ','r',' ','X',' ','X'},
            {'X',' ',' ',' ','X',' ',' ',' ','X'},
            {'X','X','X','X','X','X','X','X','X'}
        };

        int chips = countChips(grid);

        return new Maps(grid, chips);
    }

    // special tiles and items only
    public static Maps generateMap2() {
        char[][] grid = {
            {'X','X','X','X','X','X','X','X','X'},
            {'X','E','F','F','v','W','W','L','X'},
            {'X','F','F','F','v','W','X','X','X'},
            {'X','F','F','F','v','W','W','W','X'},
            {'X','#','<','<','#','X','X','#','X'},
            {'X','>','v','^','v','W','W','W','X'},
            {'X','^','#','^','v','W','X','X','X'},
            {'X','^','<','<','<','W','_','@','X'},
            {'X','X','X','X','X','X','X','X','X'}
        };

        int chips = countChips(grid);

        return new Maps(grid, chips);
    }

    // combination of both
    public static Maps generateMap3() {
        char[][] grid = {
            {'X','X','X','X','X','X','X','X','X','X','X'},
            {'X',' ',' ',' ',' ',' ',' ',' ','#','@','X'},
            {'X','v','X','X','X','^','X','X','X','B','X'},           
            {'X','v','X',' ','F',' ',' ','X','#',' ','X'},
            {'X','v','X','F','X','X',' ',' ','X',' ','X'},
            {'X','v','X','F','R','E','X','_','X',' ','X'},
            {'X','v','X','F','X','X','X','X','#',' ','X'},
            {'X','v',' ',' ','W','W','W',' ','X',' ','X'},
            {'X','X',' ','X','X','W','X','^','X',' ','X'},
            {'X','L',' ','#','X','b','X','^','#','r','X'},
            {'X','X','X','X','X','X','X','X','X','X','X'}
        };

        int chips = countChips(grid);

        return new Maps(grid, chips);
    }

    private static int countChips(char[][] map) {
        int count = 0;
        for (int y = 0; y < map.length; y++) {          
            for (int x = 0; x < map[y].length; x++) {   
                char tile = map[y][x];
                if (tile == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}
