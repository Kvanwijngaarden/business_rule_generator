package controller.validation;

import java.util.Map;

/**
 * Created by Niels van Baren on 4-7-2017.
 */
public class EntityOtherRule implements IValidate{
    public boolean Validate(Map<String, String> Brule){
        ValidationFunctions vf = new ValidationFunctions();

        String[] fieldstrigger = "TARGET_TABLE|TARGET_COLUMN|GRULETYPE_RULETYPE_ID|TRIGGER_EVENT|TRIGGER_ON|VALUE".split("\\|");
        return vf.EmptyCheck(Brule, fieldstrigger);
    }
}
