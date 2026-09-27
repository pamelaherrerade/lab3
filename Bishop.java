/*subclass for Bishop piece, inherits from superclass ChessPiece
verifies if target move aligns with chess rules of Bishop piece (diagonally)
changelog:
 26/09 - Created class extending ChessPiece, created constructors
         using super, added verifyMove method by Overriding from
         superclass
 27/09 - Added comments
*/
public class Bishop extends ChessPiece {
    //default constructor
    public Bishop(){
        super(PieceType.BISHOP.name(),"White",'A',1);
    }
    //constructor w/all attributes. can use enum type for name
    public Bishop(String name,String color,char col,int row){
        super(name,color,col,row);
    }

    //override verifyMove to use Bishop's movement rules
    @Override
    public boolean verifyMove(char targetCol,int targetRow){
        //get abs value of differences between target move and original position
        int colDiff=Math.abs(targetCol-getColumn());
        int rowDiff=Math.abs(targetRow-getRow());

        //check if piece moves / target position is the same as original
        if(colDiff==0 && rowDiff==0){
            return false;
        }

        //check if the number of squares of col and row are the same (diagonal movement)
        return colDiff==rowDiff;
    }
}
