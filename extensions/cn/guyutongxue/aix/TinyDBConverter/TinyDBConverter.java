// Copyright 2017-2018 Guyutongxue
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package cn.guyutongxue.aix.TinyDBConverter;

import gnu.lists.LList;
import gnu.lists.Pair;

import org.json.JSONException;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.content.DialogInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.util.Base64;

import org.json.*;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.*;

@DesignerComponent(version = TinyDBConverter.VERSION,
    description = "This component can convert TinyDB's data to a JSON string,"+
    				" or convert JSON string to a certain TinyDB."+
    				"<br/> It can also en/decode from/to Base-64."+
    				"<br/> Made by Guyutongxue.",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "images/extension.png")

@SimpleObject(external = true)
public class TinyDBConverter extends AndroidNonvisibleComponent implements Component {
	public static final int VERSION = 1;
	private ComponentContainer container;
    private Activity activity;
    public TinyDBConverter(ComponentContainer container){
        super(container.$form());
        this.container = container;
        activity = container.$context();
    }
	
	private void alert(Activity activity,String message, String title, String buttonText){
		AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
		alertDialog.setTitle(title);
		// prevents the user from escaping the dialog by hitting the Back button
		alertDialog.setCancelable(false);
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
			buttonText, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
    		}});
    	alertDialog.show();
	}
	
	@SimpleFunction(description = "Convert TinyDB's data to a JSON string.")
	public String TinyDBToJson(TinyDB tinyDB){
		try{
			StringBuilder json = new StringBuilder();
			json.append("{");
			List<String> lsTag = (List<String>)tinyDB.GetTags();
			String separator = "";
			for(int i=0;i<lsTag.size();i++){
				json.append(separator).append("\"").append(lsTag.get(i)).append("\":");
				json.append(JsonUtil.getJsonRepresentation(tinyDB.GetValue(lsTag.get(i),null)));
				separator = ",";
			}
			json.append("}");
			return json.toString();
		}
		catch(Exception ex){
			alert(activity, "Error: "+ ex.getMessage(), "Error", "OK");
			return new String();
		}
	}
	
	@SimpleFunction(description = "Convert JSON string to a certain TinyDB. If fails, it'll throw an alert.")
	public void JsonToTinyDB(String jsonString, TinyDB tinyDB){
		String backup = TinyDBToJson(tinyDB);
		try{
			tinyDB.ClearAll();
			JSONObject jsonObj = new JSONObject(jsonString);
			Iterator itrt= jsonObj.keys();
			while(itrt.hasNext()){
				String key = (String) itrt.next();
				Object obj = JsonUtil.getObjectFromJson(jsonObj.getString(key));
				tinyDB.StoreValue(key,obj);
			}
		}
		catch(Exception ex){
			alert(activity, "Error: "+ ex.getMessage(), "Error", "OK");
			//If thing goes wrong, reconvert from the original.
			JsonToTinyDB(backup,tinyDB);
		}
	}
	
	@SimpleFunction(description = "Convert TinyDB's data to a Base64ed JSON string.")
	public String TinyDBToBase64String(TinyDB tinyDB){
		String str = TinyDBToJson(tinyDB);
		byte[] b = null;
		try{
            b = str.getBytes("utf-8");
            if(b != null){  
            	str = new String(Base64.encode(b,Base64.DEFAULT));  
        	}
        	return str;
        }
        catch(Exception ex){
             alert(activity, "Error: "+ ex.getMessage(), "Error", "OK");
             return new String();
        }
    }
    
    @SimpleFunction(description = "Convert Base64ed JSON string to a certain TinyDB. If fails, it'll throw an alert.")
    public void Base64StringToTinyDB(String base64String, TinyDB tinyDB){
    	String backup = TinyDBToJson(tinyDB);
		try{
			byte b[] = Base64.decode(base64String, Base64.DEFAULT);
			String str = new String(b);
			JsonToTinyDB(str,tinyDB);
		}
		catch(Exception ex){
			alert(activity, "Error: "+ ex.getMessage(), "Error", "OK");
			//If thing goes wrong, reconvert from the original.
			JsonToTinyDB(backup,tinyDB);
		}
	}
    	
}