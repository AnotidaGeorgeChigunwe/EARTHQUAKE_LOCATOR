import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    
    /*This method returns an ArrayList of type QuakeEntry of all the earthquakes from quakeData 
     * that satisfie the filter.*/
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        //System.out.println("read data for "+list.size()+" quakes");
        
        //Filter magnitude & depth 
        
        Filter f = new MagnitudeFilter(3.5,4.5, "ano"); 
        Filter y = new DepthFilter(-55000,-20000, "ano");
        
        //Filter Distance & Location
        Location japan = new Location (39.7392, -104.9903);
        //Filter f = new DistanceFilter(japan,1000,"Ano"); 
        //Filter y = new PhraseFilter("a","end","Ano");
        ArrayList<QuakeEntry> m7  = filter(list, f);
        m7  = filter(m7, y);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+m7.size()+" quakes that match criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    public void testMatchAllFilter () {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes"); 
        
        MatchAllFilter maf = new MatchAllFilter();
        
        //Filters Tests       
        Location japan = new Location (55.7308, 9.1153);
        Filter x = new DistanceFilter(japan,3000, "japanDistanceFilter"); 
        Filter z = new PhraseFilter("e","any","JapanNameFilter");
        Filter ff = new MagnitudeFilter(1,4,"FilterMag2");
        maf.addFilters(x);
        maf.addFilters(z);
        maf.addFilters(ff);
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        System.out.println("Found (11) "+filtered.size()+" quakes that match criteria");
        
        
        //Filters Tests 
        Filter f = new MagnitudeFilter(1,4,"FilterMag2");
        Filter y = new DepthFilter(-180000.0,-30000.0,"FilterDepth2");
        Filter yy = new PhraseFilter("o","any","oNameFilter");
        MatchAllFilter max = new MatchAllFilter();
        max.addFilters(f);
        max.addFilters(y);
        max.addFilters(yy);
        ArrayList<QuakeEntry> filtered2 = filter(list, max);
        System.out.println("Found (12)"+filtered2.size()+" quakes that match criteria");
        
        //Filters Tests 
        Filter a = new MagnitudeFilter(0,2,"FilterMag3");
        Filter b = new DepthFilter(-100000.0,-10000.0,"FilterDepth3");
        Filter c = new PhraseFilter("a","any","aNameFilter");;
        MatchAllFilter mas = new MatchAllFilter();
        mas.addFilters(a);
        mas.addFilters(b);
        mas.addFilters(c);
        ArrayList<QuakeEntry> filtered3 = filter(list, mas);
        System.out.println("Found "+filtered3.size()+" quakes that match criteria");
        
        //Filters Tests 
        Filter d = new MagnitudeFilter(0,3,"FilterMag3");
        Location Oklahoma = new Location (36.1314, -95.9372);
        Filter m = new DistanceFilter(Oklahoma,10000, "OklahomaDistanceFilter");
        Filter n = new PhraseFilter("Ca","any","OklahomaNameFilter");;
        MatchAllFilter mak = new MatchAllFilter();
        mak.addFilters(d);
        mak.addFilters(m);
        mak.addFilters(n);
        ArrayList<QuakeEntry> filtered4 = filter(list, mak);
        System.out.println("Found "+filtered4.size()+" quakes that match criteria");
    }
 }
