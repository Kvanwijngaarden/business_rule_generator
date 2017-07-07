package controller.validation;


import java.util.Map;

/**
 * Created by Niels van Baren on 3-7-2017.
 */
public class AttributeRangeRule implements IValidate {
    public boolean Validate(Map<String, String> Brule) {
        ValidationFunctions vf = new ValidationFunctions();

        String[] fieldstrigger = "GLANGUAGE_LANG_ID|TARGET_TABLE|TARGET_COLUMN|GRULETYPE_RULETYPE_ID|TRIGGER_EVENT|TRIGGER_ON|OPERATOR|VALUE|VALUE2".split("\\|");
        String[] fieldscontraint = "GLANGUAGE_LANG_ID|TARGET_TABLE|TARGET_COLUMN|GRULETYPE_RULETYPE_ID|OPERATOR|VALUE|VALUE2".split("\\|");

        if (Brule.get("TRIGGER_EVENT") != null || Brule.get("TRIGGER_ON") != null) {
            if (!vf.EmptyCheck(Brule, fieldstrigger)){
                return false;
            }
            else if(!vf.RangeCheck(Brule.get("VALUE"), Brule.get("VALUE2"))){
                return false;
            }
            return true;
        }else{
            if (!vf.EmptyCheck(Brule, fieldscontraint)){
                return false;
            }
            else if(!vf.RangeCheck(Brule.get("VALUE"), Brule.get("VALUE2"))) {
                return false;
            }
            return true;
        }
    }
}
