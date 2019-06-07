package spu.aprendizajemovil.utils;

import java.util.ArrayList;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import spu.aprendizajemovil.R;

public class ListAdapterWithDifferentsIcons extends BaseAdapter
{

	private static final int TYPE_ITEM = 0;
	// private static final int TYPE_SEPARATOR = 1;
	private static final int TYPE_SEPARATOR_GREEN = 1;
	private static final int TYPE_SEPARATOR_RED = 2;
	private static final int TYPE_SEPARATOR_YELLOW = 3;
	private static final int TYPE_MAX_COUNT = 4;

	private ArrayList<String> mData = new ArrayList<String>();
	private LayoutInflater mInflater;

	private TreeSet<Integer> mSeparatorsSet = new TreeSet<Integer>();
	private TreeSet<Integer> mSeparatorsSetGreen = new TreeSet<Integer>();
	private TreeSet<Integer> mSeparatorsSetRed = new TreeSet<Integer>();
	private TreeSet<Integer> mSeparatorsSetYellow = new TreeSet<Integer>();

	public ListAdapterWithDifferentsIcons(Activity activity)
	{
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void addItem(final String item)
	{
		mData.add(item);
		notifyDataSetChanged();
	}

	public void addSeparatorItem(final String item)
	{
		mData.add(item);
		// save separator position
		mSeparatorsSet.add(mData.size() - 1);
		notifyDataSetChanged();
	}

	public void addSeparatorGreen(final String item)
	{
		mData.add(item);
		// save separator position
		mSeparatorsSetGreen.add(mData.size() - 1);
		notifyDataSetChanged();
	}

	public void addSeparatorRed(final String item)
	{
		mData.add(item);
		// save separator position
		mSeparatorsSetRed.add(mData.size() - 1);
		notifyDataSetChanged();
	}

	public void addSeparatorYellow(final String item)
	{
		mData.add(item);
		// save separator position
		mSeparatorsSetYellow.add(mData.size() - 1);
		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position)
	{
		if (mSeparatorsSetGreen.contains(position))
		{
			return TYPE_SEPARATOR_GREEN;
		}
		if (mSeparatorsSetRed.contains(position))
		{
			return TYPE_SEPARATOR_RED;
		}
		if (mSeparatorsSetYellow.contains(position))
		{
			return TYPE_SEPARATOR_YELLOW;
		}
		return TYPE_ITEM;
	}

	@Override
	public int getViewTypeCount()
	{
		return TYPE_MAX_COUNT;
	}

	@Override
	public int getCount()
	{
		return mData.size();
	}

	@Override
	public String getItem(int position)
	{

		return mData.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		int type = getItemViewType(position);
		// System.out.println("getView " + position + " " + convertView
		// + " type = " + type);
		if (convertView == null)
		{
			holder = new ViewHolder();
			switch (type)
			{
			case TYPE_ITEM:
				convertView = mInflater.inflate(R.layout.list_item, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.list_item_title);
				break;
			case TYPE_SEPARATOR_GREEN:
				convertView = mInflater.inflate(
						R.layout.list_header_evaluation_green, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.list_header_title);
				break;
			case TYPE_SEPARATOR_RED:
				convertView = mInflater.inflate(
						R.layout.list_header_evaluation_red, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.list_header_title);
				break;
			case TYPE_SEPARATOR_YELLOW:
				convertView = mInflater.inflate(
						R.layout.list_header_evaluation_yellow, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.list_header_title);
				break;
			}
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(mData.get(position));
		return convertView;
	}

}
