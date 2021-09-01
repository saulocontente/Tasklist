package com.aulaudemy.tasklist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aulaudemy.tasklist.activity.InputActivity;
import com.aulaudemy.tasklist.adapter.AdapterTasklist;
import com.aulaudemy.tasklist.databinding.FragmentFirstBinding;
import com.aulaudemy.tasklist.helper.RecyclerClickListener;
import com.aulaudemy.tasklist.helper.TaskDAO;
import com.aulaudemy.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private List<Task> taskList = new ArrayList<>();



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadRecyclerTask();
        binding.recyclerViewTask.addOnItemTouchListener(new RecyclerClickListener(
                getContext(),
                binding.recyclerViewTask,
                new RecyclerClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Task selectedTask = taskList.get(position);
                        Intent intent = new Intent(getContext(), InputActivity.class);
                        intent.putExtra("selectedTask", selectedTask);
                        startActivity(intent);
                    }
                }
        ));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadRecyclerTask() {
        TaskDAO taskDAO = new TaskDAO(getContext());
        taskList = taskDAO.read();
        AdapterTasklist adapter = new AdapterTasklist(taskList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        binding.recyclerViewTask.setLayoutManager(layoutManager);
        binding.recyclerViewTask.setAdapter(adapter);
        binding.recyclerViewTask.setHasFixedSize(true);
    }
}