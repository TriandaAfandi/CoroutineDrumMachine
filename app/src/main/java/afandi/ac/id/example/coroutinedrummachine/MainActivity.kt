package afandi.ac.id.example.coroutinedrummachine

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {
    private lateinit var cymbal: MediaPlayer
    private lateinit var tom: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cymbal = MediaPlayer.create(this, R.raw.crash_cymble)
        tom = MediaPlayer.create(this, R.raw.toms)
        btnStart.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.toms)
                }
                playBeats("x-----x-----x-----x-----", R.raw.crash_cymble)
            }
        }
    }
    suspend fun playBeats(beats: String, fileId: Int){
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == ""){
                if(fileId == R.raw.crash_cymble)
                    cymbal.start()
                else
                    tom.start()
            }else{
                delay(1000 * (part.length + 1L))
                if(count < beats.length){
                    if(fileId == R.raw.crash_cymble)
                        cymbal.start()
                    else
                        tom.start()
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        cymbal.stop()
        tom.stop()
    }
}