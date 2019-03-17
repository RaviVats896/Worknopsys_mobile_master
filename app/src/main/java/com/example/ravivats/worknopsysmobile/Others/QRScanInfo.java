package com.example.ravivats.worknopsysmobile.Others;

public class QRScanInfo {
    private String imageUrl;
    private String description;

    public QRScanInfo(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QRScanInfo{" + "imageUrl='" + imageUrl + '\'' + ", description='" + description + '\'' + '}';
    }
}
