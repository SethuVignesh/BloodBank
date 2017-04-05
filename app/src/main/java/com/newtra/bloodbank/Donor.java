package com.newtra.bloodbank;

public class Donor {

	// Variables
	private int _id;
	private String _name;
	private String _phone_number;
	private String _email;
	private String _postal_address;
//	private String _photograph;
    private String _bloodgroup;
    private String _dob;
    private String _distance;

	// Constructor
	public Donor(){

	}

    public String get_bloodgroup() {
        return _bloodgroup;
    }

    public void set_bloodgroup(String _bloodgroup) {
        this._bloodgroup = _bloodgroup;
    }

    public String get_dob() {
        return _dob;
    }

    public void set_dob(String _dob) {
        this._dob = _dob;
    }

    public String get_distance() {
        return _distance;
    }

    public void set_distance(String _distance) {
        this._distance = _distance;
    }

    // Constructor
	public Donor(String name, String phone_number, String email, String postal_address,String bloodgroup, String dob,String distance){
		this._name = name;
		this._phone_number = phone_number;
		this._email = email;
		this._postal_address = postal_address;
//		this._photograph = photograph;
        this._distance = distance;

        this._bloodgroup = bloodgroup;
        this._dob = dob;

	}
	
	// ID getter and setter functions
	public int getID(){
		return _id;
	}
		
	public void setID(int id){
		this._id = id;
	}
	
	// Name getter and setter functions
	public String getName(){
		return this._name;
	}
		
	public void setName(String name){
		this._name = name;
	}
	
	// Phone Number getter and setter functions
	public String getPhoneNumber(){
		return this._phone_number;
	}
		
	public void setPhoneNumber(String phone_number){
		this._phone_number = phone_number;
	}
	
	// Email getter and setter functions
	public String getEmail(){
		return this._email;
	}
		
	public void setEmail(String email){
		this._email = email;
	}
	
	// Postal Address getter and setter functions
	public String getPostalAddress(){
		return this._postal_address;	
	}	
	
	public void setPostalAddress(String postal_address){
		this._postal_address = postal_address;
	}
	
//	// Photograph getter and setter functions
//	public String getPhotograph(){
//		return this._photograph;
//	}
//
//	public void setPhotograph(String photograph){
//		this._photograph = photograph;
//	}
}