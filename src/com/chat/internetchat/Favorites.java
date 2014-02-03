package com.chat.internetchat;



import java.util.ArrayList;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class Favorites extends SherlockActivity {
	private ListView fav_listView;
	private ArrayList<String> fav_list;
	private ArrayAdapter<String> adapter;
	private String img_url;
	private static String _imgUrl = "img_url";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//Log.i("inside honeycomb ", "here am i");
		}
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.favorites);
	//	TextView text = (TextView) findViewById(R.id.label);
		
		Intent i = getIntent();
		
		img_url = i.getStringExtra(_imgUrl);
		
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowTitleEnabled(true);
	    actionBar.setTitle("My Favorites");
	    actionBar.setDisplayHomeAsUpEnabled(true);

//	    ImageLoader imgLoader;
	   // ImageView logo = (ImageView) findViewById(android.R.id.home);
	//    imgLoader = new ImageLoader(this.getApplicationContext());
	//    imgLoader.DisplayImage(img_url, logo);
		
	fav_list = i.getExtras().getStringArrayList("favourites");
		//text.setText(fav_list.size()+"");
		
		fav_listView = (ListView)findViewById(R.id.listviewer);
		Log.i("Favorites ", fav_listView+"");
		adapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.label, fav_list);
		fav_listView.setAdapter(adapter);  
}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.favorites, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         // This ID represents the Home or Up button. In the case of this
	         // activity, the Up button is shown. Use NavUtils to allow users
	         // to navigate up one level in the application structure. For
	         // more details, see the Navigation pattern on Android Design:
	         //
	         // http://developer.android.com/design/patterns/navigation.html#up-vs-back
	         //
	      //   NavUtils.navigateUpTo(this,
	       //        new Intent(this, ListItemDetail.class));
	    	
	    		    finish();
	    		  
	         return true;
	   }
	   return super.onOptionsItemSelected(item);
	}
}