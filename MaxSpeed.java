/**The maxSpeed of a car*/
public enum MaxSpeed
{
    /**maxspeed Forward*/
    FORWARD(15, 5),
    /**Maxspeed Backward*/
    BACKWARD(-15, -5);

    /**Maxspeed onroad*/
    public final int onRoad;
    /**Maxspeed offroad*/
    public final int offRoad;

    /**
     * The maxspeed onroad {@param onRoad}
     * The maxspeed offroad {@param offRoad}
     */
    MaxSpeed(final int onRoad, final int offRoad) 
    {
	    this.onRoad = onRoad;
	    this.offRoad = offRoad;
    }
}