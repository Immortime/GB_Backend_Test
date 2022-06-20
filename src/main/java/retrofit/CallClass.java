package retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CallClass {

    @GET("api/v1/categories/{id}")
    Call<CategoryResponse> getCategory(@Path("id") Integer id);

    @GET("api/v1/categories/{id}")
    Call <CategoryResponseWithoutProducts> getOnlyCategory(@Path("id") Integer id);

    @GET("api/v1/products")
    Call<List<ProductsResponse>> getProduct();

}
