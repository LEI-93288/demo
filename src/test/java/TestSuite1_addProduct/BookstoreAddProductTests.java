package TestSuite1_addProduct;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Testes de Adição de Produtos - Bookstore")
public class BookstoreAddProductTests {

    private BookstorePage bookstorePage;
    private static final String BASE_URL = "https://vaadin-bookstore-example.demo.vaadin.com/";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @BeforeEach
    public void setup() {
        open(BASE_URL);
        bookstorePage = new BookstorePage();
    }

    @AfterEach
    public void tearDown() {
        // Limpa cookies/storage para garantir que o próximo teste obriga o login
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Deve fazer login com credenciais válidas")
    public void testLoginWithValidCredentials() {
        bookstorePage.login(USERNAME, PASSWORD);
        assert bookstorePage.isGridVisible() : "Grid não encontrada após login";
        System.out.println("Login realizado com sucesso");
    }

    @Test
    @DisplayName("Deve abrir o formulário de adição de produto")
    public void testOpenAddProductForm() {
        bookstorePage.login(USERNAME, PASSWORD);
        bookstorePage.openAddProductForm();
        System.out.println("Formulário aberto com sucesso");
    }

    @Test
    @DisplayName("Deve adicionar um novo produto com sucesso")
    public void testAddNewProduct() {
        String productTitle = "Livro Teste " + System.currentTimeMillis();

        String price = "30";
        String stock = "10";

        bookstorePage.login(USERNAME, PASSWORD);
        bookstorePage.openAddProductForm();

        bookstorePage.fillProductForm(productTitle, price, stock);
        bookstorePage.submitProduct();

        // O filtro interno do método trata de encontrar o livro mesmo com lazy loading
        boolean productFound = bookstorePage.isProductVisibleInList(productTitle);

        assert productFound : "Produto não encontrado na lista (falha na gravação?)";
        System.out.println("Produto adicionado e verificado: " + productTitle);
    }
}