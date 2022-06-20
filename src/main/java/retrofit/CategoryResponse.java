package retrofit;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private Integer id;
    private String title;
    private List<ProductsResponse> products;

}
