package fi.uta.student.h.tuuli.kahkonen.iban_notebook;

import android.provider.BaseColumns;

/**
 * IBANNotebookContract contains table name and column names for the database used by the
 * application.
 * @author Tuuli Kähkönen
 */
public class IBANNotebookContract {

    public IBANNotebookContract() {}

    public static abstract class Iban implements BaseColumns {
        public static final String TABLE_NAME = "iban_account";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IBAN = "iban";
        public static final String COLUMN_NAME_CREATED = "created";
    }
}
