import java.io.*;

class ReportThread extends Thread {

String report ="";


public void run(){
write();
}

private synchronized void  write(){

			try{
				FileManager.setContents(new File("report/index.html"), report);
			}catch(Exception ne){}


}



}