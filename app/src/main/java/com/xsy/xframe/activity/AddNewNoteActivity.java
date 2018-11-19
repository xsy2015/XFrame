package com.xsy.xframe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.xsy.base.R2;
import com.xsy.base.ui.BaseFragmentActivity;
import com.xsy.base.ui.utils.ToastUtils;
import com.xsy.xframe.R;
import com.xsy.xframe.XAplication;
import com.xsy.xframe.db.DaoSession;
import com.xsy.xframe.db.NoteType;
import com.xsy.xframe.db.NotesBean;
import com.xsy.xframe.db.NotesBeanDao;
import com.xsy.xframe.event.RefreshNoteEvent;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/11/12
 */

public class AddNewNoteActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    private NotesBeanDao noteDao;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addnew_note;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        mTopBar.setTitle("添加新笔记");
        mTopBar.setTitleGravity(Gravity.CENTER);

        et_content.requestFocus();

        // get the note DAO
        DaoSession daoSession = ((XAplication)getApplication()).getDaoSession();
        noteDao = daoSession.getNotesBeanDao();

        View view= LayoutInflater.from(this).inflate(R.layout.topbar_right,null);
        TextView tv_right = view.findViewById(R.id.tv_right);
        tv_right.setText("保存");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200,RelativeLayout.LayoutParams.MATCH_PARENT);
        mTopBar.addRightView(view,R.id.tv_right,layoutParams);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewNote();
            }
        });
    }

    private void addNewNote() {
       /* if (TextUtils.isEmpty(et_title.getText())){
            ToastUtils.showShort("标题不能为空");
            return;
        }*/
        if (TextUtils.isEmpty(et_content.getText())){
            ToastUtils.showShort("内容不能为空");
            return;
        }
        NotesBean notesBean = new NotesBean();
        notesBean.setDate(new Date());
        notesBean.setText(et_title.getText().toString());
        notesBean.setComment(et_content.getText().toString());
        notesBean.setType(NoteType.TEXT);
        noteDao.insert(notesBean);
        ToastUtils.showShort("保存成功");
        EventBus.getDefault().post(new RefreshNoteEvent());
        finish();
    }
}
