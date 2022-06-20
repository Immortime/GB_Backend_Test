import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.model.Categories;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit.ApiMarket;
import retrofit.CategoryResponse;
import retrofit.CategoryResponseWithoutProducts;

import java.io.IOException;
import java.util.List;

public class CategoryTests extends AbstractClass {

    private static ApiMarket market;

    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(Resources.getResourceAsStream("myBatisConfig.xml"));

    public CategoryTests() throws IOException {
    }

    public Categories ormMethod() {
        try (SqlSession session = sessionFactory.openSession()) {
            CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);

            Categories categories = categoriesMapper.selectByPrimaryKey(1L);
            return categories;
        }
    }

    @BeforeAll
    static void beforeAll() {
        market = new ApiMarket();
    }

    @Test
    void getCategoryTest() throws Exception {
        CategoryResponse category = market.getCategory(1);
        assertJson(getResource("category.json"), category);
    }

    @Test
    void getCategoryFromORMTest() throws Exception {
        CategoryResponseWithoutProducts category = market.getOnlyCategory(1);
        assertJson(category, ormMethod());
    }
}
