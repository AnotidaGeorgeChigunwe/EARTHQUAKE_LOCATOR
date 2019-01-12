
/**
 * Write a description of DepthFilter here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/08/2019)
 */
public class DepthFilter implements Filter {
    
    private double minDep;
    private double maxDep;
    private String currName;
    
    public DepthFilter (double min, double max, String filterName) {
        minDep = min;
        maxDep = max;
        currName = filterName;
    }
    
    public boolean satisfies( QuakeEntry qe) {
        return  qe.getDepth() >= minDep && qe.getDepth() <= maxDep;
    }
    
    public String getName () {
        return currName;
    };
}
