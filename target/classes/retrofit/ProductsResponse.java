package retrofit;

import lombok.Data;

@Data
public class ProductsResponse {

    private Integer id;
    private String title;
    private Integer price;
    private String categoryTitle;

}
