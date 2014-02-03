package com.chat.internetchat;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

public class ListItemDetail extends SherlockListActivity {

	/** Items entered by the user is stored in this ArrayList variable */
	private ArrayList<String> listArr = new ArrayList<String>();
	private ArrayList<String> favourites = new ArrayList<String>();
	// private Editable chats;
	/** Declaring an ArrayAdapter to set items to ListView */
	private ArrayAdapter<String> adapter;
	private EditText edit, editsearch;
	private String texted;
	private LinearLayout titleText;
	private static String _name = "name";
	private static String _imgUrl = "img_url";
	String name;
	String img_url;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//Log.i("inside honeycomb ", "here am i");
		}
		//setTheme(R.style.Theme_Sherlock);
		setTheme(R.style.Theme_Sherlock_Light);
		// setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);

		setContentView(R.layout.activity_chatscreen);
		registerForContextMenu(getListView());
		//ImageLoader imgLoader;
	//	ImageView logo = (ImageView) findViewById(android.R.id.home);
	//	imgLoader = new ImageLoader(this.getApplicationContext());
		// logo.setImageDrawable(drawable);

		Intent intent = getIntent();
		// int position = intent.getIntExtra("position", 0);
		name = intent.getStringExtra(_name);
		img_url = intent.getStringExtra(_imgUrl);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.setTitle(name);
		// actionBar.setIcon(R.drawable.audio_shortcut);
		actionBar.setDisplayHomeAsUpEnabled(true);
		// imgLoader.DisplayImage(img_url, logo);
		// Here we turn your string.xml in an array
		// String[] myKeys = getResources().getStringArray(R.array.sections);

		// //TextView myTextView = (TextView) findViewById(R.id.my_textview);
		// myTextView.setText(name);

		Button btn = (Button) findViewById(R.id.sendb);
		registerForContextMenu(getListView());
		/** Defining the ArrayAdapter to set items to ListView */
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listArr);
		ListView lv = getListView();
		/** Defining a click event listener for the button "send" */
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				edit = (EditText) findViewById(R.id.edittextmess);
				listArr.add(edit.getText().toString());
				texted = edit.getText().toString();
				edit.setText("");
				adapter.notifyDataSetChanged();
			}
		};
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		/** Setting the event listener for the add button */
		btn.setOnClickListener(listener);
		/** Setting the adapter to the ListView */
		lv.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.chat_menu, menu);

		// Locate the EditText in menu.xml
		editsearch = (EditText) menu.findItem(R.id.menu_search1)
				.getActionView();
		titleText = (LinearLayout) menu.findItem(R.id.window_title)
				.getActionView();
		TextView tv = (TextView) titleText.getChildAt(0);
		tv.setText(name);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in = new Intent(ListItemDetail.this, Favorites.class);
				in.putExtra("favourites", favourites);
				in.putExtra(_imgUrl, img_url);
				startActivity(in);
			}
		});
		// Capture Text in EditText
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, Gravity.LEFT
						| Gravity.CENTER_VERTICAL);
		getSupportActionBar().setCustomView(titleText, lp);
		editsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				/*
				 * String text = editsearch.getText().toString()
				 * .toLowerCase(Locale.getDefault()); showToast();
				 * Log.i("Text Here", text+" Before filter "+usersList.size());
				 * adapter.filter(text); Log.i("Text Here",
				 * text+" after filter "+usersList.size());
				 */
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				/*
				 * String text = editsearch.getText().toString()
				 * .toLowerCase(Locale.getDefault()); showToast();
				 * Log.i("Text Here", text+" Before filter "+usersList.size());
				 * adapter.filter(text); Log.i("Text Here",
				 * text+" after filter "+usersList.size());
				 */
				// TODO Auto-generated method stub

			}
		}

		);

		// Show the search menu item in menu.xml
		MenuItem menuSearch = menu.findItem(R.id.menu_search1);

		menuSearch.setOnActionExpandListener(new OnActionExpandListener() {

			// Menu Action Collapse
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// Empty EditText to remove text filtering
				editsearch.setText("");
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
		MenuItem menuSettings = menu.findItem(R.id.action_settings1);

		// Capture menu item clicks
		menuSettings.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				// TODO Auto-generated method stub
				// Do something here
				Intent in = new Intent(getApplicationContext(), Favorites.class);
				startActivity(in);
				Toast.makeText(getApplicationContext(), "Nothing here!",
						Toast.LENGTH_LONG).show();
				return false;
			}

		});
		// Calling super after populating the menu is necessary here to ensure
		// that the
		// action bar helpers have a chance to handle this event.
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
			NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contexts, menu);
	}

	/** This will be invoked when a menu item is selected */
	public boolean onContextItemSelected(android.view.MenuItem item) {
		Log.i("onContextItemSelected ", item.getItemId() + " " + R.id.favorites);
		switch (item.getItemId()) {
		case R.id.favorites:
			Intent in = new Intent(getApplicationContext(), Favorites.class);
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
					.getMenuInfo();
			favourites.add(listArr.get(info.position));
			in.putExtra("favourites", favourites);
			in.putExtra(_imgUrl, img_url);
			// Toast.makeText(getApplicationContext(),
			// edit.getText().toString(), ).show();
			Log.i("favourites print ", favourites.size() + "");
			Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT)
					.show();
			startActivity(in);
			break;
		case R.id.deletecon:
			Toast.makeText(this, "Delete : ", Toast.LENGTH_SHORT).show();

			break;
		case R.id.sharecon:
			Toast.makeText(this, "Share : ", Toast.LENGTH_SHORT).show();
			break;
		case R.id.forward:
			Toast.makeText(this, "Forward : ", Toast.LENGTH_SHORT).show();
			break;

		}
		return true;
	}

}
