package demo.parth_dev.parth.com.practical_35b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Parth PAtel on 11-Apr-18.
 */

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "customer.db";
    public static final String TABLE_NAME = "student_details";

    public static Context ctx;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // TODO Auto-generated constructor stub
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE "
                + TABLE_NAME
                + "(C_ID INTEGER PRIMARY KEY AUTOINCREMENT,C_NAME TEXT,C_MOBILE TEXT,C_ADDRESS TEXT,C_EMAIL TEXT)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

/*
    public Boolean insertdata(String name, String mobile, String address, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("C_NAME", name);
        c.put("C_MOBILE", mobile);
        c.put("C_ADDRESS", address);
        c.put("C_EMAIL", email);

        long r = db.insert(TABLE_NAME, null, c);

        if (r == -1) {
            return false;
        } else {
            return true;
        }

    }
*/

    public Cursor getdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME+ " WHERE C_ID "+"='" +id.trim()+"'";
        Cursor cdata = db.rawQuery(sql, null);
        return cdata;
    }

    public boolean updateData(String id,String name,String mobile,String address,String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("C_NAME", name);
        c.put("C_MOBILE", mobile);
        c.put("C_ADDRESS", address);
        c.put("C_EMAIL", email);
        long r = db.update(TABLE_NAME,c," C_ID= ?", new String[]{id});

        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deletedata(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
      long r=  db.delete(TABLE_NAME, "C_ID ='" + id + "'", null);
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }
}
