package tomerbu.edu.rssdemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class YnetFragment extends Fragment implements RssHTTPHandler.RssResultListener {
    RecyclerView rvRss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ynet, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        rvRss = v.findViewById(R.id.rvYnet);

        String url = "http://www.ynet.co.il/Integration/StoryRss2.xml";
        RssHTTPHandler.read(url, this);
    }


    @Override
    public void onResult(@NonNull ArrayList<Rss> result) {
        rvRss.setAdapter(new RssNewsAdapter(getActivity(), result));
        rvRss.setLayoutManager(new LinearLayoutManager(null));
    }

    @Override
    public void onError(@NonNull Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
