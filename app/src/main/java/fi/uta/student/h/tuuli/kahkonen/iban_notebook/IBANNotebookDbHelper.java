package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * IBANNotebookDbHelper implements the database used by the application and contains the database
 * name and create statements.
 * @author Tuuli Kähkönen
 */
public class IBANNotebookDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IBANNotebook.db";

    public IBANNotebookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_IBANs = "CREATE TABLE " + IBANNotebookContract.Iban.TABLE_NAME + " (" +
        IBANNotebookContract.Iban._ID + " INTEGER PRIMARY KEY, " +
        IBANNotebookContract.Iban.COLUMN_NAME_IBAN + " VARCHAR(34), " +
        IBANNotebookContract.Iban.COLUMN_NAME_NAME + " VARCHAR(100), " +
        IBANNotebookContract.Iban.COLUMN_NAME_CREATED + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
        " )";
        db.execSQL(sql_create_IBANs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
