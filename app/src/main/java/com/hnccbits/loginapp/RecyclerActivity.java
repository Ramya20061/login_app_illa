package com.hnccbits.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hnccbits.loginapp.databinding.ActivityRecyclerBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding binding;

    private MutableLiveData<List<UserModel>> userObserver = new MutableLiveData<>(new ArrayList<>());
    private List<UserModel> users = new ArrayList<>();
    private UserAdapter userAdapter;
    private FirebaseFirestore firestore;
    private CollectionReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
        fetchData();
    }

    private void fetchData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        firestore = FirebaseFirestore.getInstance();
        userRef = firestore.collection("users");
        userRef.get().addOnCompleteListener(task -> {
            binding.progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                List<UserModel> userModels = querySnapshot.getDocuments().stream().map((documentSnapshot) -> documentSnapshot.toObject(UserModel.class)).collect(Collectors.toList());
                userObserver.postValue(userModels);
            }
        });
    }

    private void initUI() {
        userAdapter = new UserAdapter(this);
        binding.mainRv.setAdapter(userAdapter);
        binding.mainRv.setLayoutManager(new LinearLayoutManager(this));
        userObserver.observe(this, (userModels) -> {
            this.users = userModels;
            userAdapter.submitList(userModels);
            Objects.requireNonNull(binding.mainRv.getLayoutManager()).scrollToPosition(0);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_name: {
                List<UserModel> nameSortedList = users.stream().sorted(Comparator.comparing(UserModel::getName)).collect(Collectors.toList());
                userObserver.postValue(nameSortedList);
                return true;
            }
            case R.id.sort_city:
                List<UserModel> citySortedList = users.stream().sorted(Comparator.comparing(UserModel::getCity)).collect(Collectors.toList());
                userObserver.postValue(citySortedList);
                return true;
            case R.id.sort_age:
                List<UserModel> ageSortedList = users.stream().sorted(Comparator.comparing(UserModel::getAge)).collect(Collectors.toList());
                userObserver.postValue(ageSortedList);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}