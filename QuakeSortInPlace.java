
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/09/2019)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       int count =0;
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            count ++;
            if (checkInSortedOrder(in)) {
                break;
            } 
        }
       System.out.println("Pass needed " + count); 
    }
    // Returns index of max depth 
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from ) {
        int answer = from;
        double maxDepth = quakeData.get(from).getDepth();
        for(int k = from; k< quakeData.size();k++ ){
            if (quakeData.get(k).getDepth()> maxDepth){
                answer = k;
                maxDepth = quakeData.get(k).getDepth();
            }
        }
        return answer;
    }
    
    public void sortByLargestDepth (ArrayList<QuakeEntry> quakeData) {
        ArrayList<QuakeEntry> anwers = new ArrayList<QuakeEntry>  ();
        for (int k=0; k< quakeData.size(); k++) {
           int currLargest = getLargestDepth(quakeData, k );
           QuakeEntry in = quakeData.get(currLargest);
           QuakeEntry out = quakeData.get(k);
           quakeData.set(k,in);
           quakeData.set(currLargest,out);
        }
    }
    
    //BUBLE SORT 
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int k=0;k<quakeData.size()- numSorted-1;k++) {
            if(quakeData.get(k).getMagnitude()> quakeData.get(k+1).getMagnitude()){
                QuakeEntry qe1 = quakeData.get(k);
                QuakeEntry qe2 = quakeData.get(k+1);
                quakeData.set(k+1,qe1);
                quakeData.set(k,qe2);
            }
        }
    }
    //CHECKS IF THE ARRAY IS SORTED
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        for (int k=0;k<quakeData.size()-1;k++) {
            if((quakeData.get(k).getMagnitude()> quakeData.get(k+1).getMagnitude())){
                return false ;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSort (ArrayList<QuakeEntry> quakeData) {
        int count =0;
        for (int k=0;k<quakeData.size()-1;k++) {
            onePassBubbleSort(quakeData, k);           
            count++;
            if (checkInSortedOrder(quakeData)) {
                break;
            }            
            //System.out.println("Printing Quakes after pass " + k);
            //for (QuakeEntry qe:quakeData) { 
            //    System.out.println(qe);
            //}
        }
        System.out.println("Pass needed " + count);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth (list);
        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
