package project.gaycity.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class subHeader implements Parcelable {
    public final String name;
    public final int imageOrFragment;
    public boolean isFragment;

    public subHeader(String name, int image, boolean isFragment) {
        this.name = name;
        this.imageOrFragment = image;
        this.isFragment = isFragment;
    }

    protected subHeader(Parcel in){
        name = in.readString();
        imageOrFragment = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<subHeader> CREATOR = new Creator<subHeader>() {
        @Override
        public subHeader createFromParcel(Parcel in) {
            return new subHeader(in);
        }

        @Override
        public subHeader[] newArray(int size) {
            return new subHeader[size];
        }
    };
}
