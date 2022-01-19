// package com.githubyss.mobile.common.ui.mvvm.bindingadapter;
//
// import android.widget.ImageView;
//
// import com.githubyss.mobile.common.kit.glide.GlideUtils;
//
// import androidx.databinding.BindingAdapter;
//
//
// public class ImageViewBindingAdapter {
//
//     @BindingAdapter({"path"})
//     public static void loadImage(ImageView view, Object path) {
//         GlideUtils.INSTANCE.loadImage(view, view, path, null, null);
//     }
//
//     @BindingAdapter(value = {"path", "placeholder", "error"}, requireAll = false)
//     public static void loadImage(ImageView view, Object path, Object placeholder, Object error) {
//         GlideUtils.INSTANCE.loadImage(view, view, path, placeholder, error);
//     }
// }
