package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * IBANNotebookCursorAdapter helps with constructing the rows of the ListIBANsActivity from the
 * data in the database.
 * @author Tuuli Kähkönen
 */
public class IBANNotebookCursorAdapter extends CursorAdapter {

    public IBANNotebookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.iban_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvIBAN = (TextView) view.findViewById(R.id.iban);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(IBANNotebookContract.Iban.COLUMN_NAME_NAME));
        String iban = cursor.getString(cursor.getColumnIndexOrThrow(IBANNotebookContract.Iban.COLUMN_NAME_IBAN));

        tvName.setText(name);
        tvIBAN.setText(iban);

        // Add row id to the name TextView to be able to reference the row later.
        tvName.setTag(cursor.getInt(cursor.getColumnIndexOrThrow(IBANNotebookContract.Iban._ID)));
    }

}
