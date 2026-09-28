/*subclass for Queen piece, inherits from superclass Rook
verifies if target move aligns with chess rules of Queen piece (as a rook or bishop)
changelog:
 26/09 - Created class extending Rook, created constructors
         using super, added verifyMove method by Overriding from
         superclass, added temporary code for verifyMove method
 27/09 - Corrected verifyMethod by implementing super.verifyMove to use
         the superclass' move set, added comments
*/
public class Queen extends Rook{
    //default constructor
    public Queen(){
        super(PieceType.QUEEN.name(),"White",'A',1);
    }
    //constructor w/all attributes (not using enum for name since this constructor will be
    //used by subclass)
    public Queen(String name,String color,char col,int row){
        super(name,color,col,row);
    }

    //override verifyMove from Rook to fit move rules for Queen
    @Override
    public boolean verifyMove(char targetCol,int targetRow){
        //use rook's verifyMove method
        boolean rookMove=super.verifyMove(targetCol, targetRow);

        //using temporary Bishop to verify diagonal movements
        Bishop temp=new Bishop(getPieceName(),getColor(),getColumn(),getRow());
        boolean bishopMove=temp.verifyMove(targetCol, targetRow);

        //check if either movement as rook or bishop are true
        return bishopMove || rookMove;
    }
}
