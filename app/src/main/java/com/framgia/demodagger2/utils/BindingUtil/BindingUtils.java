package com.framgia.demodagger2.utils.BindingUtil;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vnnht.demodagger2.R;
import com.framgia.demodagger2.screen.list.ListStudentAdapter;

public class BindingUtils {
    private BindingUtils() {
    }

    @BindingAdapter({ "bind:imageUrl" })
    public static void loadImage(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions().centerCrop()
                .placeholder(R.drawable.ic_replace_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp);
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    @BindingAdapter({ "bind:recyclerAdapter" })
    public static void setApdaterForRecycler(RecyclerView recycler, ListStudentAdapter adapter) {
        recycler.setAdapter(adapter);
    }
}
