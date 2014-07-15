import java.util.*;


public class ReportManager {


public static String getCSV(int totalTestCases, String company, String productName, String browser, HashMap testCaseNames, HashMap isPassStatus, HashMap testCaseMap, HashMap imageList, HashMap testFileName){

StringBuffer buffer = new StringBuffer();


buffer.append("\nCompany,"+company+"");
buffer.append("\nProduct,"+productName+"");
buffer.append("\nBrowser,"+browser+"");

if(SELTestRunner.isSmokeTest)
buffer.append("\nTest Run,Smoke Test");

else
buffer.append("\nTest Run,Regression Test");

if(SELTestRunner.isSmokeTest)
buffer.append("\nTest Case,Component,Status,Test File");
else
buffer.append("\nTest Case,Component,Role,Artifact,Status,Test File");

Iterator it=isPassStatus.keySet().iterator();

for(int i=0; i<testFileName.size(); i++){

String testVaseNameRow = ""+testCaseNames.get(new Integer(i));
Boolean isPass = (Boolean)isPassStatus.get(testVaseNameRow);

if(SELTestRunner.isSmokeTest){

if(isPass)
buffer.append("\n"+testVaseNameRow+","+SELTestRunner.componentMap.get(new Integer(i))+",Pass,"+testFileName.get(new Integer(i))+"");

	else
buffer.append("\n"+testVaseNameRow+","+SELTestRunner.componentMap.get(new Integer(i))+",Fail,"+testFileName.get(new Integer(i))+"");

}

else{
if(isPass)
buffer.append("\n"+testVaseNameRow+","+SELTestRunner.componentMap.get(new Integer(i))+","+SELTestRunner.roleMap.get(new Integer(i))+","+SELTestRunner.artifactMap.get(new Integer(i))+",Pass,"+testFileName.get(new Integer(i))+"");

	else
buffer.append("\n"+testVaseNameRow+","+SELTestRunner.componentMap.get(new Integer(i))+","+SELTestRunner.roleMap.get(new Integer(i))+","+SELTestRunner.artifactMap.get(new Integer(i))+",Fail,"+testFileName.get(new Integer(i))+"");
}


}


return buffer.toString();

	}


public static String getIndex(int totalTestCases, String company, String productName, String browser, HashMap testCaseNames, HashMap isPassStatus, HashMap testCaseMap, HashMap imageList, HashMap testFileName){

StringBuffer buffer = new StringBuffer();

buffer.append("\n<html xmlns=\"http://www.w3.org/1999/xhtml\">");
buffer.append("\n<head>");
buffer.append("\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
buffer.append("\n<title>"+productName+"</title>");
    
	buffer.append("\n<script type=\"text/javascript\">");

       buffer.append("\n var GB_ROOT_DIR = \"greybox/\";");

    buffer.append("\n</script>");



    buffer.append("\n<script type=\"text/javascript\" src=\"greybox/AJS.js\"></script>");

    buffer.append("\n<script type=\"text/javascript\" src=\"greybox/AJS_fx.js\"></script>");

    buffer.append("\n<script type=\"text/javascript\" src=\"greybox/gb_scripts.js\"></script>");

    buffer.append("\n<link href=\"greybox/gb_styles.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />");

buffer.append("\n<style type=\"text/css\">");
buffer.append("\n<!--");
buffer.append("\n.style1 {");
buffer.append("\n	color: #FFFFFF;");
buffer.append("\n	font-family: Arial, Helvetica, sans-serif;");
buffer.append("\n}");
buffer.append("\n.style2 {");
buffer.append("\n	font-family: Arial, Helvetica, sans-serif;");
buffer.append("\n	font-weight: bold;");
buffer.append("\n}");
buffer.append("\n.style3 {font-family: Verdana, Arial, Helvetica, sans-serif}");
buffer.append("\n-->");
buffer.append("\n</style>");
buffer.append("\n</head>");

buffer.append("\n<body>");
buffer.append("\n<table width=\"100%\" border=\"0\" cellspacing=\"0\">");
  buffer.append("\n<tr>");
    buffer.append("\n<td height=\"44\" bgcolor=\"#666666\"><table width=\"100%\" border=\"0\" cellspacing=\"10\">");
      buffer.append("\n<tr>");
       buffer.append("\n <td bgcolor=\"#FFFFFF\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" bgcolor=\"#660000\">");

String testRun="Smoke Test";

	   if(SELTestRunner.isSmokeTest)
testRun="Smoke Test";

else
	testRun="Regression Test";



          buffer.append("\n<tr>");
            buffer.append("\n<td width=\"73%\" height=\"32\"><div align=\"center\"><span class=\"style1\">["+testRun+"]"+productName+" against "+browser+"</span></div></td>");
           buffer.append("\n <td width=\"27%\"><div align=\"center\" class=\"style1\">Generated on "+DateUtils.format(new java.util.Date(),DateUtils.ISO8601_DATETIME_PATTERN)+"</div></td>");
         buffer.append("\n </tr>");
       buffer.append("\n </table></td>");
    buffer.append("\n  </tr>");
   buffer.append("\n </table></td>");
  buffer.append("\n</tr>");
  buffer.append("\n<tr>");
    buffer.append("\n<td><table width=\"100%\" border=\"0\" cellspacing=\"5\">");
      buffer.append("\n<tr bgcolor=\"#F0F0F0\">");


/******* Test case ********************/

if(SELTestRunner.isSmokeTest){
       buffer.append("\n <td width=\"18%\"><span class=\"style2\">Test Case</span></td>");
	   		buffer.append("\n<td width=\"7%\"><span class=\"style2\">Component</span></td>");
        buffer.append("\n<td width=\"7%\"><span class=\"style2\">Status</span></td>");
	buffer.append("\n<td width=\"47%\"><span class=\"style2\">Test File</span></td>");
        buffer.append("\n<td width=\"28%\"><span class=\"style2\">Page Output</span></td>");
     buffer.append("\n </tr>");
}
else{

	 buffer.append("\n <td width=\"18%\"><span class=\"style2\">Test Case</span></td>");
	   		buffer.append("\n<td width=\"7%\"><span class=\"style2\">Component</span></td>");
				buffer.append("\n<td width=\"7%\"><span class=\"style2\">Role</span></td>");
				buffer.append("\n<td width=\"7%\"><span class=\"style2\">Artifact</span></td>");
        buffer.append("\n<td width=\"7%\"><span class=\"style2\">Status</span></td>");
	buffer.append("\n<td width=\"47%\"><span class=\"style2\">Test File</span></td>");
        buffer.append("\n<td width=\"28%\"><span class=\"style2\">Page Output</span></td>");
     buffer.append("\n </tr>");

}

	 int passCount = 0;

	 int failCount = 0;

for(int i=0; i<testCaseNames.size(); i++){
    
	String testVaseNameRow = ""+testCaseNames.get(new Integer(i));
	Boolean isPass = (Boolean)isPassStatus.get(testVaseNameRow);

//13-Jun-2013
String testCases = (String)testCaseMap.get(testVaseNameRow);
String imgName = (String)imageList.get(testVaseNameRow);

	if(isPass.booleanValue()){
		passCount+=1;
	
	 buffer.append("\n <tr>");
       buffer.append("\n <td><span class=\"style3\">"+testVaseNameRow+"</span></td>");
    
			
			if(SELTestRunner.isSmokeTest){
 buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.componentMap.get(new Integer(i))+"");
       buffer.append("\n </td>");
			}
			else{
buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.componentMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

	   buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.roleMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

 buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.artifactMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

			}

       buffer.append("\n <td><p class=\"style3\">PASS</p>");
        buffer.append("\n  <p class=\"style3\"><img src=\"pass.jpg\" width=\"48\" height=\"48\" /></p></td>");

buffer.append("\n <td>");
          buffer.append("\n "+testFileName.get(new Integer(i))+"");
       buffer.append("\n </td>");

        buffer.append("\n<td><ol class=\"style3\">");
          buffer.append("\n ");
      buffer.append("\n </ol></td>");
     buffer.append("\n </tr>");
     buffer.append("\n <tr>");
	}
	else {

failCount+=1;
      buffer.append("\n <tr>");
       buffer.append("\n <td><span class=\"style3\">"+testVaseNameRow+"</span></td>");
    
			
			if(SELTestRunner.isSmokeTest){
 buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.componentMap.get(new Integer(i))+"");
       buffer.append("\n </td>");
			}
			else{
buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.componentMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

	   buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.roleMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

 buffer.append("\n <td>");
          buffer.append("\n "+SELTestRunner.artifactMap.get(new Integer(i))+"");
       buffer.append("\n </td>");

			}

       buffer.append("\n <td><p class=\"style3\">FAIL</p>");
        buffer.append("\n  <p class=\"style3\"><img src=\"fail.gif\" width=\"48\" height=\"48\" /></p></td>");

buffer.append("\n <td>");
          buffer.append("\n "+testFileName.get(new Integer(i))+"");
       buffer.append("\n </td>");

        buffer.append("\n<td><ol class=\"style3\">");
          buffer.append("\n "+imgName);
      buffer.append("\n </ol></td>");
     buffer.append("\n </tr>");
     buffer.append("\n <tr>");
	}

 buffer.append("\n <tr>");
      buffer.append("\n  <td colspan=\"4\"><hr  size=\"1\" color=\"#333333\"/></td>");
      buffer.append("\n  </tr>");
}
/*******/

    
     buffer.append("\n <tr>");
     buffer.append("\n   <td colspan=\"4\"><b>Total Test Cases :<b> "+totalTestCases+"<b>  Total Test Cases Run :<b> "+testCaseNames.size()+" Passed : "+passCount+" Failed : "+failCount+"</td>");
    buffer.append("\n  </tr>");
   buffer.append("\n </table></td>");
 buffer.append("\n </tr>");
 buffer.append("\n <tr>");
 buffer.append("\n   <td bgcolor=\"#666666\">&nbsp;&nbsp;<span class=\"style1\">&copy; "+company+"</span></td>");
  buffer.append("\n</tr>");
buffer.append("\n</table>");
buffer.append("\n</body>");
buffer.append("\n</html>");

return buffer.toString();
}


}