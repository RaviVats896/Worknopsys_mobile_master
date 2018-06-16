package com.example.ravivats.worknopsysmobile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ravi Vats on 15-06-2018.
 */

public class Time24HrFormatValidator{

    private Pattern pattern;
    private Matcher matcher;

    private static final String TIME12HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public Time24HrFormatValidator(){
        pattern = Pattern.compile(TIME12HOURS_PATTERN);
    }

    public boolean validate(final String time){
        matcher = pattern.matcher(time);
        return matcher.matches();
    }
}