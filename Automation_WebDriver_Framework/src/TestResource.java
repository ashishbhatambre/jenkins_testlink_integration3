import java.util.*;

public class TestResource {
Locale locale=null;
ResourceBundle resourceBundle=null;

public TestResource(String resName){

resourceBundle=ResourceBundle.getBundle(resName,Locale.getDefault()); 
}

public String getItemInLocale(String itemName){
String res=resourceBundle.getString(itemName);
return res;
}

}
 
 