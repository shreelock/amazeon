package cse305.ecomm.representations;

public class ItemReviewResponse {
    private String customerName;
    private ItemReview reviewInfo;

    public ItemReviewResponse(String customerName, ItemReview review) {
        this.customerName = customerName;
        this.reviewInfo = review;
    }

    public String getCustomerName() { return customerName; }

    public ItemReview getReviewInfo() { return reviewInfo; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public void setReviewInfo(ItemReview review) { this.reviewInfo = review; }

}