/**
 * Create by TaoTaoNing
 * 2019/4/9
 **/
public class Person {
    private Integer id;

    private String shop_id;

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +

                ", shop_id='" + shop_id + '\'' +
                '}';
    }
}
