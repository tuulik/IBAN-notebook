package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * IBANNotebookApplication is used to open database connection on application startup.
 * The handle is kept open and available to all the actions of the application to avoid
 * opening and closing the connection.
 * @author Tuuli Kähkönen
 */
public class IBANNotebookApplication extends Application {

    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        IBANNotebookDbHelper helper = new IBANNotebookDbHelper(this);
        db = helper.getWritableDatabase();
    }

    /**
     * @return Database handle.
     */
    public SQLiteDatabase getDB() {
        return db;
    }

}
