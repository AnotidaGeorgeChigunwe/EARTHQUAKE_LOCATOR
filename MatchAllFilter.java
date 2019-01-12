
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/08/2019)
 */
import java.util.*;
import edu.duke.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters = new ArrayList<Filter> ();
    }
    
    public void addFilters (Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter f : filters){
            if(!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName () {
        String name = "";
        for (Filter fil : filters){
            String currName = fil.getName();
            name = name + " " + currName;
        }
        return name;
    }
}
