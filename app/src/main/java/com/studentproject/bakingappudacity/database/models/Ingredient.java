package com.studentproject.bakingappudacity.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Ingredient implements Parcelable {

    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Ingredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getFullIngredient() {

        return getQuantity() + " " + convertMeasure(getMeasure(), getQuantity()) + " " + getIngredient();

    }

    private String convertMeasure(String measureCode, double quantity) {

        String measureConverted = "";
        String s = "s";

        switch (measureCode) {
            case "TSP":
                measureConverted = "teaspoon";
                break;
            case "TBLSP":
                measureConverted = "tablespoon";
                break;
            case "K":
                measureConverted = "kilogram";
                break;
            case "CUP":
                measureConverted = "cup";
                break;
            case "G":
                measureConverted = "gram";
                break;
            case "OZ":
                measureConverted = "ounce";
                break;
            case "UNIT":
                measureConverted = "unit";
                break;
        }

        if (quantity > 1) {
            return measureConverted.concat(s);
        }

        return measureConverted;


    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }
}
