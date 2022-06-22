/** Stores the possible move for a a poly. */
public enum Move
{   /**Forward*/
    FORWARD(1, 0),
    /**Backward*/
    BACKWARD(-1, 0),
    /**ForwardLeft*/
    FORWARDLEFT(1, -5),
    /**ForwardRight*/
    FORWARDRIGHT(1, 5),
    /**BackwardLeft*/
    BACKWARDLEFT(-1, 5),
    /**BackwardRight*/
    BACKWARDRIGHT(-1, -5),
    /**Not moving*/
    PARKED(0, 0);

    /**Direction*/
    public final int direction;
    /**Turning*/
    public final int degree;

    /**
     * The direction of the car {@param direction}
     * The turning of the car {@param degree}
     */
    Move(final int direction, final int degree) 
    {
	    this.direction = direction;
	    this.degree = degree;
    }
}