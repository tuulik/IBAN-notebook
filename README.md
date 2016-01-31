# IBAN-notebook 
by Tuuli Kähkönen <kahkonen.tuuli.h@student.uta.fi>

A simple Android app for storing IBAN bank account numbers.

A person's name and IBAN number can be added with a form and the IBAN is validated before saving into the database.
The list of saved IBANs is on the main view of the app. The IBANs can also be removed by clicking on the X-button
on the right side of the IBAN row on the list and the app promps for verification before the removal.

The app uses Gradle for managing packages. IBAN is validated with [Java-iban](https://github.com/barend/java-iban) library.
Gradle downloads this library when building the app.
