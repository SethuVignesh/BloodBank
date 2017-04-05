// CustomAdapter.java
package com.newtra.bloodbank;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

	private List<Donor> items;
	private Context context;
	private LayoutInflater inflater;

	public CustomAdapter(Context _context, List<Donor> _items) {
		inflater = LayoutInflater.from(_context);
		this.items = _items;
		this.context = _context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Donor contact = items.get(position);

		View view = convertView;

		if (view == null)
			view = inflater.inflate(R.layout.contact_item, null);

		TextView name = (TextView) view.findViewById(R.id.tv_full_name);
		TextView phone = (TextView) view.findViewById(R.id.tv_phone_number);
		ImageView call = (ImageView) view.findViewById(R.id.iv_call);
		ImageView msg = (ImageView) view.findViewById(R.id.iv_msg);
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context,
						"Calling :" + contact.getPhoneNumber(),
						Toast.LENGTH_LONG).show();

			}
		});
		msg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context,
						"Sending Message to : " + contact.getPhoneNumber(),
						Toast.LENGTH_LONG).show();

			}
		});
		// ImageView photo = (ImageView) view.findViewById(R.id.list_image);

		name.setText(contact.getName());
		phone.setText(contact.getPhoneNumber());
		// photo.setImageBitmap(BitmapFactory.decodeFile(contact.getPhotograph()));

		return view;
	}

}
