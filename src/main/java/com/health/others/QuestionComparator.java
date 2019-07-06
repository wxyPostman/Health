package com.health.others;

import com.health.entity.Question;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class QuestionComparator implements Comparator<Question> {
    @Override
    public int compare(Question o1, Question o2) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long date1 = 0;
        long date2 = 0;
        try {
            date1 = format.parse(o1.getTime()).getTime();
            date2 = format.parse(o2.getTime()).getTime();
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
