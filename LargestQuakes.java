
/**
 * LargestQuakes
 * 
 * @Anotida George Chigunwe
 * @version 1.0, January 2016
 */
import java.util.*;
import edu.duke.*;

public class LargestQuakes {

    public void findLargestQuakes () {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";       
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+ list.size() +" quakes");
        //int indexOfLargest =  indexOfLargest(list);
        //System.out.println("The largest index is  "+ indexOfLargest);
        ArrayList<QuakeEntry> getLargestOfHowMany =  getLargest(list, 5);
                for (QuakeEntry qe : getLargestOfHowMany ){
            System.out.println(qe);
        }
        System.out.println("Found "+getLargestOfHowMany.size()+" quakes that match that criteria");
    }
    
    // This returns the index of the QuakeEntry with the largest magnitude
    public int indexOfLargest (ArrayList<QuakeEntry> quakeData) {
        int indexOfLargest = 0;
        for (int k = 0;k< quakeData.size(); k++) {
            if (quakeData.get(k).getMagnitude() >  
                                quakeData.get(indexOfLargest).getMagnitude()){
                indexOfLargest = k;
            }
        }
        return indexOfLargest;
    }
    //This method returns an ArrayList of type QuakeEntry of the top howMany largest magnitude 
    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        if (howMany> quakeData.size()){
            howMany = quakeData.size();
        }
        for (int k=0;k<howMany; k++){
            int maxIndex = indexOfLargest(copy);
            answer.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return answer; 
    }
}
