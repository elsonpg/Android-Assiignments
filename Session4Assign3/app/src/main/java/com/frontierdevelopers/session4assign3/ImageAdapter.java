package com.frontierdevelopers.session4assign3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Elson on 5/4/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(110, 110));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.drawable1,R.drawable.drawable2,
            R.drawable.drawable3, R.drawable.drawable4,
            R.drawable.drawable5, R.drawable.drawable6,
            R.drawable.drawable7, R.drawable.drawable8,
            R.drawable.drawable9,
    };


}

/*First, this implements some required methods inherited from BaseAdapter. The constructor and getCount()
are self-explanatory. Normally, getItem(int) should return the actual object at the specified position in the adapter,
but it's ignored for this example. Likewise, getItemId(int) should return the row id of the item, but it's not needed here.

The first method necessary is getView(). This method creates a new View for each image added to the ImageAdapter.
 When this is called, a View is passed in, which is normally a recycled object (at least after this has been called once),
 so there's a check to see if the object is null. If it is null, an ImageView is instantiated and configured with desired
 properties for the image presentation:
setLayoutParams(ViewGroup.LayoutParams) sets the height and width for the View—this ensures that, no matter the size of the
drawable, each image is resized and cropped to fit in these dimensions, as appropriate.
setScaleType(ImageView.ScaleType) declares that images should be cropped toward the center (if necessary).
setPadding(int, int, int, int) defines the padding for all sides. (Note that, if the images have different aspect-ratios, then
 less padding will cause more cropping of the image if it does not match the dimensions given to the ImageView.)
If the View passed to getView() is not null, then the local ImageView is initialized with the recycled View object.
At the end of the getView() method, the position integer passed into the method is used to select an image from the mThumbIds
array, which is set as the image resource for the ImageView.
All that's left is to define the mThumbIds array of drawable resources.
Run the application.
Try experimenting with the behaviors of the GridView and ImageView elements by adjusting their properties. For example, instead
of using setLayoutParams(ViewGroup.LayoutParams), try using setAdjustViewBounds(boolean).*/
