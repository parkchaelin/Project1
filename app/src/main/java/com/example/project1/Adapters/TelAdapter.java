package com.example.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.Classes.Tel;

import java.util.List;

public class TelAdapter extends BaseAdapter {
    //String TAG = TelAdapter.class.getSimpleName();

    Context context;
    List<Tel> items;
    LayoutInflater inflter;


    public TelAdapter(Context context, List<Tel> data) {
        this.context = context;
        this.items = data;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
        //return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
        //return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.tel_item, null);

        TextView txtName = (TextView) view.findViewById(R.id.name);
        TextView txtPhone = (TextView) view.findViewById(R.id.phone);

        txtName.setText(items.get(i).getName());
        txtPhone.setText(items.get(i).getPhone());


        return view;
    }

//    InputStream inputstream;
//    Drawable drawable;
//    private void loadImage(ImageView imageView, String imageName) {
//        try {
//            inputstream = context.getAssets().open("images/" + imageName);
//            drawable = Drawable.createFromStream(inputstream, null);
//            imageView.setImageDrawable(drawable);
//        } catch (Exception ex) {
//            Log.e(TAG, ex.getMessage());
//        }
//    }
}
