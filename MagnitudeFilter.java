
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/08/2019)
 */
public class MagnitudeFilter implements Filter {
    
    private double minMag;
    private double maxMag;
    private String currName;
    
    public MagnitudeFilter (double min, double max, String filterName) { 
        minMag = min;
        maxMag = max;
        currName = filterName;
    } 
    

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag; 
    } 
    
    public String getName () {
     return currName;
    };
    
}
