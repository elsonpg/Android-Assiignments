package expenser.elson.com.expensecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.elson.expenser.com.elson.expenser.models.IncomeCategoryInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Database extends SQLiteOpenHelper {
	  private Context context;
	  private static final String DATABASE_NAME = "FinanceManage.db";
	  private static final int DATABASE_VERSION = 1;
	  public Database(Context context1)
	    {
	        super(context1, DATABASE_NAME, null,DATABASE_VERSION);
	        context = context1;
	       
	    }
	  public Cursor getCursor(String s)
	    {
	        Log.i("Expenser", s);
	        return getWritableDatabase().rawQuery(s, null);
	    }
	  private void save(String s, int i, SQLiteDatabase sqlitedatabase, ContentValues contentvalues)
	    {
	        if (i == 0)
	        {
	            sqlitedatabase.insert(s, null, contentvalues);
	            return;
	        } else
	        {
	            sqlitedatabase.update(s, contentvalues, (new StringBuilder("_id ='")).append(i).append("'").toString(), null);
	            return;
	        }
	    }
	@Override
	public void onCreate(SQLiteDatabase sqlitedatabase) {
		  SQLiteDatabase sqlitedatabase1 = sqlitedatabase;
	        if (sqlitedatabase.isReadOnly())
	        {
	            sqlitedatabase1 = getWritableDatabase();
	        }
	        sqlitedatabase1.execSQL((new StringBuilder(String.valueOf("CREATE TABLE IF NOT EXISTS "))).append("categories").append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ").append("category TEXT,category_type TEXT, image integer)").toString());
	        sqlitedatabase1.execSQL((new StringBuilder(String.valueOf("CREATE TABLE IF NOT EXISTS "))).append("movements").append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ").append("account TEXT, category TEXT, quantity TEXT, sign TEXT, detail TEXT, date_idx TEXT, date TEXT, time TEXT, day TEXT, week TEXT, fortnight TEXT, month TEXT, year TEXT)").toString());
		
	}

    public void add_category(String category ,String category_type,int image, String account, String select, int i)
    {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + image);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", category);
        values.put("category_type",  category_type);
        values.put("image",  image);

       // save("categories",i,db,values);
        db.insert("categories", null, values);
        db.close();

    }
    public int countCategories() {

        String countQuery = "SELECT  * FROM " + "categories";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        db.close();
        return cnt;
    }
    public List<IncomeCategoryInfo> get_allcategories() {
       List<IncomeCategoryInfo> originalValues = new ArrayList<IncomeCategoryInfo>();
        HashMap<String, String> temp;

        String selectQuery = "SELECT  * FROM " + "categories";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                IncomeCategoryInfo ci = new IncomeCategoryInfo();
                ci.name = cursor.getString(1);
                ci.image = Integer.parseInt(cursor.getString(3));
                originalValues.add(ci);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return originalValues;
    }
	  public void selectAllCategories(String s, String s1)
	    {
	       /* SQLiteDatabase sqlitedatabase = getWritableDatabase();
	        s1 = getCursor((new StringBuilder("SELECT * FROM categories ")).append(s1).toString());	       
	        if (s1.moveToFirst())
	        {
	            do
	            {
	                int i = s1.getInt(s1.getColumnIndex("_id"));
	                ContentValues contentvalues = new ContentValues();
	                contentvalues.put("select", s);
	                save("categories", i, sqlitedatabase, contentvalues);
	            } while (s1.moveToNext());
	        }*/
	    }
	  public void selectCategory(int i)
	    {
	        SQLiteDatabase sqlitedatabase = getWritableDatabase();
	        ContentValues contentvalues = new ContentValues();
	        contentvalues.put("select", "si");
	        save("categories", i, sqlitedatabase, contentvalues);
	    }
	  public void updateMovementDates(SQLiteDatabase sqlitedatabase)
	    {
	        Cursor cursor = sqlitedatabase.rawQuery("SELECT * FROM movements", null);
	        ContentValues contentvalues = new ContentValues();
	        if (cursor.moveToFirst())
	        {
	            do
	            {
	                String s = cursor.getString(cursor.getColumnIndex("date"));
	                int i = cursor.getInt(cursor.getColumnIndex("_id"));
	                String s1 = cursor.getString(cursor.getColumnIndex("time"));
	                contentvalues.put("day", "");
	                contentvalues.put("week", "");
	                contentvalues.put("fortnight", "");
	                contentvalues.put("month", "");
	                contentvalues.put("date_idx", "");
	                sqlitedatabase.update("movements", contentvalues, (new StringBuilder("_id ='")).append(i).append("'").toString(), null);
	            } while (cursor.moveToNext());
	        }
	        cursor.close();
	    }
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
