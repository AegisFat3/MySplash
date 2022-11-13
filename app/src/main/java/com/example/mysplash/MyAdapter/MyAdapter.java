package com.example.mysplash.MyAdapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mysplash.R;
import com.example.mysplash.json.MyData;

import java.io.Serializable;
import java.util.List;

public class MyAdapter extends BaseAdapter implements Serializable {
    private List<MyData> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int []images = { R.drawable.plus,R.drawable.edit};

    public MyAdapter(List<MyData> list, Context context)
    {
        this.list = list;
        this.context = context;
        if( context != null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public boolean isEmptyOrNull( )
    {
        return list == null || list.size() == 0;
    }

    @Override
    public int getCount()
    {
        if(isEmptyOrNull())
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        if(isEmptyOrNull())
        {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        TextView textView = null;
        TextView textView2 = null;
        ImageView imageView = null;
        ImageView imageView2 = null;
        ImageView imageView3 = null;
        view = layoutInflater.inflate(R.layout.activity_list_view, null );
        textView = view.findViewById(R.id.textViewId2);
        textView2 = view.findViewById(R.id.textViewId1);
        textView.setText(list.get(i).getContra());
        textView2.setText(list.get(i).getRed());
        imageView = view.findViewById(R.id.imageView5);
        imageView2 = view.findViewById(R.id.imageView4);
        imageView3 = view.findViewById(R.id.imageView);
        imageView.setImageResource(list.get(i).getImage());
        imageView2.setImageResource(images[0]);
        imageView3.setImageResource(images[1]);
        return view;
    }
}