import android.os.Parcel;
import android.os.Parcelable;

public class Auto implements Parcelable {
    private int image;
    private String licensePlate;
    private String model;

    public static class AutoMetaData {
        public static String LICENSEPLATE = "_licensePlate";
        public static String IMAGE = "image";
        public static String MODEL = "model";
        public static String TABLE_NAME = "Auto";
        public static String[] COLUMNS = new String[] { LICENSEPLATE, IMAGE, MODEL };
    }

    public static final Parcelable.Creator<Auto> CREATOR = new Parcelable.Creator<Auto>() {

        @Override
        public Auto createFromParcel(Parcel source) {
            return new Auto(source);
        }

        @Override
        public Auto[] newArray(int size) {
            return new Auto[size];
        }
    };

    public Auto() {
    }

    protected Auto(Parcel in) {
        image = in.readInt();
        licensePlate = in.readString();
        model = in.readString();
    }

    public String getModel(){
        return model;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public int getImage(){
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(licensePlate);
        dest.writeString(model);
    }
}
