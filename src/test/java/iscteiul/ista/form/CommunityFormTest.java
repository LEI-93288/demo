package iscteiul.ista.form;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CommunityFormTest {

    private CommunityFormPage page;

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openForm() {
        page = CommunityFormPage.openPage();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("1 - Submeter formulário com dados válidos")
    void shouldSubmitFormSuccessfully() {

        page.fillFirstName("Afonso")
                .fillLastName("Carolo")
                .fillHandle("acarolo")
                .fillPassword("Teste1234")
                .acceptMarketing()
                .submit();

        String msg = page.getSuccessMessageText();
        assertTrue(msg.toLowerCase().contains("thanks"),
                "Mensagem de sucesso não contém 'Thanks': " + msg);
    }
}
