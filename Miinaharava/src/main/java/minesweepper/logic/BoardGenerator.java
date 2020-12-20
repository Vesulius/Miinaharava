package minesweepper.logic;

import java.util.HashSet;
import java.util.Random;
import javafx.scene.layout.GridPane;
import minesweepper.ui.AppUi;
import minesweepper.ui.Tile;

public class BoardGenerator {

    private AppUi ui;
    private Tile[][] tiles;

    public BoardGenerator(AppUi ui) {
        this.ui = ui;
    }
    
    public GridPane generateBoard(int width, int heigth, int mines) {
        this.tiles = new Tile[heigth][width];
        GridPane grid = new GridPane();
        
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                if (tiles[y][x] == null) {
                    Tile tile = new Tile(0, this.ui);
                    tiles[y][x] = tile;
                }
                grid.add(this.tiles[y][x], x, y);
                this.addNeighbours(y, x, tiles[y][x]);
            }
        }
        
        for (String coordinate: this.generateMines(heigth, width, mines)) {
            String[] split = coordinate.split(":");
            int y = Integer.valueOf(split[0]); 
            int x = Integer.valueOf(split[1]); 
            tiles[y][x].isMine();
        }
        
        for (int y = 0; y < heigth; y++) {
            for (int x  = 0; x <  width; x++) {
                tiles[y][x].setTextToMine();
            }
        }
        return grid;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    
    
    // This method generates unique coordinates for each mine
    private HashSet<String> generateMines(int heigth, int width, int mines) {
        HashSet<String> set = new HashSet<>();
        Random random = new Random();
        int counter = mines;

        while (counter > 0) {
            int y = random.nextInt(heigth);
            int x = random.nextInt(width);

            if (set.add(y + ":" + x)) {
                counter--;
            }
        }
        return set;
    }

//    The point of this method is to mark all the Tiles that are next to mines
//    0 0 0
//    0 X 0   Zeros are the tiles that the tile X will try to add to the neighbours list 
//    0 0 0
//    It goes through each of the surrounding Tiles and sees if they exist (aka is x a border tile) adds them to the neighbours lists
    private void addNeighbours(int yCoord, int xCoord, Tile tile) {
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                if (y == 0 && x == 0) {
                    continue;
                }
                int yNeigh = yCoord + y;
                int xNeigh = xCoord + x;
                if (yNeigh >= 0 && xNeigh >= 0 && yNeigh < this.tiles.length && xNeigh < this.tiles[0].length) {
                    if (this.tiles[yNeigh][xNeigh] == null) {
                        this.tiles[yNeigh][xNeigh] = new Tile(0, this.ui);
                    }
                    tile.addNeighbour(this.tiles[yNeigh][xNeigh]);
                }
            }
        }
    }
}