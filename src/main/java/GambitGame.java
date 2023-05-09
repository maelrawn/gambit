import board.ChessBoard;
import com.googlecode.lanterna.TerminalSize;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.*;
import graphics.Menu;

import input.InputHandler;

public class GambitGame {
    public void run(String[] args){
        try {
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
            defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(25, 12));
//            theoretically correct code for specifying a monospaced font, for some reason the terminal area = 0 if set
//            File fontfile = new File("/System/Library/Fonts/Supplemental/Andale Mono.ttf");
//            Font font = Font.createFont(Font.MONOSPACED, fontfile);
//            SwingTerminalFontConfiguration terminalfont = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING,font);
//            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(terminalfont);
            Screen screen = defaultTerminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            ChessBoard board = new ChessBoard(2, 2);
            Menu menu = new Menu();
            menu.draw(screen);
            board.generateSpawnedPiece(screen);
            InputHandler input = new InputHandler();
            playGame(board, menu, screen, input);
            printEndScreen(screen);
            screen.readInput();
            screen.stopScreen();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void playGame(ChessBoard board, Menu menu, Screen screen, InputHandler input) {
        int turncount = 0;
        while (board.getPlayer().isAlive()) {
            board.actorPathfind();
            board.draw(screen);
            try {
                screen.refresh();
            } catch (Exception e){
                e.printStackTrace();
            }
            handlePlayerTurn(board, menu, screen, input);
            turncount += 1;
            if (turncount % 2 == 0) {
                board.generateSpawnedPiece(screen);
            } else {
                board.spawnPiece(screen);
            }
        }
    }
    public void handlePlayerTurn(ChessBoard board, Menu menu, Screen screen, InputHandler input){
        boolean turnActionTaken = false;
        while(!turnActionTaken){
            turnActionTaken = input.processInput(input.getInput(screen), board, screen);
        }
        for (int i = 0; i < board.getPlayer().getHeldPieces().length - 1; i++) {
            menu.updateItemValue(board.getPlayer().getHeldPieces()[i], i);
            if(i > 2){
                menu.updateItemValue(board.getPlayer().getHeldPieces()[i+1], i);
            }
        }
        menu.draw(screen);
        board.actorMove();
    }
    public static void printEndScreen(Screen screen){
        screen.clear();
        TextGraphics render = screen.newTextGraphics();
        render.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        render.putString(8, 5, "GAME OVER");
        render.putString(6, 6, "press any key");
        try {
            screen.refresh();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        GambitGame game = new GambitGame();
        game.run(args);
    }
}
