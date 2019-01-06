
/**
 * Find N-closest quakes
 * @author Anotida George Chigunwe
 * @author Duke Software/Learn to Program (findClosestQuakes method)
 * 
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    
    //This method returns the closest howMany earthquakes to the current Location and return them in an ArrayList of type QuakeEntry.
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;  

        for (int k =0;k< howMany; k++ ){
            int index = 0;
            double dis = Double.POSITIVE_INFINITY;
            for (QuakeEntry qe : copy ){
                Location currLocation = qe.getLocation();
                double currDistance = currLocation.distanceTo(current);
                //System.out.println("currDistance" + currDistance);
                if (currLocation.distanceTo(current)< dis){
                    index = copy.indexOf(qe);
                    dis = currDistance;                    
                }
            }
            //System.out.println("Lowest so far is " + dis);
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
