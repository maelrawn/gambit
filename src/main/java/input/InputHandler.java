package input;

import board.ChessBoard;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import cursor.Cursor;
import pieces.Player;

import java.util.ArrayList;

public class InputHandler {
    public static Character getInput(Screen screen){
        Character result = null;
        try {
            result = screen.readInput().getCharacter();
        } catch ( Exception e ){
            System.out.println(e);
        }
        return result;
    }
    public static boolean processInput( Character input, Cursor cursor,
                                        ChessBoard board, Player player){
        switch (input){
            case 'w':
                //return cursormovement(up)
                break;
            case 'a':
                //return cursormovement(left)
                break;
            case 's':
                //return cursormovement(down)
                break;
            case 'd':
                //return cursormovement(right)
                break;
            case 'q':
                //return player.switchpiece(queen)
                break;
            case 'n':
                //return player.switchpiece(knight)
                break;
            case 'k':
                //return player.switchpiece(king)
                break;
            case 'r':
                //return player.switchpiece(king)
                break;
            case 'p':
                //return player.switchpiece(pawn)
                break;
            case 'b':
                //return player.switchpiece(bishop)
                break;
            case '\n':
                //return board.makemove(cursor.getposition)
                break;
        }
        return false;
    }
}
