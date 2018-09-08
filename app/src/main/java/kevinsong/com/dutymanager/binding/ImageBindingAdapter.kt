package kevinsong.com.dutymanager.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kevinsong.com.dutymanager.R
import java.util.*

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["url"], requireAll = false)
    fun setUrl(imageView: ImageView, url: String) {
        Picasso.get()
                .load(url + Random().nextInt())
                .fit()
                .placeholder(R.drawable.image_default)
                .into(imageView)
    }
}