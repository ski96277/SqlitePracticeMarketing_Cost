package imransk.ml.marketing_cost.Databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import imransk.ml.marketing_cost.POJOClass.Info_POJO;

public class DataOperation {
    DatabaseHelperClass databaseHelperClass;
    SQLiteDatabase sqLiteDatabase;

    public DataOperation(Context context) {

        databaseHelperClass = new DatabaseHelperClass(context);
    }

    public void openDB() {
        sqLiteDatabase = databaseHelperClass.getWritableDatabase();
    }

    public void closeDB() {
        sqLiteDatabase.close();
    }

    public boolean insertDB(Info_POJO info_pojo) {
        this.openDB();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelperClass.coulmn_1, info_pojo.getItem());
        values.put(DatabaseHelperClass.coulmn_2, info_pojo.getQuentity());
        values.put(DatabaseHelperClass.coulmn_3, info_pojo.getPrice());
        values.put(DatabaseHelperClass.coulmn_4, info_pojo.getSpinner());


        long number = sqLiteDatabase.insert(DatabaseHelperClass.table_name, null, values);
        this.closeDB();
        if (number > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Info_POJO> get_ALL_Info_Table() {

        List<Info_POJO> info_pojos = new ArrayList<>();

        this.openDB();
        Cursor cursor = sqLiteDatabase.query(DatabaseHelperClass.table_name, null, null, null
                , null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0 && cursor != null) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelperClass.coulmn_0));
                String item = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_1));
                String quentity = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_2));
                String price = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_3));
                String spinner = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_4));
                info_pojos.add(new Info_POJO(String.valueOf(id), item, quentity, price, spinner));

            } while (cursor.moveToNext());
            this.closeDB();
        }
        return info_pojos;

    }

    public List<Info_POJO> get_Table_Info(String table) {

        List<Info_POJO> info_pojos = new ArrayList<>();

        this.openDB();
        Cursor cursor = sqLiteDatabase.query(table, null, null, null
                , null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0 && cursor != null) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelperClass.coulmn_0));
                String item = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_1));
                String quentity = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_2));
                String price = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_3));
                String spinner = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_4));
                info_pojos.add(new Info_POJO(String.valueOf(id), item, quentity, price, spinner));

            } while (cursor.moveToNext());
            this.closeDB();
        }
        return info_pojos;

    }

    public boolean deleteUser(String id) {
        this.openDB();
        int number = sqLiteDatabase.delete(DatabaseHelperClass.table_name, DatabaseHelperClass.coulmn_0 + "=" + id, null);
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Info_POJO info_pojos) {
        this.openDB();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperClass.coulmn_1, info_pojos.getItem());
        values.put(DatabaseHelperClass.coulmn_2, info_pojos.getQuentity());
        values.put(DatabaseHelperClass.coulmn_3, info_pojos.getPrice());
        values.put(DatabaseHelperClass.coulmn_4, info_pojos.getSpinner());
        int number = sqLiteDatabase.update(DatabaseHelperClass.table_name, values, DatabaseHelperClass.coulmn_0 + "=" + info_pojos.getId(), null);

        if (number > 0) {
            return true;
        } else {
            return true;
        }
    }

    public List<String> getAllTable() {
        List<String> alltable_list = new ArrayList<>();
        this.openDB();
        Cursor c = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                alltable_list.add(c.getString(0));

                c.moveToNext();
            }
        }
        return alltable_list;
    }

    public int TotalPrice(String table) {
        int total_price = 0;
        this.openDB();
        Cursor cursor = sqLiteDatabase.query(table, null, null, null, null,
                null, null);
        if (cursor.getCount() > 0 && cursor != null) {
            cursor.moveToFirst();
            do {
                String price = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.coulmn_3));
                total_price = total_price + Integer.valueOf(price);
            } while (cursor.moveToNext());
            return total_price;
        }
        this.closeDB();
        return total_price;
    }

    public void table_Delete(String table){
        this.openDB();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table);
        this.closeDB();

    }
}
