// Pawn class inherits from ChessPiece

public class Pawn extends ChessPiece{
    // Default Constructor
    public Pawn(){
        super(PieceType.PAWN.name(), "White", 'A', 1);
    }

    // Constructor with parameters: color, column, row
    public Pawn(String name, String color, char column, int row){
        super(name, color, column, row);
    }

    // Verify move method changed to Pawn specifically
    @Override 
    public boolean verifyMove(char targetColumn, int targetRow){
        if(Character.toUpperCase(targetColumn) != Character.toUpperCase(getColumn())){
            return false;
        }
        if(getColor().equalsIgnoreCase("White")){
            return targetRow == getRow() + 1;
        } else if(getColor().equalsIgnoreCase("Black")){
            return targetRow == getRow() - 1;
            }
        return false;
        }
}