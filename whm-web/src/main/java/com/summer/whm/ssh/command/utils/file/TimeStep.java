package com.summer.whm.ssh.command.utils.file;

import java.util.Calendar;
import java.util.Date;

public class TimeStep {
    private Calendar calendar = Calendar.getInstance();
    // calendar field
    private int field = Calendar.SECOND;
    // the amount of the date or time
    private int amount = 10;

    /**
     * field
     * 
     * @return
     */
    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    /**
     * amount
     * 
     * @return
     */
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 获取时间
     * 
     * @exception IllegalArgumentException if field is unknown
     * @return
     */
    public Date next() {
        calendar.add(field, amount);
        return calendar.getTime();
    }
}
