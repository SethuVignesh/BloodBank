// ContactHandler
package com.newtra.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

	// All variables about DB
	// Database name
	private static final String DATABASE_NAME = "contactBook";

	// Database version
	private static final int DATABASE_VERSION = 1;

	// Contacts table name
	private static final String TABLE_CONTACT = "contacts";

	// Table Column names
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_PHONE = "phone";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_ADDRESS = "address";
	private static final String COLUMN_PHOTOGRAPH = "photograph";
	private static final String COLUMN_DISTANCE = "distance";
	private static final String COLUMN_BLOOD_GROUP = "bloodgroup";
	private static final String COLUMN_DOB = "dob";

	private String[] columns = { COLUMN_ID, COLUMN_NAME, COLUMN_PHONE,
			COLUMN_EMAIL, COLUMN_ADDRESS, COLUMN_DISTANCE, COLUMN_BLOOD_GROUP,
			COLUMN_DOB };

	// Create database
	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Create table
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACT + "(" + COLUMN_ID
				+ " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
				+ COLUMN_PHONE + " TEXT," + COLUMN_EMAIL + " TEXT,"
				+ COLUMN_ADDRESS + " TEXT,"
				// + COLUMN_PHOTOGRAPH + " TEXT"
				+ COLUMN_DISTANCE + " TEXT," + COLUMN_BLOOD_GROUP + " TEXT,"
				+ COLUMN_DOB + " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
	}

	// Drop table if older version exist
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		onCreate(db);
	}

	/*
	 * Handling Contact table using sql queries.
	 */

	// Add Contact
	public boolean addContactDetails(Donor contact) {
		// Get db writable
		SQLiteDatabase db = this.getWritableDatabase();

		// Get the values to insert
		ContentValues vals = new ContentValues();
		vals.put(COLUMN_NAME, contact.getName());
		vals.put(COLUMN_PHONE, contact.getPhoneNumber());
		vals.put(COLUMN_EMAIL, contact.getEmail());
		vals.put(COLUMN_ADDRESS, contact.getPostalAddress());
		// vals.put(COLUMN_PHOTOGRAPH, contact.getPhotograph());
		vals.put(COLUMN_DISTANCE, contact.get_distance());
		vals.put(COLUMN_DOB, contact.get_dob());
		vals.put(COLUMN_BLOOD_GROUP, contact.get_bloodgroup());

		// Insert values into table
		long i = db.insert(TABLE_CONTACT, null, vals);
		// Close database
		db.close();

		if (i != 0) {
			return true;
		} else {
			return false;
		}
	}

	// Reading all contacts
	public List<Donor> readAllContacts() {
		// Get db writable
		SQLiteDatabase db = this.getWritableDatabase();

		// Define contacts list
		List<Donor> contacts = new ArrayList<Donor>();

		Cursor cursor = db.query(TABLE_CONTACT, columns, null, null, null,
				null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Donor contact = new Donor();
			contact.setID(Integer.parseInt(cursor.getString(0)));
			contact.setName(cursor.getString(1));
			contact.setPhoneNumber(cursor.getString(2));
			contact.setEmail(cursor.getString(3));
			contact.setPostalAddress(cursor.getString(4));
			// contact.setPhotograph(cursor.getString(5));

			contact.set_distance(cursor.getString(5));
			contact.set_bloodgroup(cursor.getString(6));
			contact.set_dob(cursor.getString(7));

			contacts.add(contact);
			cursor.moveToNext();
		}

		// Make sure to close the cursor
		cursor.close();
		return contacts;
	}

	// Reading all contacts
	public List<Donor> readContactsParticular(String bloodGroup) {
		// Get db writable
		SQLiteDatabase db = this.getWritableDatabase();

		// Define contacts list
		List<Donor> contacts = new ArrayList<Donor>();

		Cursor cursor = db.query(TABLE_CONTACT, columns, COLUMN_BLOOD_GROUP
				+ "=?", new String[] { bloodGroup }, null, null, null, null);
		// Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
		// KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
		// new String[] { String.valueOf(id) }, null, null, null, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Donor contact = new Donor();
			contact.setID(Integer.parseInt(cursor.getString(0)));
			contact.setName(cursor.getString(1));
			contact.setPhoneNumber(cursor.getString(2));
			contact.setEmail(cursor.getString(3));
			contact.setPostalAddress(cursor.getString(4));
			// contact.setPhotograph(cursor.getString(5));

			contact.set_distance(cursor.getString(5));
			contact.set_bloodgroup(cursor.getString(6));
			contact.set_dob(cursor.getString(7));

			contacts.add(contact);
			cursor.moveToNext();
		}

		// Make sure to close the cursor
		cursor.close();
		return contacts;
	}

	// Update contact contact
	public boolean editContact(Donor contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues vals = new ContentValues();
		vals.put(COLUMN_NAME, contact.getName());
		vals.put(COLUMN_PHONE, contact.getPhoneNumber());

		// updating row
		int i = db.update(TABLE_CONTACT, vals, COLUMN_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });

		db.close();

		if (i != 0) {
			return true;
		} else {
			return false;
		}

	}

	// Deleting contact
	public boolean deleteContact(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		int i = db.delete(TABLE_CONTACT, COLUMN_ID + " = ?",
				new String[] { String.valueOf(id) });

		db.close();

		if (i != 0) {
			return true;
		} else {
			return false;
		}
	}

}
