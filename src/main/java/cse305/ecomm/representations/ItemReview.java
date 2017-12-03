package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class ItemReview {
    @NotNull
    private Integer customerId;
    @NotNull
    private Integer itemId;
    private String reviewText;
    private Integer rating;

    public ItemReview(Integer customerId, Integer itemId, String reviewText, Integer rating) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Integer getCustomerId() { return customerId; }

    public Integer getItemId() {
        return itemId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
