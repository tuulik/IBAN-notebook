package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import nl.garvelink.iban.Modulo97;


/**
* AddNewIBANActivity contains a form and functionality for adding a new name and IBAN pair to the
* database.
* @author Tuuli Kähkönen
*/
public class AddNewIBANActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_iban);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
    * saveIBAN validates the form and saves the new entry into the database.
    */
    public void saveIBAN(View view) {
        EditText etName = (EditText) findViewById(R.id.add_name);
        EditText etIBAN = (EditText) findViewById(R.id.add_iban);

        // Using IBAN verification from the barend/java-iban library.
        // Modulo97.verifyCheckDigits throws IllegalArgumentException if there are less than
        // five chars in the argument string.
        boolean ibanValid = false;
        try {
            ibanValid = Modulo97.verifyCheckDigits(etIBAN.getText().toString());
        }
        catch (IllegalArgumentException e) {
            ibanValid = false;
        }

        if(etName.getText().toString().length() == 0) {
            etName.setError("Name is required!");
        }

        else if(!ibanValid) {
            etIBAN.setError("Please give a valid IBAN!");
        }

        // If the form values where valid, save the new values to the database and return to the
        // ListIBANsActivity activity.
        else {
            IBANNotebookApplication app = (IBANNotebookApplication) this.getApplication();
            SQLiteDatabase db = app.getDB();

            ContentValues values = new ContentValues();
            values.put(IBANNotebookContract.Iban.COLUMN_NAME_NAME, etName.getText().toString());
            values.put(IBANNotebookContract.Iban.COLUMN_NAME_IBAN, etIBAN.getText().toString());

            db.insert(IBANNotebookContract.Iban.TABLE_NAME, null, values);
            finish();
        }
    }
}
