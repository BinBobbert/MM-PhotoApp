package com.example.mm_photoapp.ui.photo_view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mm_photoapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import java.lang.Exception


class PhotoDetail : Fragment() {

    private var photoUrl: String? = null
    private var photoTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photoUrl = arguments?.getString(PHOTO_URL)
        photoTitle = arguments?.getString(PHOTO_TITLE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container!!.removeAllViews()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load(photoUrl).into(photo_detail, object: Callback{
            override fun onSuccess() {
                progress_detail.visibility = View.GONE
                group_detail.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("error", e.toString())
            }
        })

        photo_detail_title.text = photoTitle
    }

    /**
     * Create a new PhotoDetail() Instance
     * With arguments
     */
    companion object{
        private const val PHOTO_URL = "photo_url"
        private const val PHOTO_TITLE = "photo_title"

        fun newInstance(url: String, title: String) = PhotoDetail().apply {
            arguments = Bundle(2).apply {
                putString(PHOTO_TITLE, title)
                putString(PHOTO_URL, url)
            }

        }

    }
}
