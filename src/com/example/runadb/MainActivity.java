package com.example.runadb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public void onStart(){
		String command = "uiautomator runtest /data/local/tmp/AutoRunner.jar  -c com.Runner";
		boolean ret = execCommands(command);

		return;
		/*
		ProcessBuilder pb = new ProcessBuilder("uiautomator", "runtest", "/data/local/tmp/AutoRunner.jar", "-c", "com.Runner");
		//ProcessBuilder pb = new ProcessBuilder("ls -l");
		try {
			String line = "";
			Process p = pb.start();
			p.waitFor();
			BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ( (line = br2.readLine()) != null)
		        System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
	public Boolean execCommands(String... command) {
	    try {
	        Runtime rt = Runtime.getRuntime();
	        Process process = null;
	        try{
	        	process = rt.exec("ps", null, null);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        
	        DataOutputStream os = new DataOutputStream(process.getOutputStream());

	        for(int i = 0; i < command.length; i++) {
	            os.writeBytes(command[i] + "\n");
	            os.flush();
	        }
	        os.writeBytes("exit\n");
	        os.flush();
	        process.waitFor();
	    } catch (IOException e) {
	        return false;
	    } catch (InterruptedException e) {
	        return false;
	    }
	    return true; 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
