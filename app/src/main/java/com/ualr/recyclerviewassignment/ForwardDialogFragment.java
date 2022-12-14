package com.ualr.recyclerviewassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class ForwardDialogFragment extends DialogFragment {
    private static final String TAG = ForwardDialogFragment.class.getSimpleName();
    private static final String SELECT_KEY = "selectedIndex";
    private SharedViewModel mViewModel;


    public static ForwardDialogFragment newInstance(int selectedIndex) {
        ForwardDialogFragment fragment = new ForwardDialogFragment();
        Bundle args = new Bundle();
        args.putInt(SELECT_KEY, selectedIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        mViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forward_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int selectedIndex = getArguments().getInt(SELECT_KEY);

        if (selectedIndex >= 0) {
            final Inbox selectedItem = mViewModel.getInboxList().getValue().get(selectedIndex);

            Button sendBtn = view.findViewById(R.id.dialog_send_btn);
            final EditText nameET = view.findViewById(R.id.dialog_name);
            final EditText emailET = view.findViewById(R.id.dialog_to);
            final EditText contentET = view.findViewById(R.id.dialog_msg);

            nameET.setText(selectedItem.getFrom());
            emailET.setText(selectedItem.getEmail());
            contentET.setText(selectedItem.getMessage());

            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newName = nameET.getText().toString();
                    String newEmail = emailET.getText().toString();
                    String newContent = contentET.getText().toString();

                    char firstLetter = newName.charAt(0);
                    String newInitial = "" + firstLetter;

                    Inbox forwardEmail = new Inbox();
                    forwardEmail.setFrom(newName);
                    forwardEmail.setInitials(newInitial);
                    forwardEmail.setEmail(newEmail);
                    forwardEmail.setMessage(newContent);
                    forwardEmail.setDate(selectedItem.getDate());
                    forwardEmail.setSelected(false);

                    List<Inbox> currentEmails = mViewModel.getInboxList().getValue();
                    currentEmails.set(selectedIndex, forwardEmail);
                    mViewModel.setInboxList(currentEmails);
                    dismissDialog();
                }
            });
        }
        else {
            dismissDialog();
        }
    }
    public void dismissDialog() {
        this.dismiss();
    }
}