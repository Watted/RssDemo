package tomerbu.edu.rssdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TomerBu on 06/12/2017.
 *
 */
public class RssNewsAdapter extends RecyclerView.Adapter<RssNewsAdapter.RssNewsViewHolder>{

    LayoutInflater inflater; //takes an xml file (R.layout.) and inflates it to an object of type View!
    ArrayList<Rss> data;
    Context context;
    //constructor!
    public RssNewsAdapter(Context context, ArrayList<Rss> data) {
        this.inflater = LayoutInflater.from(context);

        this.context = context;
        this.data = data;
    }
    @Override
    public RssNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.ynet_item, parent, false);
        return new RssNewsViewHolder(v);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onBindViewHolder(RssNewsViewHolder holder, int position) {
        Rss rss = data.get(position);
        //data binding:
        holder.tvTitle.setText(rss.title);
        holder.tvSummary.setText(rss.summary);
        Picasso.with(context).load(rss.imageUrl).into(holder.ivArticle);
    }

    //implement the abstract methods.

    //inner class ViewHolder:
    //find the views by id inside the constructor:
    class RssNewsViewHolder extends RecyclerView.ViewHolder{
        //fields: tvTitle, tvSummary, ivArticle.
        TextView tvTitle, tvSummary;
        ImageView ivArticle;
        public RssNewsViewHolder(View itemView) {
            super(itemView);
            //Find View By Id:
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSummary = itemView.findViewById(R.id.tvSummary);
            ivArticle = itemView.findViewById(R.id.ivArticle);
        }
    }
}
