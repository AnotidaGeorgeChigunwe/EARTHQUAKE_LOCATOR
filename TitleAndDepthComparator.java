
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (Anotida G Chuginwe ) 
 * @version (01/12/2019)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> { 
 public int compare(QuakeEntry q1, QuakeEntry q2) {
     if (q1.getInfo().compareTo(q2.getInfo())==0) {
         return Double.compare(q1.getDepth(),q2.getDepth());
     }
     return q1.getInfo().compareTo(q2.getInfo());
 }
}
