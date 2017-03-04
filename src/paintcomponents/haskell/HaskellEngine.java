package paintcomponents.haskell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class HaskellEngine {
	
	public String interpreterName = "ghci";
	
	public String typeForExpression(String expr){
		//building command to execute
		String cmd = "echo :t " + expr + " | " + interpreterName;
		
		try {
//			String[] cmds = {
//					"/bin/sh",
//					"-c",
//					"\"" + cmd + "\""
//					};
//			System.out.println(System.getenv().get("PATH"));
//			Process process = Runtime.getRuntime().exec(cmds, null);

			Process process = Runtime.getRuntime().exec(interpreterName, null);
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			w.write(":t " + expr);
			w.flush();
			w.close();
			process.waitFor();
			if(process.exitValue() != 0){
				System.out.println(convertStreamToString(process.getErrorStream()));
				return "Error In Execution, perhaps ghci is not installed?";
			}
			String output = convertStreamToString(process.getInputStream());
			String[] comps = output.split("Prelude>");
			String error = convertStreamToString(process.getErrorStream());
			//Assume the typeStirng is = comps[comps.length - 2];
			return comps[comps.length - 2] + error;


		} catch (IOException e) {

			e.printStackTrace();
			return "IO Exception";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Interruped Exception";
		}


	}
	
	public String valueForExpression(String expr){
		
			Process process;
			try {
				process = Runtime.getRuntime().exec(interpreterName, null);
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			w.write( expr);
			w.flush();
			w.close();
			process.waitFor();
			if(process.exitValue() != 0){
				System.out.println(convertStreamToString(process.getErrorStream()));
				return "Error In Execution, perhaps ghci is not installed?";
			}
			String output = convertStreamToString(process.getInputStream());
			String[] comps = output.split("Prelude>");
			//the error is null if there is no error
			String error = convertStreamToString(process.getErrorStream());

			//Assume the typeStirng is = comps[comps.length - 2];
			return comps[comps.length - 2] + error;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "IO Error";
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "INterrupted Error";
			}
			
	}
	
	//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
