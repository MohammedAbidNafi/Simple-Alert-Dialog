package com.margsapp.simplealertdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import java.lang.ref.WeakReference;

public class SimpleAlertDialog {

    private String titleTxt,descriptionTxt,pTxt,nTxt;

    @Visibility
    private int visibility;
    private SimpleAlertDialogListener pListener,nListener;
    @ColorInt
    private int pBtnColor,pBtnTxtColor, nBtnColor,nBtnTxtColor,backColor,titleColor,descColor;


    private boolean cancel;
    private Dialog dialog;

    private SimpleAlertDialog(Builder builder){

        this.titleTxt = builder.titleTxt;
        this.descriptionTxt = builder.descriptionTxt;
        this.pTxt = builder.pTxt;
        this.nTxt = builder.nTxt;
        this.visibility = builder.visibility;
        this.pListener = builder.pListener;
        this.nListener = builder.nListener;
        this.cancel = builder.cancel;
        this.dialog = builder.dialog;
        this.pBtnColor = builder.pBtnColor;
        this.pBtnTxtColor = builder.pBtnTxtColor;
        this.nBtnColor = builder.nBtnColor;
        this.nBtnTxtColor = builder.nBtnTxtColor;
        this.backColor = builder.backColor;
        this.titleColor = builder.titleColor;
        this.descColor = builder.descColor;
    }

    public SimpleAlertDialog show(){
        dialog.show();
        return this;
    }

    public SimpleAlertDialog dismiss(){
        dialog.dismiss();
        return this;
    }


    public static class Builder {

        private String titleTxt,descriptionTxt, pTxt, nTxt;
        @Visibility
        private int visibility;
        private SimpleAlertDialogListener pListener, nListener;

        @ColorInt int pBtnColor;
        @ColorInt int pBtnTxtColor;
        @ColorInt int nBtnColor;
        @ColorInt int nBtnTxtColor;
        @ColorInt int backColor;
        @ColorInt int titleColor;
        @ColorInt int descColor;

        private boolean cancel;

        private WeakReference<Context> context;
        private Dialog dialog;


        private Builder(Context context) {
            // use #with
            this.context = new WeakReference<>(context);
        }

        public static Builder with(@NonNull Context context) {
            return new Builder(context);
        }

        public Builder setTitle(String titletxt) {
            this.titleTxt = titletxt;
            return this;
        }

        public Builder setTitleColor(@ColorInt int titleColor){
            this.titleColor = titleColor;
            return this;
        }

        public Builder setDescription(String descriptionTxt){
            this.descriptionTxt = descriptionTxt;
            return this;
        }

        public Builder setDescriptionColor(@ColorInt int descColor){
            this.descColor = descColor;
            return this;
        }

        public Builder setPositiveButtonColor(@ColorInt int pBtnColor){
            this.pBtnColor = pBtnColor;
            return this;
        }

        public Builder setPositiveButtonTextColor(@ColorInt int pBtnTxtColor){
            this.pBtnTxtColor = pBtnTxtColor;
            return this;
        }

        public Builder setNegativeButtonColor(@ColorInt int nBtnColor){
            this.nBtnColor = nBtnColor;
            return this;
        }

        public Builder setNegativeButtonTextColor(@ColorInt int nBtnTxtColor){
            this.nBtnTxtColor = nBtnTxtColor;
            return this;
        }

        public Builder setPositiveText(String positiveText) {
            this.pTxt = positiveText;
            return this;
        }

        public Builder setNegativeText(String negativeText) {
            this.nTxt = negativeText;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int backColor){
            this.backColor = backColor;
            return this;
        }


        public Builder onPositiveClicked(@Nullable SimpleAlertDialogListener pListener) {
            this.pListener = pListener;
            return this;
        }

        public Builder onNegativeClicked(@Nullable SimpleAlertDialogListener nListener) {
            this.nListener = nListener;
            return this;
        }


        public Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }


        public SimpleAlertDialog build() {



            TextView Title,Description;
            AppCompatButton pBtn,nBtn;
            LinearLayout layout;


            layout = dialog.findViewById(R.id.layout);
            Title = dialog.findViewById(R.id.title);
            Description = dialog.findViewById(R.id.description);
            pBtn = dialog.findViewById(R.id.pBtn);
            nBtn = dialog.findViewById(R.id.nBtn);


            Title.setText(titleTxt);


            if(descriptionTxt != null){
                Description.setText(descriptionTxt);
            }

            if(backColor != 0){
                layout.setBackgroundColor(backColor);
            }

            if (pBtnColor != 0) {
                pBtn.setBackgroundColor(pBtnColor);
            }

            if(pBtnTxtColor != 0){
                pBtn.setTextColor(pBtnTxtColor);
            }

            if(pTxt != null){
                pBtn.setText(pTxt);
            }

            if (nBtnColor != 0) {
                nBtn.setBackgroundColor(pBtnColor);
            }

            if(nBtnTxtColor != 0){
                nBtn.setTextColor(nBtnTxtColor);
            }

            if(nTxt != null){
                nBtn.setText(nTxt);
            }


            if (pListener != null) {
                pBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pListener.onClick(dialog);
                        dialog.dismiss();
                    }
                });
            }
            if (nListener != null) {
                nBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nListener.onClick(dialog);
                        dialog.dismiss();
                    }
                });
            }
            dialog = new Dialog(context.get());
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancel);
            dialog.setContentView(R.layout.alert_dialog);


            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


            return new SimpleAlertDialog(this);
        }


    }

}
