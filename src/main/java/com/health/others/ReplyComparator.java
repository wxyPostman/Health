package com.health.others;

import com.health.entity.Reply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class ReplyComparator implements Comparator<Reply> {
    @Override
    public int compare(Reply o1, Reply o2) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long date1 = 0;
        long date2 = 0;
        try {
            date1 = format.parse(o1.getRtime()).getTime();
            date2 = format.parse(o2.getRtime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1 == date2) {
            return 0;
        } else if (date1 < date2) {
            return 1;
        } else {
            return -1;
        }

    }
}
