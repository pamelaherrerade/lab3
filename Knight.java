/*subclass for Knight piece, inherits from superclass ChessPiece
verifies if target move aligns with chess rules of knight piece (2 columns forwards/back
and 1 row left/right or 1 column forward/back and 2 rows left/right)
changelog:
 26/09 - Created class extending ChessPiece, created constructors
         using super, added verifyMove method by Overriding from
         superclass
*/

public class Knight extends ChessPiece{
    public Knight(){
        super(PieceType.KNIGHT.name(),"White",'A',1);
    }
    public Knight(String name, String color,char col,int row){
        super(name,color,col,row);
    }

    @Override
    public boolean verifyMove(char targetCol,int targetRow){
        int colDiff=Math.abs(targetCol-getColumn());
        int rowDiff=Math.abs(targetRow-getRow());

        if(colDiff==0 && rowDiff==0){
            return false;
        }
        return ((colDiff==2 && rowDiff==1) || (colDiff==1 && rowDiff==2));
    }
}
