package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ListIBANsActivity is the launcher activity of the application. It contains the listing of
 * all the saved IBANs on the database and a method for deleting an IBAN from the database.
 * @author Tuuli Kähkönen
 */
public class ListIBANsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ibans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpListView();
    }

    /**
     * setUpListView queries the database for all IBAN entries and sets them up in a ListView.
     */
    private void setUpListView() {
        IBANNotebookApplication app = (IBANNotebookApplication) this.getApplication();
        SQLiteDatabase db = app.getDB();

        String[] projection = {
                IBANNotebookContract.Iban._ID,
                IBANNotebookContract.Iban.COLUMN_NAME_NAME,
                IBANNotebookContract.Iban.COLUMN_NAME_IBAN
        };

        Cursor c = db.query(
                IBANNotebookContract.Iban.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                IBANNotebookContract.Iban.COLUMN_NAME_CREATED + " DESC"
        );

        final ListView lvIbans = (ListView) findViewById(R.id.list);
        IBANNotebookCursorAdapter adapter = new IBANNotebookCursorAdapter(this, c);
        lvIbans.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_ibans, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * addNewIBAN starts the AddNewIBANActivity.
     */
    public void addNewIBAN(View view) {
        Intent intent = new Intent(this, AddNewIBANActivity.class);
        startActivity(intent);
    }

    /**
     * deleteIBAN starts a confirmation dialog for deleting the IBAN and if the user confirms,
     * deletes the IBAN from the database.
     */
    public void deleteIBAN(View view) {

        // Finalize view and activity so they can be called from the anonymous inner class.
        final View VIEW = view;
        final ListIBANsActivity ACTIVITY = this;

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    // Deletes the IBAN from the database.
                    case DialogInterface.BUTTON_POSITIVE: {
                        IBANNotebookApplication app = (IBANNotebookApplication) ACTIVITY.getApplication();
                        SQLiteDatabase db = app.getDB();

                        RelativeLayout row = (RelativeLayout) VIEW.getParent();
                        // First TextView of the row is tagged with the row id.
                        TextView child = (TextView) row.getChildAt(0);
                        int index = (Integer) child.getTag();

                        String selection = IBANNotebookContract.Iban._ID + " LIKE ?";
                        String[] selectionArgs = {String.valueOf(index)};
                        db.delete(IBANNotebookContract.Iban.TABLE_NAME, selection, selectionArgs);

                        // Data has changed, so the ListView has to be updated.
                        setUpListView();
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Do you want to delete the IBAN?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
