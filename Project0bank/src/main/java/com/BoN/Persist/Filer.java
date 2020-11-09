package com.BoN.Persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.BoN.Users.Users;

public class Filer {
	File newFile = new File("userFile.txt");
	File newFile2 = new File("loginFile.txt");
	public static final String  userFile = "userFile.txt";
	public static final String  loginFile = "loginFile.txt";
	
	//write stuff
	public static void writeUserFile(Map<Integer, Users>uMap){
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(userFile));
			objectOut.writeObject(uMap);
			objectOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeLogInFile(Map<String, String> uAndP){
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(loginFile));
			objectOut.writeObject(uAndP);
			objectOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readUserFile() {
		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(userFile));
			try {
				Lists.uMap = (Map<Integer, Users>) objectIn.readObject();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			objectIn.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unchecked")
	public static void readLogInFile() {
		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(loginFile));
			try {
				Lists.uAndP= (Map<String, String>) objectIn.readObject();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			objectIn.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
