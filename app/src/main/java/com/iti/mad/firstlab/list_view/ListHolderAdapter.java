package com.iti.mad.firstlab.list_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

/**
 * Created by ayman on 2019-01-30.
 */

public class ListHolderAdapter extends ArrayAdapter {
    Context context;
    Cake[] cakes;
    LayoutInflater inflter;
    public ListHolderAdapter(@NonNull Context context,  Cake[] cakes) {
        super(context, R.layout.list_row,cakes);
        this.context=context;
        this.cakes=cakes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        View viewRow=view;
        if (view==null){
            inflter= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflter.inflate(R.layout.list_row, null);

            viewHolder=new ViewHolder(viewRow);
            viewRow.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) viewRow.getTag();
        }

        viewHolder.bind(position);

        return viewRow;


    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public  Bitmap decodeImageFromFiles(Resources res,int resId, int width, int height) {
        BitmapFactory.Options scaleOptions = new BitmapFactory.Options();
        BitmapFactory.Options outOptions=null;
        try {
            scaleOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, scaleOptions);

            int scale = 1;
            int aa= scaleOptions.outWidth / scale / 2;
            while (scaleOptions.outWidth / scale / 2 >= width&& scaleOptions.outHeight / scale / 2 >= height) {
                scale *= 2;
            }
            outOptions = new BitmapFactory.Options();
            outOptions.inSampleSize = scale;
            return  BitmapFactory.decodeResource(res, resId, outOptions);


        } catch (OutOfMemoryError e) {

        }
        return  BitmapFactory.decodeResource(res, resId, outOptions);


    }





    public  int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public  class ViewHolder{
        TextView names;
        TextView descText;
        ImageView image;
        public ViewHolder(View view){
             names = (TextView) view.findViewById(R.id.nameText);
             descText = (TextView) view.findViewById(R.id.descText);
             image = view.findViewById(R.id.imageId);


        }
        void bind(int position){
            names.setText(cakes[position].getCakeName());
            descText.setText(cakes[position].getCakeDesc());
//            image.setImageResource(cakes[position].getImageCake());

            image.setImageBitmap(decodeImageFromFiles(context.getResources(), cakes[position].getImageCake(), 50 ,50));
        }


    }
}
