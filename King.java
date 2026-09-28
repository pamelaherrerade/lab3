/*subclass for King piece, inherits from superclass Queen
verifies if target move aligns with chess rules of King piece (as a Queen, can
only move by one square)
changelog:
 26/09 - Created class extending Queen, created constructors
         using super, added verifyMove method by Overriding from
         superclass, added temporary code for verifyMove method
 27/09 - Corrected verifyMethod by implementing super.verifyMove to use
         the superclass' move set, added comments
*/
public class King extends Queen {
    //default constructor
    public King(){
        super(PieceType.KING.name(),"White",'A',1);
    }

    //constructor w/all attributes
    public King(String name,String color,char column,int row){
        super(name,color,column,row);
    }

    
    @Override
    public boolean verifyMove(char targetColumn,int targetRow){
        //using queen's verifyMove method since King also has same-ish ruless
        boolean queenMove=super.verifyMove(targetColumn, targetRow);
        int colDiff=Math.abs(targetColumn - this.getColumn());
        int rowDiff=Math.abs(targetRow-this.getRow());

        //gets checked in bishop method, can be removed
        if(colDiff==0 && rowDiff==0){
            return false;
        }

        //return true/false if the queen movement is valid AND the king moves only
        //one square
        return queenMove && (colDiff <=1 && rowDiff <=1);
    }
}
