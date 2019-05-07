package md5Executor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import iexecutable.IExecutable;
import tuple.Tuple;

public class Md5Executor implements IExecutable<String>{
	
	public ArrayList<Tuple<String, String>> result = new ArrayList<Tuple<String, String>>();

	public void Process(File file) {
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
			String md5hash = DatatypeConverter.printHexBinary(MD5.digest(fileBytes));
			result.add(new Tuple<String, String> (file.getAbsolutePath(), md5hash));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public ArrayList<Tuple<String, String>> getResult() {
		return result;
	}

	public void addResult(String path, String resultString) {
		result.add(new Tuple<String, String>(path, resultString));
	}

	public Tuple<String, String> getResult(String path) {
		Process(new File(path));
		return result.get(0);
	}
}
