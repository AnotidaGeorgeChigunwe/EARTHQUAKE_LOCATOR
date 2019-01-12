
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/08/2019)
 */
public class DistanceFilter implements Filter {
    private Location currLoc;
    private double dis;
    private String currName;
    
    public DistanceFilter(Location curr, double distance, String name ){
        currLoc = curr;
        dis = distance*1000;
        currName = name;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        return currLoc.distanceTo(qe.getLocation())< dis;
    }
    
    public String getName () {
        return currName;
    };
}
