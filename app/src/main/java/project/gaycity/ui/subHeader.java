package project.gaycity.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class subHeader implements Parcelable {
    public final String name;
    public final int imageOrFragment;//this value is either a reference to an image or a fragment. if isFragment is true, its a fragment
    public boolean isFragment; //if true, it should change fragments when clicked

    public subHeader(String name, int imageOrFragment, boolean isFragment) {
        this.name = name;
        this.imageOrFragment = imageOrFragment;
        this.isFragment = isFragment;
    }

    protected subHeader(Parcel in) {
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
