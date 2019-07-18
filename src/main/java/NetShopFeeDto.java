/**
 * Create by TaoTaoNing
 * 2019/4/9
 **/
public class NetShopFeeDto {
    /* 自增编号 */
    private String id;
    /* 分机机构编号 */
    private String fs_id;
    /* 店铺编号 */
    private String shop_id;
    /* 类别编号 */
    private String category_id;
    /* 平台扣点 */
    private String d_fee_rate;

    /** 默认的构造函数 */
    public NetShopFeeDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFs_id() {
        return fs_id;
    }

    public void setFs_id(String fs_id) {
        this.fs_id = fs_id;
    }
    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    public String getD_fee_rate() {
        return d_fee_rate;
    }

    public void setD_fee_rate(String d_fee_rate) {
        this.d_fee_rate = d_fee_rate;
    }

    @Override
    public String toString() {
        return "NetShopFeeDto{" +
                "id='" + id + '\'' +
                ", fs_id='" + fs_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", d_fee_rate='" + d_fee_rate + '\'' +
                '}';
    }
}
