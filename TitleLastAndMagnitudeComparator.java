import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] n1 = (q1.getInfo()).split(" ");
        String[] n2 = (q2.getInfo()).split(" ");
        String lastWord1 = n1[n1.length-1]; 
        String lastWord2 = n2[n2.length-1]; 
        if (lastWord1.compareTo(lastWord2)==0) {
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return lastWord1.compareTo(lastWord2);
    }
}
