package fileWorker;

import java.io.File;
import java.util.ArrayList;

import iexecutable.IExecutable;
import tuple.Tuple;

public class FileWorker<T> {
	
	public final String path;
    public boolean isRecursive;


    public FileWorker(String str)
    {
        path = str;
    }

    public ArrayList<Tuple<String, T>> execute(IExecutable<T> command)
    {
        File currDirInfo = new File(path);
        if (isRecursive)
            recursiveProcess(currDirInfo, command);
        else
            flatProcess(currDirInfo, command);
        return command.getResult();
    }
    
    public Tuple<String, T> execute(IExecutable<T> command, String path){
		return command.getResult(path);    	
    }

    private void recursiveProcess(File currentDir, IExecutable<T> command)
    {
        flatProcess(currentDir, command);
        File[] allItems = currentDir.listFiles();
        ArrayList<File> allDirs = new ArrayList<File>(); 
        for (File item : allItems) {
        	if (item.isDirectory())
        		allDirs.add(item);
        }
        for (File dir: allDirs)
            recursiveProcess(dir, command);
    }

    private void flatProcess(File currentDir, IExecutable<T> command)
    {
        File[] allFiles = currentDir.listFiles();
        for (File file : allFiles)
            command.Process(file);
    }
    
    public ArrayList<String> getAllFiles(){
    	ArrayList<String> result = new ArrayList<String>();
    	File currentDirectory = new File(path);
    	File[] allFiles = currentDirectory.listFiles();
    	for (File file : allFiles) {
    		result.add(file.getAbsolutePath());
    	}
    	return result;
    }
    
    
}
