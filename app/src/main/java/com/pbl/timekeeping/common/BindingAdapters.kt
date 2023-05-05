package com.pbl.timekeeping.common

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object BindingAdapters{
    @JvmStatic
    @BindingAdapter("textString","isChanged", requireAll = true)
    fun setVisibilityEmptyTextView(view : TextView, text : String?, isChanged : Boolean){
        if(!isChanged) return
        view.visibility = if(Utils.isEmptyText(text)) View.VISIBLE else View.GONE
    }
    @JvmStatic
    @BindingAdapter("email")
    fun setVisibilityValidateEmail(view : TextView,email : String?){
        email?.let {
            view.visibility = if(it.isEmpty()) View.GONE else {
                if(Utils.validateEmail(it)) View.GONE else View.VISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setImageView(iv : ImageView, imgUrl : String?){
        imgUrl?.let {
            Glide.with(iv.context)
                .load(imgUrl)
                .into(iv)
        }
    }
    @JvmStatic
    @BindingAdapter("date")
    fun setDateTextView(tv : TextView, dateString : String?){
       dateString?.let {
          try{
              val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)
              format.timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
              val date = format.parse(it)
              val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
              val newDateString = newFormat.format(date)
              tv.text = newDateString
          }
          catch (e : Exception){
              e.printStackTrace()
          }
       }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter("time")
    fun setTimeTextView(tv : TextView, time : String?){
        time?.let {
            try{
                val dateTimeFormatter = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US)
                val parsedDateTime = ZonedDateTime.parse(time, dateTimeFormatter)
                val formattedTime = parsedDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

                tv.text = formattedTime
            }
            catch (e : Exception){
                //e.printStackTrace()
            }
        }
    }
    @JvmStatic
    @BindingAdapter("base64")
    fun displayBase64Image(imageView: ImageView, base64: String?){
        base64?.let {
            try {
                val imageBytes = Base64.decode(base64, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imageView.setImageBitmap(decodedImage)
            } catch (e: Exception) {
                //e.printStackTrace()
            }
        }
    }
    @JvmStatic
    @BindingAdapter("dob")
    fun displayDateString(textView: TextView,dob : String?){
        try {
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)
            val date = inputFormat.parse(dob)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            textView.text = outputFormat.format(date)
        }
        catch ( e : Exception){
            print(e.message)
        }
    }
}
