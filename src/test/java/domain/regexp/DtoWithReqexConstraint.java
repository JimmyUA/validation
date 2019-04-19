package domain.regexp;

import validation.constraints.RegexConstraint;
import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoWithReqexConstraint implements Dto {

    @RegexConstraint(pattern = "^[0-9]*")
    private String digitsOnly;

    @RegexConstraint(pattern = "[a-z]*")
    private String onlyLatinLow;

    public void setDigitsOnly(String digitsOnly) {
        this.digitsOnly = digitsOnly;
    }

    public void setOnlyLatinLow(String onlyLatinLow) {
        this.onlyLatinLow = onlyLatinLow;
    }
}
