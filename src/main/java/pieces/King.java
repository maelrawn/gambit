package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

public class King extends ChessPiece {
    private final char graphic = 'K';

    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        int numSpaces = 0;
        int[][] spaces = new int[numSpaces][2];
        int[][] newSpaces = new int[numSpaces][2];
        for(int xdir = -1; xdir < 2; xdir++){
            for(int ydir = -1; ydir < 2; ydir++){
                if(board.isOpenSpace(x + xdir, y + ydir)){
                    numSpaces++;
                    newSpaces = new int[numSpaces][2];
                    for(int i = 0; i < spaces.length; i++){
                        newSpaces[i] = spaces[i];
                    }
                    newSpaces[newSpaces.length] = new int[]{x + xdir, y + ydir};
                    spaces = newSpaces;
                }
            }
        }
        return newSpaces;
    }
}
