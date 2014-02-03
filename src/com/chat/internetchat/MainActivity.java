package com.chat.internetchat;

import java.util.ArrayList;
import java.util.Locale;

import android.R.menu;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
//import com.internetchat.instachat.DataProvider;

public class MainActivity extends SherlockActivity implements OnItemClickListener/*, LoaderManager.LoaderCallbacks<Cursor> */{
static final String KEY_SONG = "Parent_node"; // parent node
static final String KEY_ID = "id";
static final String KEY_NAME = "name";
static final String KEY_LATEST_MSG = "latest_msg";
static final String KEY_TIME = "time";
static final String KEY_THUMB_URL = "thumb_url";

ListView list;
ListViewAdapter adapter;
EditText editsearch;

ArrayList<ListItemInfo> usersList = new ArrayList<ListItemInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//Log.i("inside honeycomb ", "here am i");
		}
	//	
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		// looping through all sample data
		for (int i = 0; i < 10; i++) {
			// creating new HashMap
			/*HashMap<String, String> map = new HashMap<String, String>();
			//Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, i+"");
			map.put(KEY_NAME, "Name "+i);
			map.put(KEY_LATEST_MSG, "Latest Message "+i);
			map.put(KEY_TIME, i+":00");*/
			//map.put(KEY_THUMB_URL, "URL PATH");
			ListItemInfo lii = new ListItemInfo("", i+"", "name "+i, "Latest Message "+i, i+":00", "URL PATH", 0);
			// adding HashList to ArrayList
			usersList.add(lii);
		}
		
		list = (ListView) findViewById(R.id.listview1);
		
		// Getting adapter by passing data ArrayList
        adapter=new ListViewAdapter(this, usersList);        
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(this);
		/*mchat.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in = new Intent(MainActivity.this,ChatScreen.class);
		startActivity(in);
			 
			
		}});*/
	}
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
            // Then you start a new Activity via Intent
            Intent intent = new Intent();
            intent.setClass(this, ListItemDetail.class);
            intent.putExtra("position", position);
            intent.putExtra("name",
					(usersList.get(position).getName()));
			// Pass all data country
			intent.putExtra("img_url",
					(usersList.get(position).getThumbURL()));
            // Or / And
            intent.putExtra("id", id);
            startActivity(intent);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getSupportMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        
     // Locate the EditText in menu.xml
        editsearch = (EditText) menu.findItem(R.id.menu_search).getActionView();
        System.out.print(editsearch+"");
        Log.i("Text Here", "editsearch "+editsearch);
        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {
    		
        	@Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
               /* String text = editsearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                showToast();
                Log.i("Text Here", text+" Before filter "+usersList.size());
                adapter.filter(text);
                Log.i("Text Here", text+" after filter "+usersList.size());*/
            }
     
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
     
            }
     
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
            	String text = editsearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                showToast();
                Log.i("Text Here", text+" Before filter "+usersList.size());
                adapter.filter(text);
                Log.i("Text Here", text+" after filter "+usersList.size());
                // TODO Auto-generated method stub
     
            } }
     
        );
 
        // Show the search menu item in menu.xml
        MenuItem menuSearch = menu.findItem(R.id.menu_search);
 
        menuSearch.setOnActionExpandListener(new OnActionExpandListener() {
 
            // Menu Action Collapse
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Empty EditText to remove text filtering
                editsearch.setText("Hello");
                editsearch.clearFocus();
                return true;
            }
 
            // Menu Action Expand
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Focus on EditText
                editsearch.requestFocus();
 
                // Force the keyboard to show on EditText focus
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                return true;
            }
        });
        
     // Show the settings menu item in menu.xml
        MenuItem menuSettings = menu.findItem(R.id.action_settings);
 
        // Capture menu item clicks
        menuSettings.setOnMenuItemClickListener(new OnMenuItemClickListener() {
 
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // TODO Auto-generated method stub
                // Do something here
                Toast.makeText(getApplicationContext(), "Nothing here!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
 
        });
        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return true;
    }
	
	// EditText TextWatcher
   // private TextWatcher textWatcher = 
 
        public void showToast()
        {
        	//Toast.makeText(this, "before filter", Toast.LENGTH_SHORT);
        }
        
        public void showToast1()
        {
        	//Toast.makeText(this, "after filter", Toast.LENGTH_SHORT);
        }
        
       /* @Override
    	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    		CursorLoader loader = new CursorLoader(this, 
    				DataProvider.CONTENT_URI_PROFILE, 
    				new String[]{DataProvider.COL_ID, DataProvider.COL_NAME, DataProvider.COL_COUNT}, 
    				null, 
    				null, 
    				DataProvider.COL_ID + " DESC"); 
    		return loader;
    	}

    	@Override
    	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    		adapter.swapCursor(data);
    	}

    	@Override
    	public void onLoaderReset(Loader<Cursor> loader) {
    		adapter.swapCursor(null);
    	}	*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_refresh:
                Toast.makeText(this, "Fake refreshing...", Toast.LENGTH_SHORT).show();
                getActionBarHelper().setRefreshActionItemState(true);
                getWindow().getDecorView().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                getActionBarHelper().setRefreshActionItemState(false);
                            }
                        }, 1000);
                break;

            case R.id.menu_search:
                Toast.makeText(this, "Tapped search", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }*/
	/*public boolean onKeyDown(int keycode, KeyEvent event) {
	    if (keycode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(true);
	    }
	    return super.onKeyDown(keycode, event);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
