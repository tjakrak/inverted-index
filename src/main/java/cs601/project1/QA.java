package cs601.project1;

public class QA extends GsonObject {
    private String questionType;
    private String asin;
    private String answerTime;
    private int unixTime;
    private String question;
    private String answer;

    public QA(String questionType, String asin, String answerTime, int unixTime, String question, String answer) {
        this.questionType = questionType;
        this.asin = asin;
        this.answerTime = answerTime;
        this.unixTime = unixTime;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Getter for questionType
     * @return the questionType
     */
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * Getter for Asin
     * @return Asin
     */
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    /**
     * Getter for questionType
     * @return the questionType
     */
    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    /**
     * Getter for unixTime
     * @return unixTime
     */
    public int getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(int unixTime) {
        this.unixTime = unixTime;
    }

    /**
     * Getter for question
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter for answer
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * A method to
     * @return a String containing question and answer
     */
    public String getDataSet() {
        String dataSet = question + " " + answer;
        return dataSet;
    }

    @Override
    public String toString() {
        return "question='" + question + "'" + System.lineSeparator() + "answer='" + answer + "'" + System.lineSeparator();
    }
}
