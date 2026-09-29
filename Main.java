/*
Author(s): Andres Iglesias, Diana Islava Rauda, Pamela Herrera
Lab 3 - Chess Pieces implementing Hierarchy and Polymorphism. Program will  instantiate six unique chess pieces, validate positions to verify they are within chessboard limits, and executes move verification in a polymorphic way across pieces.
Changelog:
09/23: Created GitHub repository with readme file and assigned tasks, including attributes and methods signatures, output format requirements, and git workflow.
09/25: Created ChessPiece, PieceType, Chessboard and Pawn files.
09/26: Created Knight, Bishop, Rook, Queen, and King files.
09:26: Created Main file.
 */

import java.util.Scanner;

public class Main{
    // Attributes for everything
    static Chessboard chessboard = new Chessboard();

    static String pieceName;
    static String color;
    static char column;
    static int row;

    static char targetCol;
    static int targetRow;

    public static void main(String[] args) {

        
        Scanner input = new Scanner(System.in);

        // Create an array to hold six chess pieces
        ChessPiece[] pieces = new ChessPiece[6];

        //Creating a boolean list to keep track of used pieces
        boolean[] usedPieces = new boolean[PieceType.values().length];

        System.out.println("Please enter the attributes of your piece in the following format: piece type, color, column(A-H), row(1-8) ");
        System.out.println("NO DUPLICATE PIECES ALLOWED");

        for (int i = 0; i < pieces.length; i++) {
            // the loop to create six chess pieces
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Enter piece information: " + (i + 1) + ": ");
                    System.out.println("Example: QUEEN, WHITE, A, 1");
                    
                    String pieceInfo = input.nextLine();
                    String[] pieceInfoParts = pieceInfo.split(",");
                    
                    pieceName = pieceInfoParts[0].trim().toUpperCase();
                    color = pieceInfoParts[1].trim().toUpperCase();
                    column = pieceInfoParts[2].trim().toUpperCase().charAt(0);
                    row = Integer.parseInt(pieceInfoParts[3].trim());
                    
                    // Check piece name and color
                    verifyInput(input);
                    
                    PieceType pieceType = PieceType.valueOf(pieceName);

                    // Check if this piece was already entered by comparing odinals values of the enum types
                    if (usedPieces[pieceType.ordinal()]) {
                        System.out.println("That chess piece was already entered.");
                        System.out.println("Please enter a different piece.");
                        continue;
                    }

                    // Check original position
                    verifyPosition(input);

                    // Create the correct piece and put it in the array
                    if (pieceType == PieceType.PAWN) {
                        pieces[i] = new Pawn(pieceName, color, column, row);
                    }

                    else if (pieceType == PieceType.ROOK) {

                        pieces[i] = new Rook(pieceName, color, column, row);
                    }

                    else if (pieceType == PieceType.KNIGHT) {

                        pieces[i] = new Knight(pieceName, color, column, row);
                    }

                    else if (pieceType == PieceType.BISHOP) {

                        pieces[i] = new Bishop(pieceName, color, column, row);
                    }

                    else if (pieceType == PieceType.QUEEN) {

                        pieces[i] = new Queen(pieceName, color, column, row);
                    }

                    else if (pieceType == PieceType.KING) {

                        pieces[i] = new King(pieceName, color, column, row);
                    }
                    
                    // Mark this piece as already used
                    usedPieces[pieceType.ordinal()] = true;

                    // Everything worked
                    validInput = true;

                } catch (Exception e) {


                    System.out.println("Invalid input. Please try again.");

                }
            }
        }

        // Now we ask the user for target position after all 6 pieces are created
        boolean validTarget = false;

        while (!validTarget) {

            try {

                System.out.println();
                System.out.println("Please enter the target position!!");
                System.out.println("Example: D, 5 (in the exact format)");

                String targetPosition = input.nextLine();
                String[] targetParts = targetPosition.split(",");

                targetCol = targetParts[0].trim().toUpperCase().charAt(0);
                targetRow = Integer.parseInt(targetParts[1].trim());

                // Check if target position is inside chessboard
                verifyTargetPosition(input);

                validTarget = true;

            } catch (Exception e) {

                System.out.println("Invalid input. Please try again.");
            }
        }


        // Traverse the array and check every piece
        System.out.println();
        System.out.println("Results:");

        for (ChessPiece piece : pieces) {

            boolean isValidMove = piece.verifyMove(targetCol, targetRow);

            String result;

            if (isValidMove) {
                result = " can move to ";
            } else {
                result = " can NOT move to ";
            }

            System.out.println("The " + piece.getPieceName() + " at " + piece.getColumn() + "," + piece.getRow() + result + targetCol + "," + targetRow);
            
        }

        input.close();
        
    }
    //METHODSS 

    // Check if piece name exists in PieceType
    public static boolean verifyName(){

        boolean validPiece;

        for (PieceType piece : PieceType.values()) {

            validPiece = piece.name().equalsIgnoreCase(pieceName);

            if (validPiece) {

                return true;
            }
        }

        return false;
    }


    // Check piece name and color
    public static void verifyInput(Scanner input){

        // Check if the color is valid
        boolean verifyColor = color.equalsIgnoreCase("BLACK") || color.equalsIgnoreCase("WHITE");

        // verify if the piece name is valid, using the prior method.
        boolean verifyName = verifyName();

        while (!(verifyColor && verifyName)) {

            // We ask the user to re-enter the piece name and color until both are valid.
            if (!verifyName) {

                System.out.print("Please enter a valid chess piece name: ");

                pieceName = input.nextLine().trim().toUpperCase();

                // Check again if the piece name is valid after the user re-enters it.
                verifyName = verifyName();
            }

            if (!verifyColor) {

                // We ask for the colors.
                System.out.print("Please enter a valid chess piece color: ");

                color = input.nextLine().trim().toUpperCase();

                // We check again if the color is valid after the user re-enters it.
                verifyColor = color.equalsIgnoreCase("BLACK") || color.equalsIgnoreCase("WHITE");
            }
        }
    }


    // Check original piece position
    public static void verifyPosition(Scanner input){

        // Check if the original piece position is within the chessboard which is 8x8.
        boolean withinBoard = Chessboard.withinChessboard(column, row);

        while (!withinBoard) {
            // If false, we ask them to re-enter the original piece position.
            System.out.println("Please enter a coordinate within the chessboard (A-H,1-8)");

            String coordinate = input.nextLine();
            String[] coordinateParts = coordinate.split(",");

            column = coordinateParts[0].trim().toUpperCase().charAt(0);
            row = Integer.parseInt(coordinateParts[1].trim());

            withinBoard = Chessboard.withinChessboard(column, row);
        }
    }


    // Check target position
    public static void verifyTargetPosition(Scanner input){

        boolean withinBoard = Chessboard.withinChessboard(targetCol, targetRow);

        // Check if the target position is within the chessboard which is 8x8.
        while (!withinBoard) {

            System.out.println("Please enter a target coordinate within the chessboard (A-H,1-8)");

            String targetCoordinate = input.nextLine();
            String[] targetParts = targetCoordinate.split(",");

            targetCol = targetParts[0].trim().toUpperCase().charAt(0);
            targetRow = Integer.parseInt(targetParts[1].trim());

            withinBoard = Chessboard.withinChessboard(targetCol, targetRow);
        }
    }
}