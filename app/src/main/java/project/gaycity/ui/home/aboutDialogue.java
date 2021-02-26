package project.gaycity.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import project.gaycity.R;

public class aboutDialogue extends DialogFragment {
    private static AlertDialog dialog;
    public aboutDialogue(){

    }

    public static aboutDialogue newInstance(){
        return new aboutDialogue();
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.dialogue_about, container);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> dialog.dismiss());
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }
}
