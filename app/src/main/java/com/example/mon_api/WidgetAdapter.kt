package com.example.mon_api
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WidgetAdapter(private var widgets: List<Widget>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TEXT -> TextViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text, parent, false)
            )
            VIEW_TYPE_IMAGE -> ImageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bindText(widgets[position].widget_title)
            is ImageViewHolder -> holder.bindImage(widgets[position].widget_title)
        }
    }

    override fun getItemCount(): Int {
        return widgets.size
    }

    override fun getItemViewType(position: Int): Int {
        val widgetType = widgets[position].widget_type
        return when (widgetType) {
            "text" -> VIEW_TYPE_TEXT
            "image" -> VIEW_TYPE_IMAGE
            else -> {
                println("Unknown widget type: $widgetType. Falling back to text view.")
                VIEW_TYPE_TEXT
            }
        }
    }


    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bindText(text: String) {
            textView.text = text
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ImageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bindImage(imageUrl: String) {
            if (imageUrl.isNotEmpty()) {
                Picasso.get().load(imageUrl).into(ImageView)
            } else {

                ImageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }


    companion object {
        private const val VIEW_TYPE_TEXT = 1
        private const val VIEW_TYPE_IMAGE = 2
    }

}
