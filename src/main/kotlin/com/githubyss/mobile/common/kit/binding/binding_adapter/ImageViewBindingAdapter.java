// package com.githubyss.mobile.common.kit.mvvm.bindingadapter;
//
// import android.widget.ImageView;
//
// import com.githubyss.mobile.common.kit.glide.GlideUtils;
//
// import androidx.databinding.BindingAdapter;
//
//
// /**
//  * ImageViewBindingAdapter
//  *
//  * @author Ace Yan
//  * @github githubyss
//  * @createdTime 2022/05/19 11:26:05
//  */
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
