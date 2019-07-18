import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

/**
 * Create by TaoTaoNing
 * 2019/4/9
 **/
public class TestJson {
    public static void main(String[] args) {

        String data = "[{\"id\":43,\"fs_id\":1,\"shop_id\":11,\"shop_name\":\"pctb\",\"category_id\":2,\"d_fee_rate\":\"2\",\"_id\":40,\"_uid\":40,\"_state\":\"modified\"}]";
        String data1 = data.replaceAll("\"_id\":\\d+,","");
        System.out.println(data1);
        List<NetShopFeeDto> people = JSONArray.parseArray(data1, NetShopFeeDto.class);

        for (NetShopFeeDto o: people
             ) {
            System.out.println(o);
        }








 //       String array = "[{\"id\":1,\"_id\":2,\"shop_id\":\"3\"},{\"id\":2,\"_id\":3,\"shop_id\":\"4\"}]";

//        String a = "[{\"id\":1,\"_id\":\"2\",\"shop_id\":\"3\"}]";

      //  Gson gson = new Gson();
//        List<Person> people = JSONArray.parseArray(array, Person.class);
//
//        for (Person o: people
//             ) {
//            System.out.println(o);
//        }

       // String s = gson.toJson(p);
     //   System.out.println(s);


       // System.out.println(Arrays.toString(gson.fromJson(array, Person[].class)));
        //gson.fromJson(array, List.class);

    }

}