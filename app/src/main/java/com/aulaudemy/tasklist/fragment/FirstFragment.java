package com.aulaudemy.tasklist.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aulaudemy.tasklist.R;
import com.aulaudemy.tasklist.adapter.AdapterTasklist;
import com.aulaudemy.tasklist.databinding.FragmentFirstBinding;
import com.aulaudemy.tasklist.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadRecyclerTask() {
        Task task = new Task();
        task.setDescription("peito");
        taskList.add(task);
        AdapterTasklist adapter = new AdapterTasklist(taskList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        binding.recyclerViewTask.setLayoutManager(layoutManager);
        binding.recyclerViewTask.setAdapter(adapter);
        binding.recyclerViewTask.setHasFixedSize(true);
    }
}