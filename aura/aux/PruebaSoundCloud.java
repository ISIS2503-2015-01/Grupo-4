import java.io.File;
import java.util.ArrayList;

import de.voidplus.soundcloud.*;
// https://github.com/nok/soundcloud-java-library

public class Prueba {
	
	public static SoundCloud soundcloud;
	public static  ArrayList archivos;
	public static String soundcloudId ="07fe9e7f76d4ac14db7bed65c2241a9d";
	public static String soundcloudSecret ="e66ea659eb26c0346df0a7670062237d";
	public static String soundcloudEmail ="aura201510@gmail.com";
	public static String soundcloudPsw ="aura123456789";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  String path = "."; 
		 
		  String files;
		  File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		  archivos = new ArrayList();
		  
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		 
		   if (listOfFiles[i].isFile()) 
		   {
		   files = listOfFiles[i].getName();
		       if (files.endsWith(".mp3") || files.endsWith(".MP3"))
		       {
		          System.out.println(files);
		          archivos.add(files);
		        }
		     }
		  }
		  
		  iniTConection();
		  
		  Integer id = upload("rain.mp3");
		  Track song = (Track) soundcloud.get("tracks/" + id);
		  System.out.println(song);
	}
	
	public static void iniTConection()
	{
		soundcloud = new SoundCloud(soundcloudId,soundcloudSecret);
		soundcloud.login(soundcloudEmail, soundcloudPsw);
	}
	
	public static Integer upload(String path)//Retorna id de canción subida
	{
		Track track = soundcloud.postTrack(new Track("Song 1", "rain.mp3"));
		// OR
		// Track track = soundcloud.post("tracks", new Track("titel of the song!", "path/to/file.mp3"));
		
		System.out.println(track.getTitle()+" (#"+track.getId()+")");
		return track.getId();
	}

}

