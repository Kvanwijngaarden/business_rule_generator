package controller.validation;

import java.util.Map;

/**
 * Created by Niels van Baren on 4-7-2017.
 */
public class AttributeListRule implements IValidate{
    public boolean Validate(Map<String, String> Brule) {
        ValidationFunctions vf = new ValidationFunctions();

        String[] fieldstrigger = "TARGET_TABLE|TARGET_COLUMN|GRULETYPE_RULETYPE_ID|TRIGGER_EVENT|TRIGGER_ON|OPERATOR|VALUE".split("\\|");
        String[] fieldscontraint = "TARGET_TABLE|TARGET_COLUMN|GRULETYPE_RULETYPE_ID|OPERATOR|VALUE".split("\\|");

        if (Brule.get("TRIGGER_EVENT") != null || Brule.get("TRIGGER_ON") != null){
            return vf.EmptyCheck(Brule, fieldstrigger);
        }else{
            return vf.EmptyCheck(Brule, fieldscontraint);
        }
    }
}
