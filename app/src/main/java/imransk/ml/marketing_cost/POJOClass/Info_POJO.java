package imransk.ml.marketing_cost.POJOClass;

public class Info_POJO {

    String id;

    String item;
    String quentity;
    String price;
    String spinner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public Info_POJO(String id, String item, String quentity, String price, String spinner) {
        this.id = id;
        this.item = item;
        this.quentity = quentity;
        this.price = price;
        this.spinner = spinner;
    }

    public Info_POJO(String item, String quentity, String price, String spinner) {
        this.item = item;
        this.quentity = quentity;
        this.price = price;
        this.spinner = spinner;
    }
}
