package cs601.project1;

import java.util.List;

public class Review extends GsonObject {
    private String reviewerID;
    private String asin;
    private String reviewerName;
    private List<Integer> helpful;
    private String reviewText;
    private double overall;
    private String summary;
    private int unixReviewTime;
    private String reviewTime;

    public Review(String reviewerID, String asin, String reviewerName, List<Integer> helpful, String reviewText, double overall, String summary, int unixReviewTime, String reviewTime) {
        this.reviewerID = reviewerID;
        this.asin = asin;
        this.reviewerName = reviewerName;
        this.helpful = helpful;
        this.reviewText = reviewText;
        this.overall = overall;
        this.summary = summary;
        this.unixReviewTime = unixReviewTime;
        this.reviewTime = reviewTime;
    }

    /**
     * Getter for reviewerID
     * @return reviewerID
     */
    public String getReviewerID() {
        return reviewerID;
    }

    /**
     * Setter for reviewerID
     * @param reviewerID
     */
    public void setReviewerID(String reviewerID) {
        this.reviewerID = reviewerID;
    }

    /**
     * Getter for asin
     * @return asin
     */
    public String getAsin() {
        return asin;
    }

    /**
     * Setter for asin
     * @param asin
     */
    public void setAsin(String asin) {
        this.asin = asin;
    }

    /**
     * Getter for reviewerName
     * @return reviewerName
     */
    public String getReviewerName() {
        return reviewerName;
    }

    /**
     * Setter for reviewerName
     * @param reviewerName
     */
    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    /**
     * Getter for helpful
     * @return helpful
     */
    public List<Integer> getHelpful() {
        return helpful;
    }

    /**
     * Setter for helpful
     * @param helpful
     */
    public void setHelpful(List<Integer> helpful) {
        this.helpful = helpful;
    }

    /**
     * Getter for reviewText
     * @return reviewText
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Setter for reviewText
     * @param reviewText
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Getter for overall
     * @return overall
     */
    public double getOverall() {
        return overall;
    }

    /**
     * Setter for overall
     * @param overall
     */
    public void setOverall(double overall) {
        this.overall = overall;
    }

    /**
     * Getter for summary
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Setter for summary
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Getter for unixReviewTime
     * @return unixReviewTime
     */
    public int getUnixReviewTime() {
        return unixReviewTime;
    }

    /**
     * Setter for unixReviewTime
     * @param unixReviewTime
     */
    public void setUnixReviewTime(int unixReviewTime) {
        this.unixReviewTime = unixReviewTime;
    }

    /**
     * Getter for reviewTime
     * @return reviewTime
     */
    public String getReviewTime() {
        return reviewTime;
    }

    /**
     * Setter for reviewTime
     * @param reviewTime
     */
    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }


    public String getDataSet() {
        String dataSet = reviewText;
        return dataSet;
    }

    @Override
    public String toString() {
        return "reviewText='" + reviewText + "'" + System.lineSeparator();
    }
}
