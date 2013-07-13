package com.mikeriv.checkmate.views;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mikeriv
 * Date: 7/13/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuListView extends ListView {
    private ArrayList<MenuListItem> mMenuItems;

    public class MenuListItem extends View {

        public MenuListItem(Context context) {
            super(context);
        }
    }


    public MenuListView(Context context) {
        super(context);
    }

    //TODO

    // Deals with handling data for a ListView
    private class MenuListViewAdapter implements ListAdapter {

        public MenuListViewAdapter() {
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isEnabled(int position) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getCount() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getItem(int position) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public long getItemId(int position) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean hasStableIds() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getItemViewType(int position) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getViewTypeCount() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isEmpty() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }



}
