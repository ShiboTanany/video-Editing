
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s.ibrahim
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
                String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/s.ibrahim/Downloads/videos_cut.csv"));
        String line = br.readLine();
       while ((line = br.readLine()) !=null){
             String[] b = line.split(splitBy);
             String link=b[0];
             Long  start=Long.parseLong(b[1])*1000;
             String end=b[5];
             Long endMinute=Long.parseLong(end.split(":")[1])*60*1000;
             Long endSeconds=Long.parseLong(end.split(":")[2])*1000;
           getEditedFile(link, start,endMinute+endSeconds);
            
        }
        br.close();

  }
    
      public static  void getEditedFile(String url ,Long start,Long end){
    URL website;
        try {
            website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    	FileOutputStream fos = new FileOutputStream(url.split("/")[9]+"shibo.mp4");
    	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        
        TrimVideoUtils.startTrim(new File(url.split("/")[9]+"shibo.mp4"),"C:/Users/s.ibrahim/Documents/NetBeansProjects/videoEditing/ouput/", start, end);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    	
    
    }
//    
//    public void trimVideo(){
//    
//    try {
//H264TrackImpl h264Track = new H264TrackImpl(new FileDataSourceImpl(
//moviePath));
//Movie movie = new Movie();
//movie.addTrack(h264Track);
//
//    Container mp4file = new DefaultMp4Builder().build(movie);
//
//    FileChannel fc = new FileOutputStream(new File(outputPath))
//            .getChannel();
//    mp4file.writeContainer(fc);
//    fc.close();
//
//} catch (FileNotFoundException e) {
//    e.printStackTrace();
//} catch (IOException e) {
//    e.printStackTrace();
//} catch (Exception e) {
//    e.printStackTrace();
//}}
//   
//    
}
