package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

public class King extends ChessPiece {
    public King(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'K';
        this.type = pieceTypes.King;
    };
    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        int numSpaces = 0;
        int[][] spaces = new int[numSpaces][2];
        int[][] newSpaces = new int[numSpaces][2];
        for(int xdir = -1; xdir < 2; xdir++){
            for(int ydir = -1; ydir < 2; ydir++){
                if(board.isOpenSpace(this,x + xdir, y + ydir)){
                    numSpaces++;
                    newSpaces = new int[numSpaces][2];
                    for(int i = 0; i < spaces.length; i++){
                        newSpaces[i] = spaces[i];
                    }
                    newSpaces[newSpaces.length-1] = new int[]{x + xdir, y + ydir};
                    spaces = newSpaces;
                }
            }
        }
        threatenedSpaces = newSpaces;
        threatenedSpaces = removeSelfFromThreatenedSpaces();
        return threatenedSpaces;
    }
}
