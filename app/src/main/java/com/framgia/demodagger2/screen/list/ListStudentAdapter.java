package com.framgia.demodagger2.screen.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vnnht.demodagger2.R;
import com.example.vnnht.demodagger2.databinding.StudentItemRowBinding;
import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.screen.OnItemClickListen;
import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ViewHolder> {

    private List<Student> mStudentList;
    private OnItemClickListen mOnItemClickListen;

    public ListStudentAdapter() {
        mStudentList = new ArrayList<>();
    }

    public void updateStudentList(List<Student> studentList) {
        if (mStudentList != null) {
            mStudentList.clear();
        }
        mStudentList = studentList;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClickListen onItemClick) {
        mOnItemClickListen = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentItemRowBinding studentItemRowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.student_item_row, parent, false);

        return new ViewHolder(studentItemRowBinding, mOnItemClickListen);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getViewHolder(mStudentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudentList != null ? mStudentList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private StudentItemRowBinding mStudentItemRowBinding;
        private StudentItemViewModel mItemViewModel;
        private OnItemClickListen mOnItemClickListen;

        public ViewHolder(StudentItemRowBinding studentItemRowBinding,
                OnItemClickListen itemClickListen) {
            super(studentItemRowBinding.getRoot());
            mOnItemClickListen = itemClickListen;
            mStudentItemRowBinding = studentItemRowBinding;
            mItemViewModel = new StudentItemViewModel();
            mStudentItemRowBinding.setViewModel(mItemViewModel);
            itemView.setOnClickListener(this);
        }

        void getViewHolder(Student student) {
            mItemViewModel.setItemData(student);
            mStudentItemRowBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListen.itemClicked(getAdapterPosition());
        }
    }
}
