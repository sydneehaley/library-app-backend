package com.wileyedge.libraryapp.dto;

public class FormatDto {
    private boolean isAvailable;

    // Getters and Setters

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "FormatDto{" +
                "isAvailable=" + isAvailable +
                '}';
    }
}
