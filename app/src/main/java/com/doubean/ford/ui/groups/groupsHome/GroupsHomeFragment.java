package com.doubean.ford.ui.groups.groupsHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.doubean.ford.R;
import com.doubean.ford.adapters.GroupFollowedAdapter;
import com.doubean.ford.databinding.FragmentGroupsBinding;
import com.doubean.ford.util.InjectorUtils;

public class GroupsHomeFragment extends Fragment {

    private GroupsHomeViewModel groupsHomeViewModel;
    private GroupsHomeViewModelFactory factory;
    private FragmentGroupsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        factory = InjectorUtils.provideGroupsViewModelFactory(requireContext());
        groupsHomeViewModel =
                new ViewModelProvider(this, factory).get(GroupsHomeViewModel.class);
        binding = FragmentGroupsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        GroupFollowedAdapter adapter = new GroupFollowedAdapter();
        binding.followedList.setAdapter(adapter);
        groupsHomeViewModel.getFollowedList().observe(getViewLifecycleOwner(), adapter::submitList);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.inflateMenu(R.menu.groups_menu);
        binding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_search:
                    NavDirections direction = GroupsHomeFragmentDirections.actionNavigationGroupsToNavigationGroupSearch();
                    Navigation.findNavController(view).navigate(direction);
                    return true;
                case R.id.action_settings:

                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}