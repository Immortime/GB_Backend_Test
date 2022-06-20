import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit.ApiMarket;
import retrofit.ProductsResponse;

import java.io.IOException;
import java.util.List;

public class ProductsTests extends AbstractClass {

    private static ApiMarket market;
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(Resources.getResourceAsStream("myBatisConfig.xml"));

    public ProductsTests() throws IOException {
    }

    public List<Products> ormMethod() {
        try (SqlSession session = sessionFactory.openSession()) {
            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
            ProductsExample example = new ProductsExample();
            example.createCriteria()
                    .getAllCriteria();
            List<Products> products = productsMapper.selectByExample(example);
            return products;
        }
    }

    @BeforeAll
    static void beforeAll() {
        market = new ApiMarket();
    }

    @Test
    void getProductTest() throws Exception {
        List<ProductsResponse> products = market.getProduct();
        assertJson(getResource("products.json"), products);
    }

    @Test
    void getProductWithORMTest() throws Exception {
        List<ProductsResponse> products = market.getProduct();
        assertJson(ormMethod(), products);
    }
}
