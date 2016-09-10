package hack.galert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

/**
 * Created by Ankit on 9/10/2016.
 */
public class NavigationFragment extends Fragment {

    private ListView listView;
    private NavListAdapter adapter;

    public NavigationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_drawer_fragment, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.navList);

        adapter = NavListAdapter.getInstance(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                view.setActivated(true);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public void requestNavigationItems() {

        //todo: add pref list to url
        final String url = Constants.SERVER_URL_NAVIGATION_ITEMS;
        StringRequest navItemsListRequest = new StringRequest(
                StringRequest.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        parseNavigationItemsResponse(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //makeSnackbar("Authentication Failed !");
                    }
                });

        VolleyUtils.getInstance().addToRequestQueue(navItemsListRequest, "navItemsReq", getActivity());

    }

    public void parseNavigationItemsResponse(String response) {

    }

    public void updateListAdapter() {
        ArrayList<NavItems> navItems = new ArrayList<>();

        //TODO: parse response

        adapter.setItems(navItems);
        listView.setAdapter(adapter);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}