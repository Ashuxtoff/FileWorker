package iexecutable;

import java.io.File;
import java.util.ArrayList;

import tuple.Tuple;

public interface IExecutable<T> {
	void Process(File file); 
    ArrayList<Tuple<String, T>> getResult();
    Tuple<String, T> getResult(String path);
    void addResult(String path, T result);
}
