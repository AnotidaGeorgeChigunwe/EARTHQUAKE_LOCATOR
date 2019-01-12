
/**
 * Write a description of interface Filter here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01.06.2018)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
    
    public String getName ();
}
