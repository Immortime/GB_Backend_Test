import net.javacrumbs.jsonunit.JsonAssert;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

public class AbstractClass {

    public void assertJson(Object expected, Object actually) {
        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    public String getResource(String name) throws Exception {
        String resource = getClass().getSimpleName() + "/" + name;
        InputStream inputStream = getClass().getResourceAsStream(resource);
        assert inputStream != null;
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return new String(bytes, StandardCharsets.UTF_8);
    }


}
