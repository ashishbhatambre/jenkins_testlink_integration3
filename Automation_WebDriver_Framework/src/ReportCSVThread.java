import java.io.*;

class ReportCSVThread extends Thread {

String report ="";


public void run(){
write();
}

private synchronized void  write(){

			try{
				FileManager.setContents(new File("report/testresult.csv"), report);
			}catch(Exception ne){}


}



}