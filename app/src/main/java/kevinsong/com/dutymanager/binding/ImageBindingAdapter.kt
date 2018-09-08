package kevinsong.com.dutymanager.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kevinsong.com.dutymanager.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["url"], requireAll = false)
    fun setUrl(imageView: ImageView, url: String?) {
        url?.let {
            Picasso.get()
                    .load(it)
                    .fit()
                    .placeholder(R.drawable.image_default)
                    .into(imageView)
        }

    }
}