package edu.rupp.mite.weekend.BranchAndBoundSearch.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
	
	private File file = null;
	
	public FileReader(File file) {
		this.file = file;
	}
	
	public String getContent() {
		String content = "";
		
		BufferedReader bufferReader = null;
	    try {
			bufferReader = new BufferedReader(new java.io.FileReader(file));
	        StringBuilder stringBuilder = new StringBuilder();
	        String line = bufferReader.readLine();

	        while (line != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(System.lineSeparator());
	            line = bufferReader.readLine();
	        }
	        content = stringBuilder.toString();
	    } catch(NullPointerException npe) {
			npe.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
	    } catch(IOException ioe) {
	    	ioe.printStackTrace();
	    } finally {
	    	try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	    }

	    return content;
	}

}
