package checkers;

import common.Board;
import java.util.LinkedList;

/**
 * A class that represents a king
 * @author Jed Wang
 */
public class KingChecker extends AbstractChecker {
    /**
     * A constructor for a new king
     * @param isWhite whether the checker is white / lighter
     */
    public KingChecker(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> allLegalMoves(CheckerBoard cb, String currentPosition) {
        if(!Board.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition).getCharRepresentation().equals("K"))) throw new IllegalArgumentException("This isn\'t a king!");
        LinkedList<String> output = new LinkedList<>();
        String shifted;
        if(Board.isValidShift(currentPosition, 1, -1)) {
            shifted = Board.shiftSquare(currentPosition, 1, -1);
            if (cb.isEmptySquare(shifted)) {
                output.add(shifted);
            }
        }
        if(Board.isValidShift(currentPosition, -1, -1)) {
            shifted = Board.shiftSquare(currentPosition, -1, -1);
            if (cb.isEmptySquare(shifted)) {
                output.add(shifted);
            }
        }
        if(Board.isValidShift(currentPosition, 1, 1)) {
            shifted = Board.shiftSquare(currentPosition, 1, 1);
            if (cb.isEmptySquare(shifted)) {
                output.add(shifted);
            }
        }
        if(Board.isValidShift(currentPosition, -1, 1)) {
            shifted = Board.shiftSquare(currentPosition, -1, 1);
            if (cb.isEmptySquare(shifted)) {
                output.add(shifted);
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(CheckerBoard cb, String currentPosition) {
        if(!Board.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition).getCharRepresentation().equals("K"))) throw new IllegalArgumentException("This isn\'t a checker!");
        LinkedList<String> output = new LinkedList<>();
        String halfShifted, fullShifted;
        if(Board.isValidShift(currentPosition, 2, -2)) {
            halfShifted = Board.shiftSquare(currentPosition, 1, -1);
            fullShifted = Board.shiftSquare(currentPosition, 2, -2);
            if(cb.isEmptySquare(fullShifted) && !cb.isEmptySquare(halfShifted)) {
                if(cb.getPiece(halfShifted).isWhite ^ isWhite) {
                    output.add(fullShifted);
                }
            }
        }
        if(Board.isValidShift(currentPosition, -2, -2)) {
            halfShifted = Board.shiftSquare(currentPosition, -1, -1);
            fullShifted = Board.shiftSquare(currentPosition, -2, -2);
            if(cb.isEmptySquare(fullShifted) && !cb.isEmptySquare(halfShifted)) {
                if(cb.getPiece(halfShifted).isWhite ^ isWhite) {
                    output.add(fullShifted);
                }
            }
        }
        if(Board.isValidShift(currentPosition, 2, 2)) {
            halfShifted = Board.shiftSquare(currentPosition, 1, 1);
            fullShifted = Board.shiftSquare(currentPosition, 2, 2);
            if(cb.isEmptySquare(fullShifted) && !cb.isEmptySquare(halfShifted)) {
                if(cb.getPiece(halfShifted).isWhite ^ isWhite) {
                    output.add(fullShifted);
                }
            }
        }
        if(Board.isValidShift(currentPosition, -2, 2)) {
            halfShifted = Board.shiftSquare(currentPosition, -1, 1);
            fullShifted = Board.shiftSquare(currentPosition, -2, 2);
            if(cb.isEmptySquare(fullShifted) && !cb.isEmptySquare(halfShifted)) {
                if(cb.getPiece(halfShifted).isWhite ^ isWhite) {
                    output.add(fullShifted);
                }
            }
        }
        return output;
    }

    @Override
    public String getCharRepresentation() {
        return "K";
    }
}