class RepaintTheBoard2 implements Runnable{

    GameBoard theBoard;

    public RepaintTheBoard2(GameBoard theBoard){
        this.theBoard = theBoard;
    }

    @Override
    public void run() {

        theBoard.repaint();

    }

}
