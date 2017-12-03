package tomerbu.edu.rssdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class YnetFragment extends Fragment {


    public YnetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RssHTTPHandler.read("http://www.ynet.co.il/Integration/StoryRss2.xml", (rss, e) -> {
            if (rss != null)
                Toast.makeText(getActivity(), rss.toString(), Toast.LENGTH_SHORT).show();
            else if (e != null) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.fragment_ynet, container, false);
    }

}
