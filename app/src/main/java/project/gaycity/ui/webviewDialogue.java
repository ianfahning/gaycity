package project.gaycity.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import project.gaycity.R;

public class webviewDialogue extends DialogFragment {

    private static Dialog dialog;
    private String link;

    public webviewDialogue(String link){
        this.link = link;
    }

    public static webviewDialogue newInstance(String link){
        webviewDialogue frag = new webviewDialogue(link);
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_webview, container);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WebView webView = view.findViewById(R.id.webView);
        ProgressBar loading = view.findViewById(R.id.loading);
        //needed for pages that use javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.requestFocus(View.FOCUS_DOWN);
        //when done loading remove the loading symbol
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl(link);
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> dialog.dismiss());
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog alertDialogBuilder = new Dialog(getActivity());
        dialog = alertDialogBuilder;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
        return dialog;
    }
}
