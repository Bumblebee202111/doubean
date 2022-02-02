package com.doubean.ford.ui.Search;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.doubean.ford.data.Group;
import com.doubean.ford.data.repository.GroupRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchViewModel extends ViewModel {
    private final LiveData<List<Group>> results;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private final GroupRepository groupRepository;

    public SearchViewModel(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        results = Transformations.switchMap(query, search -> {
            if (search == null || search.trim().length() == 0) {
                return new MutableLiveData<>();
            } else {
                return groupRepository.search(search);
            }
        });
    }

    @VisibleForTesting
    public LiveData<List<Group>> getResults() {
        return results;
    }

    public void setQuery(@NonNull String originalInput) {
        String input = originalInput.toLowerCase(Locale.getDefault()).trim();
        if (Objects.equals(input, query.getValue())) {
            return;
        }
        query.setValue(input);
    }
}
