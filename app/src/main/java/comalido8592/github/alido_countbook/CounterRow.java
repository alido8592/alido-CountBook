package comalido8592.github.alido_countbook;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by eleiee on 2017-10-01.
 */

/**
 * Represents an item counter row
 *
 * @author Luis Anton Alido
 * @version 1.0
 */

public class CounterRow { //class for a single counting row

    private String itemName;
    private Date lastUpString;
    private int initval;
    private int currval;
    public String commentString;
    private String todayString;

    /**
     * Constructs a CounterRow
     *
     * @param itemName name of item being counted
     * @param lastUpString date the row has been last update
     * @param initval initial value for the item being counted
     */

    public CounterRow(String itemName, Date lastUpString, int initval) {
        this.itemName = itemName;

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);

        // based on
        // https://stackoverflow.com/questions/43983399/get-current-date-in-yyyy-mm-dd-format-in-java-android
        // as of 2017-09-28

        //update date on field for when currval changes

        this.lastUpString = lastUpString;
        this.todayString = todayString;
        this.initval = initval;
        this.currval = initval;
        this.commentString = "";
    }

}
