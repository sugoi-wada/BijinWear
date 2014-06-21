package bijinwear.com.bijinwear;

/**
 * Created by sugoi_wada on 2014/06/21.
 */
public class Bijin {
    private long id;
    private String link;
    private String category;
    private String thumb;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    public String getThumb() {
        return thumb;
    }
}
