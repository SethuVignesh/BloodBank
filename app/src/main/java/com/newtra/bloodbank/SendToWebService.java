//package com.newtra.bloodbank;
///*
//    Developed By : prasob kumar k.p
//    Created : 7-Jan-2014 04:00 PM
//    Last Changed : 11-03-2014 03:50 PM
// */
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.AsyncTask;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.protocol.HTTP;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class SendToWebService extends AsyncTask<String,String,String> {
//
//	String responseData = " Error : netPrerequisites is become false ";
//	boolean netPrerequisites = true;
//
//
//private Context _context;
//
//public SendToWebService(Context context){
//	this._context = context;
//}
//	public boolean isConnectingToInternet(){
//		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		  if (connectivity != null)
//		  {
//			  NetworkInfo[] info = connectivity.getAllNetworkInfo();
//			  if (info != null)
//				  for (int i = 0; i < info.length; i++)
//					  if (info[i].getState() == NetworkInfo.State.CONNECTED)
//					  {
//						  return true;
//					  }
//
//		  }
//		  return false;
//	}
//	// Helper method to execute pre-requirements.
//	@Override
//	protected void onPreExecute() {
//		super.onPreExecute();
//	}
//	@Override
//
//	public String doInBackground(String... params) {
//
//		try{
//			//Extracting params
////			String prefixUrl = "http://111.118.183.98:15100/VMwebservice.asmx/"; //Main Server
//			String prefixUrl = "http://115.119.115.78:15100/VMwebservice.asmx/"; // Local Server
//			String methodToBeCalled = params[0];// Value specifies which method to be called.
//			String url	  = prefixUrl+params[1];//Server url of the specific method to be called.
//
//			int methodNo=Integer.parseInt(methodToBeCalled);
//
//
//			// Creating an HTTP Post call, and passing the URL
//			HttpPost httpPost = new HttpPost(url);
//
//			httpPost.setHeader("content-type","application/json");
//			HttpClient httpClient = new DefaultHttpClient(getHttpParameterObj(9000,9000));
//
//			switch(methodNo){
//
//			case 1  :   ManageAdvanceDetailsTable(params[2], params[3], params[4], params[5], params[6], params[7], params[8]);
//			break;
//
//			case 2  :   ManageLocationTable(params[2],params[3],params[4],params[5],params[6],params[7], params[8]);
//			break;
//
//			case 3  :   deleteAnEmployee(params[2]);
//			break;
//
//			case 4  :   deleteVehicle(params[2]);
//			break;
//
//			case 5  :   getCleanerPaymentDetails(params[2], params[3], params[4]);
//			break;
//
//			case 6  :   getDriverPaymentDetails(params[2], params[3], params[4]);
//			break;
//
//			case 7  :   getVehiclePaymentDetails(params[2], params[3], params[4]);
//			break;
//
//			case 8  :   manageVehicle(params[2],params[3],params[4],params[5],params[6], params[7]);
//             break;
//
//
//			case 9  :   registerAnApplication(params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10]);
//			break;
//
//			case 10 :   ManageAnEmployee(params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11]);
//			break;
//
//			case 11 :   saveSourceDestinationDetails(params[2], params[3], params[4], params[5], params[6], params[7], params[8]);
//			break;
//
//			case 12 :   SaveUserDeviceMappingDetails(params[2], params[3]);
//			break;
//
//			case 13 :   syncTableDriverMileage();
//			break;
//
//			case 14 :   syncTableVehicleMileage();
//			break;
//
//			case 15 :   trackingVehicleTrip(params[2]);
//			break;
//
//			case 16 :   insertToVehicleFuel(params[2],params[3],params[4],params[5],params[6]);
//			break;
//
//			case 17 :   deleteLocation(params[2]);
//			break;
//			case 18:    managePaymentDetails(params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11]);
//			break;
//			default :   defaultMethodCall();
//			break;
//			}
//
//			if(netPrerequisites)
//			{
//				try{
//
//					StringEntity entity = new StringEntity(data.toString(), HTTP.UTF_8);
//					httpPost.setEntity(entity);
//
//					//publishProgress("Progress Update : Connecting to server..");
//
//					// Creating the http call.
//					HttpResponse response = httpClient.execute(httpPost);
//					//publishProgress("Progress Update : Connected to server!");
//
//					//Getting data from the response to see if it was a success.
//					BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//					String jsonResultStr = reader.readLine();
//					data = new JSONObject(jsonResultStr);
//
//					if(data.toString() == ""){
//						responseData = "";
//					}
//					else{
//						responseData = data.toString();
//					}
//				}catch (Exception e){
//					responseData = "Custom Exception in netPrerequisites Checks, Details : ==> "+e.getStackTrace();
//				}
//			}
//		}catch (Exception e){
//			responseData = "Custom Exception in doInBackground(String... params), Details : ==> "+e.getStackTrace();
//		}
//		return responseData;
//	}
//
//
//	// GET HTTP PARAMETER OBJECT METHOD!
//
//	/*
//	 * Build HTTP Parameters, such as timeOut.
//	 * @param timeOutConnection
//	 * @param timeOutSocket
//	 * @return
//	 */
//
//	public HttpParams getHttpParameterObj(int timeOutConnection,int timeOutSocket) {
//
//		HttpParams httpParameters = new BasicHttpParams();
//		try{
//			// Set the timeout in milliseconds until a connection is established.
//			HttpConnectionParams.setConnectionTimeout(httpParameters, timeOutConnection);
//			// Set the default socket timeout (SO_TIMEOUT)
//			// in milliseconds which is the timeout for waiting for data.
//			HttpConnectionParams.setSoTimeout(httpParameters, timeOutSocket);
//		}catch (Exception e){
//			responseData = "Custom Exception in getHttpParameterObj(...), Details : ==> "+e.getStackTrace();
//			netPrerequisites = false;
//		}
//		return httpParameters;
//	}
//
//	@Override
//	protected void onProgressUpdate(String... values) {
//		super.onProgressUpdate(values);
//
//		//code goes here
//	}
//	JSONObject data = new JSONObject();
//
//
//
//	//-----------------------------------------------------
//	//Method:1 in Switch Case
//	public void ManageAdvanceDetailsTable(String employeeId, String vehicleNumber, String advanceType, String advanceDate, String voucherNumber, String amount, String statusFlag ){
//
//		try{
//			data.put("employeeId", employeeId);
//			data.put("vehicleNumber", vehicleNumber);
//			data.put("advanceType", advanceType);
//			data.put("advanceDate", advanceDate);
//			data.put("voucherNumber", voucherNumber);
//			data.put("amount", amount);
//			data.put("statusFlag", statusFlag);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in AddToAdvanceDetailsTable(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:2 in Switch Case
//	public void ManageLocationTable(String locationName, String locationType, String locationLatitude, String locationLongitude, String distance, String amount, String statusFlag){
//		try {
//			data.put("locationName", locationName);
//			data.put("locationType", locationType);
//			data.put("locationLatitude", locationLatitude);
//			data.put("locationLongitude", locationLongitude);
//			data.put("distance", distance);
//			data.put("amount", amount);
//			data.put("statusFlag",statusFlag);
//
//		} catch (JSONException e) {
//			responseData = "Custom Exception in AddToLocationTable(...), Details : ==> "+e.getStackTrace();
//		}
//
//	}
//	//Method:3 in Switch Case
//	public void deleteAnEmployee(String employeeId){
//		try {
//			data.put("employeeId", employeeId);
//
//		} catch (JSONException e) {
//			responseData = "Custom Exception in deleteAnEmployee(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:4 in Switch Case
//	public void deleteVehicle(String vehicleNumber){
//		try {
//			data.put("vehicleNumber", vehicleNumber);
//
//		} catch (JSONException e) {
//			responseData = "Custom Exception in deleteVehicle(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:5 in Switch Case
//	public void getCleanerPaymentDetails(String employeeId, String fromDateTime,String toDateTime){
//		try {
//			data.put("employeeId", employeeId);
//			data.put("fromDateTime", fromDateTime);
//			data.put("toDateTime",toDateTime);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in getCleanerPaymentDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//	//Method:6 in Switch Case
//	public void getDriverPaymentDetails(String employeeId, String fromDateTime,String toDateTime){
//		try {
//			data.put("employeeId", employeeId);
//			data.put("fromDateTime", fromDateTime);
//			data.put("toDateTime",toDateTime);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in getDriverPaymentDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:7 in Switch Case
//	public void getVehiclePaymentDetails(String vehicleNumber,String fromDateTime,String toDateTime){
//		try {
//			data.put("vehicleNumber", vehicleNumber);
//			data.put("fromDateTime", fromDateTime);
//			data.put("toDateTime",toDateTime);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in getVehiclePaymentDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:8 in Switch Case
//	private void manageVehicle(String vehicleNumber, String vehicleType,String vehicleMileage,String IMEINumber, String deviceMobNumber,String statusFlag) {
//		try {
//			data.put("vehicleNumber", vehicleNumber);
//			data.put("vehicleType", vehicleType);
//			data.put("stdMileage", vehicleMileage);
//			data.put("deviceIMEINumber",IMEINumber);
//			data.put("statusFlag", statusFlag);
//			data.put("deviceMobNumber", deviceMobNumber);
//
//		} catch (JSONException e) {
//			responseData = "Custom Exception in trackingVehicleTrip(...), Details : ==> "+e.getStackTrace();
//		}
//
//	}
//
//	//Method:9 in Switch Case
//	public void registerAnApplication(String employeeType, String employeeName, String emailId, String employeeLicenceNumber, String employeePhoneNumber, String employeeAddress,String commission,String salary, String productKey){
//
//		try {
//			data.put("employeeType", employeeType);
//			data.put("employeeName", employeeName);
//			data.put("emailId", emailId);
//			data.put("employeeLicenceNumber", employeeLicenceNumber);
//			data.put("employeePhoneNumber", employeePhoneNumber);
//			data.put("employeeAddress", employeeAddress);
//			data.put("commission", commission);
//			data.put("salary", salary);
//			data.put("productKey", productKey);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in registerAnApplication(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//
//	//Method:10 in Switch Case
//	public void ManageAnEmployee(String employeeType, String employeeName, String emailId, String employeeLicenceNumber, String employeePhoneNumber, String employeeAddress, String commission, String salary, String statusFlag, String UpdateEmpId){
//		try {
//			data.put("employeeType", employeeType);
//			data.put("employeeName", employeeName);
//			data.put("emailId", emailId);
//			data.put("employeeLicenceNumber", employeeLicenceNumber);
//			data.put("employeePhoneNumber", employeePhoneNumber);
//			data.put("employeeAddress", employeeAddress);
//			data.put("commission", commission);
//			data.put("salary",salary);
//			data.put("statusFlag", statusFlag);
//			data.put("updateEmpId", UpdateEmpId);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in registerAnEmployee(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:11 in Switch Case
//	public void saveSourceDestinationDetails(String driverEmpId, String vehicleNumber, String cleanerEmpId, String sourceName, String destinationName, String voucherNumber, String dateTime){
//		try {
//			data.put("driverEmpId", driverEmpId);
//			data.put("vehicleNumber", vehicleNumber);
//			data.put("cleanerEmpId", cleanerEmpId);
//			data.put("sourceName", sourceName);
//			data.put("destinationName", destinationName);
//			data.put("voucherNumber", voucherNumber);
//			data.put("dateTime", dateTime);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in saveSourceDestinationDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:12 in Switch Case
//	public void SaveUserDeviceMappingDetails(String mobile, String gcmRegistrationId){
//		try {
//			data.put("mobile", mobile);
//			data.put("GcmRegistrationId", gcmRegistrationId);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in saveUserDeviceMappingDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:13 in Switch Case
//	public void syncTableDriverMileage(){
//		//No data have to be passed
//	}
//
//	//Method:14 in Switch Case
//	public void syncTableVehicleMileage(){
//		//No data have to be passed
//	}
//
//	//Method:15 in Switch Case
//	public void trackingVehicleTrip(String vehicleNumber){
//		try {
//			data.put("vehicleNumber", vehicleNumber);
//
//		} catch (JSONException e) {
//			responseData = "Custom Exception in trackingVehicleTrip(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:16 in Switch Case
//	public void insertToVehicleFuel(String vehicleNumber, String driverId, String distance, String fuel, String date){
//		try {
//			data.put("vehicleNumber", vehicleNumber);
//			data.put("driverId",driverId);
//			data.put("distance",distance);
//			data.put("fuel",fuel);
//			data.put("date",date);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in trackingVehicleTrip(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:17 in Switch Case
//	public void deleteLocation(String locationName){
//		try {
//			data.put("locationName", locationName);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in deleteLocation(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//	//Method:18 in Switch Case
//	public void  managePaymentDetails(String employeeId, String voucherNumber, String fromDate, String toDate, String commission,  String paymentAmount, String paymentDate ,String amountDeduction, String mileageDeduction, String statusFlag){
//		try {
//			data.put("employeeId", employeeId);
//			data.put("voucherNumber", voucherNumber);
//			data.put("fromDate", fromDate);
//			data.put("toDate", toDate);
//			data.put("commission",commission);
//			data.put("paymentAmount", paymentAmount);
//			data.put("paymentDate", paymentDate);
//			data.put("amountDeduction",amountDeduction);
//			data.put("mileageDeduction",mileageDeduction);
//			data.put("statusFlag", statusFlag);
//		} catch (JSONException e) {
//			responseData = "Custom Exception in managePaymentDetails(...), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//
//	//Method:19 in Switch Case
//	public void defaultMethodCall(){
//		try {
//			responseData = "Custom Error : Default method called in Switch [ Reason : 'methodNo' Value is invalid in send.execute(params..) method]";
//			netPrerequisites = false;   // To avoid http post in case of invalid data.
//
//		} catch (Exception e) {
//			responseData = "Custom Exception in defaultMethodCall(), Details : ==> "+e.getStackTrace();
//		}
//	}
//
//}