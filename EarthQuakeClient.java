/**
 * EarthQuakeClient
 * 
 * @Anotida George Chigunwe
 * @version 1.0, January 2016
 */
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        //Auto-generated constructor stub
    }
    
    /*This method returns an ArrayList of type QuakeEntry of all the 
     earthquakes from quakeData that have a magnitude larger than magMin.*/  
     
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData ) {
            if (qe.getMagnitude()>magMin) {
               answer.add(qe); 
            }
        }
        return answer;
    }

    /*This method returns an ArrayList of type QuakeEntry of all the 
    earthquakes from quakeData that have a distance less than distMax from Location from.*/
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMaxKiloMetres,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        double distMaxMetres = distMaxKiloMetres*1000; //convert to meters 
        for (QuakeEntry qe : quakeData ) {
            Location currLocation = qe.getLocation();
            double currDistance = currLocation.distanceTo(from);
            //System.out.println("curr distance : " + currDistance);
            if (currDistance < distMaxMetres) {
               answer.add(qe); 
            }
        }
        return answer;
    }
    
    
    
    /*This method returns an ArrayList of type QuakeEntry of all the earthquakes from quakeData 
     * whose depth is between minDepth and maxDepth, exclusive.*/
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
        double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData ) {
            double currDepth = qe.getDepth();
            //System.out.println("curr distance : " + currDistance);
            if (currDepth < maxDepth && currDepth > minDepth) {
               answer.add(qe); 
            }
        }
        return answer;
    }
    
    /*Returns an ArrayList of type QuakeEntry of all the earthquakes from quakeData
     * whose titles have the given phrase found at location where (“start” means the phrase must 
     * start the title, “end” means the phrase must end the title and “any” 
     * means the phrase is a substring anywhere in the title.*/
    public  ArrayList<QuakeEntry> filterByPhrase( ArrayList<QuakeEntry> quakeData, 
        String  where, String  phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (phrase.equals("start")) {
            for (QuakeEntry qe : quakeData) {        
                String currTitle = qe.getInfo();
                if (currTitle.startsWith(where)) {
                    answer.add(qe); 
                }   
             } 
         }
        if (phrase.equals("end")) {
            for (QuakeEntry qe : quakeData) {
                String currTitle = qe.getInfo();
                if (currTitle.endsWith(where)) {
                    answer.add(qe); 
                }   
             }   
        } 
        if (phrase.equals("any")) {
            for (QuakeEntry qe : quakeData) {
                String currTitle = qe.getInfo();
                if (currTitle.contains(where)) {
                    answer.add(qe); 
                }   
             }  
        }       
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    /*This method prints information of earthquakes that have a magnitude bigger than double minMagnitude */ 
    public void bigQuakes(double minMagnitude) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> byMagnitudeList = filterByMagnitude(list,minMagnitude);
        for (QuakeEntry qe : byMagnitudeList ){
            System.out.println(qe);
        }
        System.out.println("Found "+byMagnitudeList.size()+" quakes that match that criteria");
    }
    
    
    /*This method prints information of earthquakes that are less than double distMax for Location*/ 
    public void closeToMe(double distMax){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";       
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> found  = new ArrayList<QuakeEntry> ();
        System.out.println("read data for "+ list.size() +" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> byDistList = filterByDistanceFrom(list,distMax,city);
        for (QuakeEntry qe : byDistList ){
            System.out.println(qe);
        }
        System.out.println("Found "+byDistList.size()+" quakes that match that criteria");
    }
    
    //This methods prints out all details of earthquakes from a data source whose depth is between a given minimum and maximum value.
    public void quakesOfDepth(double minDepth, double maxDepth ){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";       
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> found  = new ArrayList<QuakeEntry> ();
        System.out.println("read data for "+ list.size() +" quakes");
        
        ArrayList<QuakeEntry> byDistList = filterByDepth(list,minDepth,maxDepth);
        for (QuakeEntry qe : byDistList ){
            System.out.println(qe);
        }
        System.out.println("Found "+byDistList.size()+" quakes that match that criteria");
    }
    
    //This method prints all the earthquakes from a data source that have phrase in their title in a given position in the title.
    public void quakesByPhrase(String where, String phrase){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";       
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> found  = new ArrayList<QuakeEntry> ();
        System.out.println("read data for "+ list.size() +" quakes");
        
        ArrayList<QuakeEntry> byDistList = filterByPhrase(list,where,phrase);
        for (QuakeEntry qe : byDistList ){
            System.out.println(qe);
        }
        System.out.println("Found "+byDistList.size()+" quakes that match that criteria");        
    }
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
