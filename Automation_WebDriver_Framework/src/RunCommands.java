import java.io.*;

public class RunCommands {



       public static void main(String as[]){
                       //runCommands("svnrun.cmd");
       }


private static void changeBaseURL(String fileName, String baseURL){
String script  = FileManager.getContents(fileName);

if(script.indexOf("#baseURL#")!=-1){
script = script.replaceAll("#baseURL#", baseURL);
}


try{FileManager.setContents(new File("vcscript/temselcommand.cmd"),script); } catch(Exception ne){ne.printStackTrace();}



}


       public static String runCommands(String batchFile, String baseURL){

changeBaseURL(batchFile,baseURL );
StringBuffer buffer = new StringBuffer();

//#baseURL#
try {
    // get runtime environment and execute child process
    Runtime systemShell = Runtime.getRuntime();
    Process output = systemShell.exec("vcscript/temselcommand.cmd");
    // open reader to get output from process
    BufferedReader br = new BufferedReader (new InputStreamReader(output.getInputStream()));
    String line = null;

         while((line = br.readLine()) != null )
        { 
			 
			 buffer.append(line);  }          // display process output
    int exitVal = output.waitFor();             // get process exit value
    //System.out.println("Process Exit Value : "+ exitVal);
    }
  catch (IOException ioe){ System.err.println(ioe); }
  catch (Throwable t) { t.printStackTrace();}

try{
File bakFile = new File("vcscript/temselcommand.cmd");
bakFile.delete();
bakFile.deleteOnExit();

}catch(Exception ne){}

  return buffer.toString();

       }

}