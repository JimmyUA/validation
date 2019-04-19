package domain.length;

import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoWithStringLengthConstraint implements Dto {

    @StringLength
    private String defaultMaxMin;

    @StringLength(min = 3, max = 10)
    private String bothThreeTen;

    @StringLength(min = 10)
    private String minTen;

    @StringLength(max = 6)
    private String maxSix;

    public void setDefaultMaxMin(String defaultMaxMin) {
        this.defaultMaxMin = defaultMaxMin;
    }

    public void setBothThreeTen(String bothThreeTen) {
        this.bothThreeTen = bothThreeTen;
    }

    public void setMinTen(String minTen) {
        this.minTen = minTen;
    }

    public void setMaxSix(String maxSix) {
        this.maxSix = maxSix;
    }
}
