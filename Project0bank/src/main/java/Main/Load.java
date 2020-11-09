package Main;

import java.io.File;
import java.io.IOException;

import com.BoN.Persist.Filer;
import com.BoN.Persist.Lists;
import com.BoN.Users.Admin;
import com.BoN.Users.Customer;

public class Load {
	
	public static void load(){
		Filer.readLogInFile();
		
		/*
		 * try { File newFile = new File("loginFile.txt"); if (newFile.createNewFile())
		 * { System.out.println("File created: " + newFile.getName()); } else {
		 * System.out.println("File already exists."); } } catch (IOException e) {
		 * System.out.println("An error occurred."); e.printStackTrace(); }
		 */
		
	  Admin a = new Admin(); 
	  String au= "a";
	  String apass= "abcde";
	  a.setUsername(au); 
	  a.setPassword(apass); 
	  Lists.uMap.put(a.getUserId(),a);
	  Lists.uAndP.put(au, apass); 
	  Filer.writeUserFile(Lists.uMap);
	  
	  Customer bb = new Customer(); 
	  String bu= "bb";
	  String bpass= "bb33";
	  a.setUsername(bu); 
	  a.setPassword(bpass); 
	  Lists.uMap.put(bb.getUserId(),bb);
	  Lists.uAndP.put(bu, bpass); 
	  Filer.writeUserFile(Lists.uMap);
	} 
}
