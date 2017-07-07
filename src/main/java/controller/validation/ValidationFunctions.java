package controller.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Niels van Baren on 3-7-2017.
 */
public class ValidationFunctions {

    public boolean RangeCheck(String inputString1, String inputString2){
        if (inputString1.matches("^[0-9]*$") && inputString2.matches("^[0-9]*$")){
            if (Integer.parseInt(inputString1) < Integer.parseInt(inputString2)){
                return true;
            }
        }else if(inputString1.compareTo(inputString2) < 0) {
            return true;
        }
        return false;
    }

    public boolean EmptyCheck(Map<String, String> Brule, String[] fields){
        for (String key : fields) {
            String result = Brule.get(key);
            if (result == null || result.trim().equals("")) {
                return false;
            }
        }
        return true;
    }
}
