package zelfPrutsen.wrapperdouble;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wrapperdoubleApp {

    public static void main(String[] args) {
        //double doubled = 5.30;

        //String doublei = "";
        //System.out.println(Double.valueOf(doublei));

        //double nullString = Double.parseDouble(null);

        //System.out.println(parseStringToDouble("k"));

        Pattern p = Pattern.compile("([0-9])");
        Matcher matcher = p.matcher("Hello everyb0dy");


        if(matcher.find()) System.out.println("true");

        Matcher m = Pattern.compile("([0-9])")
                .matcher("yourStringHere");
        while(m.find()) {
            System.out.println(m.group());
        }

    }

    private static double parseStringToDouble(String value) {
        return value == null || value.isEmpty()  ? Double.NaN : Double.parseDouble(value);
    }
}
