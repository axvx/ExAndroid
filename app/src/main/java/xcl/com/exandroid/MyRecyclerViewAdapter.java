package xcl.com.exandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<clase_zapatos> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<clase_zapatos> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.myrecyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      //  String animal = mData.get(position);
        clase_zapatos zapatos=mData.get(position);
        holder.txt_titulo.setText(zapatos.nombre);
        holder.txt_precio.setText(zapatos.precio +"$");
        holder.txt_ubicacion.setText("Disponibilidad:"+zapatos.ubicacion);
        Picasso.get().load(zapatos.url).into(holder.img_imagen);



    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_titulo;
        TextView txt_precio;
        TextView txt_ubicacion;
        ImageView img_imagen;

        ViewHolder(View itemView) {
            super(itemView);
            txt_titulo = itemView.findViewById(R.id.txt_titulo);
            txt_precio = itemView.findViewById(R.id.txt_precio);
            txt_ubicacion = itemView.findViewById(R.id.txt_ubicacion);
            img_imagen=itemView.findViewById(R.id.img_imagen);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    clase_zapatos getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}