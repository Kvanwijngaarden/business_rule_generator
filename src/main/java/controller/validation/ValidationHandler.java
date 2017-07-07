package controller.validation;

import java.util.Map;

/**
 * Created by Niels van Baren on 4-7-2017.
 */
public class ValidationHandler {
    public boolean Choice(Map<String, String> Brule){
        int brultype = Integer.parseInt(Brule.get("GRULETYPE_RULETYPE_ID"));
        AttributeRangeRule AR = new AttributeRangeRule();
        AttributeCompareRule AC = new AttributeCompareRule();
        AttributeListRule AL = new AttributeListRule();
        AttributeOtherRule AO = new AttributeOtherRule();
        TupleCompareRule TC = new TupleCompareRule();
        TupleOtherRule TO = new TupleOtherRule();
        InterEntityCompareRule IEC = new InterEntityCompareRule();
        EntityOtherRule EO = new EntityOtherRule();
        ModifyRule M = new ModifyRule();

        switch (brultype) {
            case 10:  return AR.Validate(Brule);
            case 11:  return AC.Validate(Brule);
            case 12:  return AL.Validate(Brule);
            case 13:  return AO.Validate(Brule);
            case 14:  return TC.Validate(Brule);
            case 15:  return TO.Validate(Brule);
            case 16:  return IEC.Validate(Brule);
            case 17:  return EO.Validate(Brule);
            case 18:  return M.Validate(Brule);
        }
        return false;
    }
}
