
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String currName;
    
    public MinMagFilter(double min, String filterName) { 
        magMin = min;
        currName = filterName;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName() {
        return currName;
    }

}
