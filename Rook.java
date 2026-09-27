/*subclass for Rook piece, inherits from superclass ChessPiece
verifies if target move aligns with chess rules of rook piece (any no. of
rows or columns (forward/back or left/right))
changelog:
 26/09 - Created class extending ChessPiece, created constructors
         using super, added verifyMove method by Overriding from
         superclass
 27/09 - Added comments
*/
public class Rook extends ChessPiece {
    //default constructor
    public Rook(){
        super(PieceType.ROOK.name(),"White",'A',1);
    }

    //constructor w/all attributes (not using enum for name since this constructor 
    //will be used by subclass)
    public Rook(String name,String color,char col,int row){
        super(name,color,col,row);
    }

    //Override ChessPiece verifyMethod for Rook (any dist left/right or
    //forward/back)
    @Override
    public boolean verifyMove(char targetCol,int targetRow){
        //check if Rooked moved / target move is at same position
        if(targetCol==getColumn() && targetRow==getRow()){
            return false;
        }
        //check if target move stays within rules (stayed in same col/row)
        return targetCol==getColumn() || targetRow==getRow();
    }
}