package chess;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 * A class that represents a rook
 * @author Jed Wang
 */
public class Rook extends AbstractChessPiece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> allLegalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition).getCharRepresentation().equals("R"))) throw new IllegalArgumentException("This isn\'t a rook!");
        LinkedList<String> output = new LinkedList<>();
        String temp;
        if(ChessBoard.isValidShift(currentPosition, 1, 0)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, 0);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, 1, 0);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, 0)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, 0);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, -1, 0);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 0, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 0, 1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, 0, 1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 0, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 0, -1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, 0, -1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        return allLegalMoves(cb, currentPosition);
    }

    @Override
    public String getCharRepresentation() {
        return "R";
    }
}