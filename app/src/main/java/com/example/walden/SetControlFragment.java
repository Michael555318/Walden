package com.example.walden;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.walden.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.walden.MainActivity.turned_on;

public class SetControlFragment extends Fragment {

    ListView grid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setcontrol, container, false);
        setGridView(v);
        return v;
    }

    private void setGridView(View v) {
        grid = v.findViewById(R.id.listView);
        List<MainSetting> set = new ArrayList<>();
        // todo: store data instead of redeclaring everytime to false (maybe backendless and other online databases)
        set.add(new MainSetting(turned_on[0], "Walden Zone"));
        set.add(new MainSetting(turned_on[1], "Breaks"));
        set.add(new MainSetting(turned_on[2], "App Restrictions"));
        final gridAdaptor adapter = new gridAdaptor(
                getActivity(),
                R.layout.item_gridview,
                set);
        grid.setAdapter(adapter);
    }
}
