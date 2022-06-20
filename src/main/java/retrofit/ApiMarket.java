package retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ApiMarket {

    private final CallClass api;

    public ApiMarket() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(System.out::println);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        api = new Retrofit.Builder()
                .baseUrl("https://minimarket1.herokuapp.com/market/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CallClass.class);
    }

    public CategoryResponse getCategory(int id) {
        return CallExecutor.executeCall(api.getCategory(id));
    }

    public CategoryResponseWithoutProducts getOnlyCategory(int id) {
        return CallExecutor.executeCall(api.getOnlyCategory(id));
    }

    public List<ProductsResponse> getProduct() {
        return CallExecutor.executeCall(api.getProduct());
    }
}
