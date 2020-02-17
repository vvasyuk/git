package com.tryout.DailyCodingProblems.p26;

import java.util.stream.IntStream;

public class n267_chess_check {
    public static void main(String[] args) {
        char[][] board = new char[8][8];
        IntStream.range(0,8).forEach(y->{
            IntStream.range(0,8).forEach(x->{
                board[x][y]='.';
            });
        });
        board[0][3] = 'k';
        board[2][1] = 'b';
        board[3][6] = 'p';
        board[4][7] = 'r';
        board[5][2] = 'n';
        board[7][5] = 'q';
        ifCheck(0,3, board);
    }

    private static void ifCheck(int x, int y, char[][] board) {
        checkResult(traverse(x, y, board, 0, -1), "straighnt");
        checkResult(traverse(x, y, board, 0, 1), "straighnt");
        checkResult(traverse(x, y, board, 1, 0), "straighnt");
        checkResult(traverse(x, y, board, 1, -1), "diag");
        checkResult((traverse(x, y, board, 1, 1)), "diag");
    }

    private static void checkResult(FigurePos figurePos, String way) {
        if (figurePos.c!='.'){
            System.out.println("intersecting " + way + " with :" + figurePos.toString());
            if (figurePos.c=='b') System.out.println("check with :" + figurePos.toString());
            if (figurePos.c=='p' && figurePos.x-0==1) System.out.println("check with :" + figurePos.toString());
        }
    }

    private static FigurePos traverse(int kX, int kY, char[][] board, int dirX, int dirY) {
        int resX = -1;
        int resY = -1;
        char c = '.';

        for (int x = kX+dirX, y = kY+dirY; x > -1 && x < 8 && y > -1 && y < 8; x+=dirX, y+=dirY) {
            //System.out.print(board[x][y]);
            if (board[x][y]!='.'){
                c = board[x][y];
                resX =x;
                resY =y;
                break;
            }
        }
        //System.out.println();
        return new FigurePos(c, resX, resY);
    }

    public static class FigurePos{
        Character c;
        int x, y;

        public FigurePos(Character c, int x, int y) {
            this.c = c;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "FigurePos{" +
                    "c=" + c +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
