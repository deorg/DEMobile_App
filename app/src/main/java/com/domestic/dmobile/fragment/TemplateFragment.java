package com.domestic.dmobile.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.adapter.TemplateAdapter;

public class TemplateFragment extends Fragment {

    ListView listItem;
    //TODO Set Adapter Template
    TemplateAdapter adapter;

    public static TemplateFragment newInstance() {
        TemplateFragment fragment = new TemplateFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TODO Set FragmentTemplate
        View view = inflater.inflate(R.layout.fragment_template, container, false);
        initInstance(view);
        initListener();
        return view;

    }

    //region #----- Initial ----------
    private void initInstance(View view) {
        //TODO Set initInstance Template
        listItem = view.findViewById(R.id.listItem);
        adapter = new TemplateAdapter();
        listItem.setAdapter(adapter);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        //TODO Set initListener Template
    }
    //---------------------------------
    private void setOnListener() {
        //TODO Set setOnListener Template
    }
    //endregion


    //region #----- MyMethod ----------
    //TODO Set MyMethod Template

    //endregion
}
