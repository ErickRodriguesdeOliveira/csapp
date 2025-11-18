package com.example.csapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import coil.load

class HighlightDetailActivity : AppCompatActivity() {

    private var currentPosition: Int = 0
    private var videoUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight_detail)

        val img = findViewById<ImageView>(R.id.imgHighlightDetail)
        val tvName = findViewById<TextView>(R.id.tvHighlightNameDetail)
        val tvInfo = findViewById<TextView>(R.id.tvHighlightInfoDetail)
        val tvDesc = findViewById<TextView>(R.id.tvHighlightDescDetail)
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
        tvDesc.text = if (desc.isNotBlank()) desc else "Sem descrição."
        tvInfo.text = "Evento: $event\nTimes: $team0 vs $team1\nMapa: $map\nFase: $stage"

        img.load(image) {
            crossfade(true)
        }

        if (videoUrl.isNotBlank()) {
            val uri = Uri.parse(videoUrl)

            // Controles de play/pause/seek
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            videoView.setVideoURI(uri)

            // Opcional: começa a tocar automaticamente quando estiver pronto
            videoView.setOnPreparedListener { mp ->
                mp.isLooping = false
                videoView.start()
            }

            // Se der erro na reprodução
            videoView.setOnErrorListener { _, _, _ ->
                Toast.makeText(this, "Erro ao reproduzir o vídeo", Toast.LENGTH_SHORT).show()
                true
            }

        } else {
            Toast.makeText(this, "Vídeo não disponível para esse highlight", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        val videoView = findViewById<VideoView>(R.id.videoHighlight)
        if (videoView.isPlaying) {
            currentPosition = videoView.currentPosition
            videoView.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        val videoView = findViewById<VideoView>(R.id.videoHighlight)
        if (videoUrl.isNotBlank()) {
            videoView.seekTo(currentPosition)
            videoView.start()
        }
    }
}
