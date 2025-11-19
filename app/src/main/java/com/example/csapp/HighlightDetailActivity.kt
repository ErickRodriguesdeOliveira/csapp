package com.example.csapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class HighlightDetailActivity : AppCompatActivity() {

    private var videoUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes do Highlight"

        val img = findViewById<ImageView>(R.id.imgHighlightDetail)
        val tvName = findViewById<TextView>(R.id.tvHighlightDetailName)
        val tvInfo = findViewById<TextView>(R.id.tvHighlightDetailInfo)
        val tvDesc = findViewById<TextView>(R.id.tvHighlightDetailDescription)
        val videoView = findViewById<VideoView>(R.id.videoHighlight)

        val name = intent.getStringExtra("name") ?: ""
        val desc = intent.getStringExtra("description") ?: ""
        val image = intent.getStringExtra("image") ?: ""
        videoUrl = intent.getStringExtra("video") ?: ""
        val event = intent.getStringExtra("event") ?: ""
        val team0 = intent.getStringExtra("team0") ?: ""
        val team1 = intent.getStringExtra("team1") ?: ""
        val map = intent.getStringExtra("map") ?: ""
        val stage = intent.getStringExtra("stage") ?: ""

        tvName.text = name
        tvInfo.text = "Evento: $event\nTimes: $team0 vs $team1\nMapa: $map\nFase: $stage"
        tvDesc.text = desc
        img.load(image)

        if (videoUrl.isNotEmpty()) {
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)

            val uri = Uri.parse(videoUrl)
            videoView.setVideoURI(uri)
            videoView.setMediaController(mediaController)

            videoView.setOnPreparedListener {
                it.isLooping = false
                videoView.start()
                mediaController.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
