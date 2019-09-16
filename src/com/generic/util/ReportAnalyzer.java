package com.generic.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.generic.setup.EnvironmentFiles;

public class ReportAnalyzer {
	public static void analyze2(String path, float period_step ) throws IOException
	{
		System.out.println("Analyzing report: " + path + " Period is: " + period_step);
		String content;
		content = new String(Files.readAllBytes(Paths.get(path)));
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		String Pline = content.split("\n")[0];
		String result = "0: " + Pline;

		int current_line = 0;
		String[] lines = content.split("\n");
		String [] body = Arrays.copyOfRange(lines, 1, lines.length);
		for (String Cline : body) {
			long diff = 0;
			try {
				diff = (format.parse(Cline.split(" ")[1]).getTime()) - (format.parse(Pline.split(" ")[1]).getTime());
			} catch (Exception t) {
				t.printStackTrace();
				//System.out.println("Line is not parsable" + current_line);
				diff = 0;
			}

			if (diff > period_step) {
				result = result + "<SPAN STYLE='background-color: #ffffcc'>" + diff / 1000 + ": " + Cline + "</SPAN>\n";
			} else {
				result = result + diff / 1000 + ": " + Cline + "\n";
			}
			current_line++;
			Pline = content.split("\n")[current_line];
		}
		FileUtils.writeStringToFile(new File(path), result, true);
		System.out.print(result);
	}
	
	public static void analyze(String path, float period_step ) throws IOException
	{
		System.out.println("Analyzing report: " + path +" Period is: "+ period_step);
		String content;
		content = new String(Files.readAllBytes(Paths.get(path)));
		//BufferedReader bufReader = new BufferedReader(new StringReader(content));
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
		
		int lineNumber = 1;
		String[] lines = content.split("\n");
		
		String Pline="";
		String Cline="";
		
		String analyzedResult = "<font face='monospace'>0: " + lines[0];
		
		for(int i=0; i< lines.length-1; i++)
		//while( (Cline=bufReader.readLine()) != null )
		{	
			Pline = lines[lineNumber-1];
			Cline = lines[lineNumber++];
				
				long  diff = 0 ;
				
				try {
					diff = (format.parse(Cline.split(" ")[1]).getTime())- (format.parse(Pline.split(" ")[1]).getTime());
				} catch (Exception t) {
					//System.out.println("Line is not parsable");
					diff = 0;
				}
				
			if (diff>period_step)
			{
				//FileUtils.writeStringToFile(new File(path), "<SPAN STYLE='background-color: #ffffcc'>"+diff/1000+": "+Cline+"</SPAN>",true) ;
				analyzedResult +="<SPAN STYLE='background-color: #ffffcc'>"+diff/1000+": "+Cline+"</SPAN>";
			}
			else
			{
				//FileUtils.writeStringToFile(new File(path), diff/1000+": "+Cline, true) ;
				analyzedResult +=diff/1000+": "+Cline;
			}
			//Pline = new String(Cline);
		}
		FileUtils.writeStringToFile(new File(path), analyzedResult+"<style>img {    border: 1px solid #ddd;    border-radius: 4px;    padding: 5px;    width: 150px;}img:hover {    box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);}</style>",Charset.forName("utf-8")) ;
	}
	
	public static void splitLogs()
	{
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath(),EnvironmentFiles.getLogFileName());
		//Path PathObj = Paths.get("F:\\WS\\OCM_Silver\\logs\\Application.log");
		FileWriter fw = null;
		BufferedWriter bw = null;
		String[] content = null;
		
		try {
			FileUtils.deleteDirectory(new File(PathObj.getParent().toString()+"\\reportLogs\\"));
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e2) {
			e2.printStackTrace();
		}
		File reportLogs = new File(PathObj.getParent().toString()+"\\reportLogs\\");
		reportLogs.mkdir();
		
		
		try {
			content = new String(Files.readAllBytes(PathObj)).split("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String lastThreadID = "";
		for (String line : content) {
			try {
				String currentthreadID =  getThreadID(line) ;
				File logfile ;
				if (!currentthreadID.equals("")) {
					lastThreadID = currentthreadID;
					logfile = new File(PathObj.getParent()+"\\reportLogs\\"+ currentthreadID + ".html");
				}
				else
				{
					logfile = new File(PathObj.getParent()+"\\reportLogs\\"+ lastThreadID + ".html");
				}
				
				// if file doesnt exists, then create it
				if (!logfile.exists()) {
					logfile.createNewFile();
					fw = new FileWriter(logfile.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);
					bw.write("<font face='monospace'>\n"+line+"\n");
				}else
				{
					fw = new FileWriter(logfile.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);
					bw.write(line+"\n");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	private static String getThreadID(String line) {
		String[] threadID = line.split(":");
		//thread id should be numeric
		if (threadID.length>4 && StringUtils.isNumeric(threadID[3].trim()))
			return threadID[3].trim();
		return "";
	}
	
	public static void splitCases() {
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath(),"reportLogs");
		//Path PathObj = Paths.get("F:\\WS\\OCM_Silver\\logs\\reportLogs");
		
		File index = new File(PathObj.toString());
		String[]entries = index.list();
		String caseSplitter = "com.generic.setup.SelTestCase.setUp";  
		int numberOfCases = 0;
		
		for(String s: entries){
			File currentFile = new File(index.getPath(),s);
			System.out.println("File"+ currentFile.toString());
		    String content = null;
			try {
				content = new String(Files.readAllBytes(currentFile.toPath()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			//check if there are more than one case in file
			numberOfCases = content.split(caseSplitter).length/2 ;
			String[] lines = content.split("\r");
			if (numberOfCases > 1)
			{
				for (int readedCases = 0; readedCases < numberOfCases ; readedCases++)
				{
					String splittedFile = ""; 
					int stopper = 1; 
					for( String line :  lines)
				    {
						//Keep reading and insert into file string case until the case number  
				    	if (line.contains(caseSplitter) && line.toLowerCase().contains("starting") )
				    	{	stopper++;		    			
				    	}//stopper counter 
				    	if (stopper==1 || stopper == (readedCases+2))
				    	{
				    		splittedFile+=(line+"\r");
				    	}
				    }
					try {
						Files.write(Paths.get(currentFile.toString().replace(".", "_"+readedCases + ".")), splittedFile.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				//removing the base file to prevent duplication 
				currentFile.delete();
				
			}//if number of cases 
		}
	}//split cases
	
	public static void copyScreenShots()
	{
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath());
		//Path PathObj = Paths.get("F:\\WS\\OCM_Silver\\logs");
		
		File index = new File(PathObj.toString());
		String[]entries = index.list();
		for(String s: entries){
			File currentFile = new File(index.getPath(),s);
			if (currentFile.toString().endsWith("jpg") || currentFile.toString().endsWith("png"))
			{
				try {
					FileUtils.copyFileToDirectory(currentFile, Paths.get(PathObj.toString(), "reportLogs").toFile());
					currentFile.delete();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//ReportAnalyzer.splitLogs(); 
		//ReportAnalyzer.splitCases();
		//ReportAnalyzer.copyScreenShots();
		//ReportAnalyzer.analyze("F:\\WS\\SAS_FW/AutomationReport/AutoRep_12-26-2017142235//12.html",5000);
		
	}

}
