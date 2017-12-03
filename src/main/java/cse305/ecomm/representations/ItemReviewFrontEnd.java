package cse305.ecomm.representations;

public class ItemReviewFrontEnd {
    private String customerName;
    private ItemReview reviewInfo;

    public ItemReviewFrontEnd(String customerName, ItemReview review) {
        this.customerName = customerName;
        this.reviewInfo = review;
    }

    public String getReviewText() { return this.reviewInfo.getReviewText(); }

    public String getCustomerName() { return this.customerName; }

    public Integer getCustomerID() { return this.reviewInfo.getCustomerId(); }

    public Integer getItemID() { return this.reviewInfo.getItemId(); }

    public Integer getRating() { return this.reviewInfo.getRating(); }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public void setReviewInfo(ItemReview review) { this.reviewInfo = review; }

}