package uz.gita.presentation.ui.adapters

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.data.mapper.MusicMapper.getMusicDataByPosition
import uz.gita.databinding.ItemMusicBinding

class MusicAdapter (var cursor:Cursor? = null):RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private var selectMusicPositionListener:((Int) -> Unit)? = null

    inner class MusicViewHolder(private val binding:ItemMusicBinding):RecyclerView.ViewHolder(binding.root){
        init {binding.root.setOnClickListener {
            selectMusicPositionListener?.invoke(absoluteAdapterPosition)
        }
        }

        fun onBind(){
            cursor?.getMusicDataByPosition(absoluteAdapterPosition)?.let {
                binding.textMusicName.text = it.title
                binding.textArtistName.text = it.artist
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder (ItemMusicBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = cursor?.count ?: 0

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
       holder.onBind()
    }

    fun selectMusicPositionListener(block:(Int) -> Unit){
        selectMusicPositionListener = block
    }
}