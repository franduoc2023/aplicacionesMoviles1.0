import androidx.compose.ui.test.junit4.createComposeRule
import com.example.greetingcard.Metodos
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import com.google.firebase.auth.FirebaseAuth

class MetodosTest {

    private val fakeBaseDatos: FirebaseAuth = mockk()
    private lateinit var viewModel: Metodos

    @Before
    fun setUp() {
        viewModel = Metodos()
        viewModel.auth = fakeBaseDatos
    }

    @Test
    fun pruebaUnitariaFalsa() {
         viewModel.entradaEmail.value = ""
        viewModel.entradaPassword.value = ""

         viewModel.iniciarSesionFalsa()

         assertFalse(viewModel.loginExitoso.value)
    }

    @Test
    fun pruebaUnitariaTrue() {
         viewModel.entradaEmail.value = "valido"
        viewModel.entradaPassword.value = "valido"

         viewModel.iniciarSesionFalsa()

         assertTrue(viewModel.loginExitoso.value)
    }
}





//https://developer.android.com/codelabs/jetpack-compose-testing?hl=es-419#0
//https://developer.android.com/develop/ui/compose/quick-guides/content/video/testing-in-compose?hl=es-419
// se parece a las prubas unitarias de spring boot donde se mock la base de datos para simular y se prueba con asserFalse o Asser
// no entendi como simular la parte del metodo donde inicia la sesion de firebase como mock eso entonces se me ocurrio crear una funcion donde entregue true para saltarme eso