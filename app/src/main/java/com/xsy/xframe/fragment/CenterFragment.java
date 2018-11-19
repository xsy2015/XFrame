package com.xsy.xframe.fragment;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;
import com.xsy.xframe.XAplication;
import com.xsy.xframe.activity.AddNewNoteActivity;
import com.xsy.xframe.activity.ChildItemActivity;
import com.xsy.xframe.activity.ChildItemOnclick;
import com.xsy.xframe.adapter.NewsListAdapter;
import com.xsy.xframe.adapter.NotesListAdapter;
import com.xsy.xframe.bean.ArticleListBean;
import com.xsy.xframe.db.DaoSession;
import com.xsy.xframe.db.NotesBean;
import com.xsy.xframe.db.NotesBeanDao;
import com.xsy.xframe.event.RefreshNoteEvent;
import com.xsy.xframe.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 */
public class CenterFragment extends BaseFragment {
    private static final java.lang.String TAG ="CenterFragment" ;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<NotesBean> notesData = new ArrayList<>();
    private NotesListAdapter adapter;
    private NotesBeanDao noteDao;
    private Query<NotesBean> notesQuery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragement_center;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mTopBar.setTitle("me");
        mTopBar.setTitleGravity(Gravity.CENTER);
        View view=LayoutInflater.from(getContext()).inflate(R.layout.topbar_right,null);
        TextView tv_right = view.findViewById(R.id.tv_right);
        tv_right.setText("add note");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200,RelativeLayout.LayoutParams.MATCH_PARENT);
        mTopBar.addRightView(view,R.id.tv_right,layoutParams);
        view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              startActivity(new Intent(getActivity(), AddNewNoteActivity.class));
           }
        });

        // get the note DAO
        DaoSession daoSession =((XAplication)getActivity().getApplication()).getDaoSession();
        noteDao = daoSession.getNotesBeanDao();
        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderDesc(NotesBeanDao.Properties.Date).build();
        notesData=notesQuery.list();
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotesListAdapter(R.layout.item_notes_list, notesData, getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        LogUtils.i(TAG,notesData.toString());
        adapter.notifyDataSetChanged();

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final BaseQuickAdapter adapter, View view, int position) {
                QMUIDialog.MenuDialogBuilder builder=new QMUIDialog.MenuDialogBuilder(getContext());
                builder.addItem("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                showMessageNegativeDialog();
                                dialog.dismiss();
                            }
                        })
                     .show();
                return false;
            }
        });
    }

    private void showMessageNegativeDialog() {
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("提示")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        noteDao.deleteByKey(notesData.get(index-1).getId());
                        notesData.clear();
                        notesData.addAll(notesQuery.list());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshNoteEvent event){
        notesData.clear();
        notesData.addAll(notesQuery.list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

