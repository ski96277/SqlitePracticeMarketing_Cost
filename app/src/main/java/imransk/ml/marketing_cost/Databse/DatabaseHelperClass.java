package imransk.ml.marketing_cost.Databse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    public static int database_version = 1;

    private SQLiteDatabase sqLiteDatabase;
    public static String database_name = "Hisab.DB";
    public static String table_name = "["+dateTodayinDD_MM_YYFormat()+"]";

    public static String coulmn_0 = "user_id";
    public static String coulmn_1 = "item_Cl";
    public static String coulmn_2 = "quentity_cl";
    public static String coulmn_3 = "price_cl";
    public static String coulmn_4 = "spinner_cl";

    public static String create_Table = "CREATE TABLE IF NOT EXISTS " + table_name + " ( " + coulmn_0 + " INTEGER PRIMARY KEY, " +
            coulmn_1 + " text NOT NULL, " + coulmn_2 + " text NOT NULL, " + coulmn_3 + " text NOT NULL, " + coulmn_4 + " text NOT NULL);";

    /*

    public static String create_table = "CREATE TABLE "+DATABASE_TABLE_NAME+" ( "+DATABASE_ID+" INTEGER PRIMARY KEY, "
            +COL_USERNAME+" text NOT NULL, "+COL_EMAIL+" text NOT NULL,"+COL_PASS+" text NOT NULL);";
);
*/


    public DatabaseHelperClass(@Nullable Context context) {
        super(context, database_name, null, database_version);
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(create_Table);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static String dateTodayinDD_MM_YYFormat() {
        return new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());
    }
}
