
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String phr;
    private String name;
    private String currName;
    
    public PhraseFilter (String title, String phrase, String filterName ) {
        phr = phrase ;
        name = title; 
        currName = filterName;
    }
    public boolean satisfies (QuakeEntry qe) {
        
        if(phr == "start") {
            return qe.getInfo().startsWith(name);
        }
        if(phr == "end") {
            return qe.getInfo().endsWith(name);
        }
        if(phr == "any") {
            return qe.getInfo().contains(name);
        }
        return false;
    }
    
    public String getName () {
        return currName;
    };
}
