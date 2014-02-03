package com.chat.internetchat;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    
	private Context mContext;
	private Activity activity;
	private LayoutInflater inflater = null;
	private List<ListItemInfo> listItemInfoData = null;
	private ArrayList<ListItemInfo> arraylist;
	public ImageLoader imageLoader; 
	static final String KEY_NAME = "name";

	public ListViewAdapter(Context context,
			List<ListItemInfo> worldpopulationlist) {
		mContext = context;
		this.listItemInfoData = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ListItemInfo>();
		this.arraylist.addAll(listItemInfoData);
	}

    public ListViewAdapter(Activity a, List<ListItemInfo> usersList) {
        activity = a;
        listItemInfoData=usersList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<ListItemInfo>();
        this.arraylist.addAll(listItemInfoData);
        this.imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public class ViewHolder {
		TextView name;
		TextView latest_message;
		TextView time;
		ImageView imgView;
	}

	@Override
	public int getCount() {
		return listItemInfoData.size();
	}

	@Override
	public ListItemInfo getItem(int position) {
		return listItemInfoData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_row, null);
			// Locate the TextViews in listview_item.xml
			holder.name = (TextView)convertView.findViewById(R.id.title); // title
	        holder.latest_message = (TextView)convertView.findViewById(R.id.artist); // artist name
	        holder.time = (TextView)convertView.findViewById(R.id.duration); // duration
			// Locate the ImageView in listview_item.xml
			holder.imgView = (ImageView) convertView.findViewById(R.id.list_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// Set the results into TextViews
		holder.name.setText(listItemInfoData.get(position).getName());
        holder.latest_message.setText(listItemInfoData.get(position).getLatestMessage()); // artist name
        holder.time.setText(listItemInfoData.get(position).getTime());
		
		// Set the results into ImageView
		//holder.imgView.setImageURI(Uri.parse(listItemInfoData.get(position).getThumbURL()));

        imageLoader.DisplayImage(listItemInfoData.get(position).getThumbURL(), holder.imgView);
        return convertView;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listItemInfoData.clear();
        if (charText.length() == 0) {
        	listItemInfoData.addAll(arraylist);
        }
        else
        {
            for (ListItemInfo lii : arraylist)
            {
            	if(lii.getName().toLowerCase(Locale.getDefault()).contains(charText))
            	{
            		Log.i("check map contains", lii.getName().toLowerCase(Locale.getDefault())+" "+charText+" "+lii.getName().toLowerCase(Locale.getDefault()).contains(charText));
            		listItemInfoData.add(lii);
            		Log.i("dlist length ", listItemInfoData.size()+"");
            	}else{
            	Log.i("check map not contains", lii.getName().toLowerCase(Locale.getDefault())+" "+charText+" "+lii.getName().toLowerCase(Locale.getDefault()).contains(charText));
            	Log.i("dlist length ", listItemInfoData.size()+"");
            	}
            		
            }
        }
        notifyDataSetChanged();
}
}