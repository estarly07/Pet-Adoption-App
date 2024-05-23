package com.estarly.petadoptionapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.MainActivity
import com.estarly.petadoptionapp.ui.ActivityStructure
import com.estarly.petadoptionapp.ui.login.screens.LoginScreen
import com.estarly.petadoptionapp.ui.login.screens.RegisterScreen
import com.estarly.petadoptionapp.ui.login.viewmodels.LoginViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(), ActivityStructure {
    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private val googleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
    private val googleSignInClient by lazy { GoogleSignIn.getClient(this, googleSignInOptions) }
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)
        initView()
        initObservers()
        getData()
    }
    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        try {
            loginViewModel.closeDialogGoogle()
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if(task.isSuccessful){
                task.getResult(ApiException::class.java).idToken?.let {idToken ->
                    loginViewModel.loginByGoogle(idToken)
                }
            }
        }catch(e : ApiException){
            e.printStackTrace()
        }

    }
    /**
     *
     * Configura e inicia el Splash Screen de la aplicación.
     *
     * Esta función privada inicializa el Splash Screen de la aplicación usando `installSplashScreen()`.
     * Utiliza una condición proporcionada por `loginViewModel.isSplashShow.value` para determinar
     * cuándo mantener el Splash Screen visible. La función `setKeepOnScreenCondition` se emplea para
     * mantener el Splash Screen visible hasta que la condición se evalúe como falsa, permitiendo
     * así controlar la duración del Splash Screen según el estado de la aplicación.
     */
    private fun initSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{
            loginViewModel.isSplashShow.value
        }
    }
    /**
     * Inicializa las vistas de la pantalla.
     *
     * Esta función se utiliza para configurar e inicializar todos los elementos visuales (views) de la pantalla.
     * Se llama generalmente en el proceso de creación de la pantalla para asegurarse de que todas las vistas
     * están correctamente configuradas antes de interactuar con ellas.
     */
    override fun initView() {
        setContent {
            val showRegisterScreen by loginViewModel.showRegisterScreen.observeAsState(initial = false)
            PetAdoptionAppTheme(darkTheme = false) {
                if(showRegisterScreen) RegisterScreen(context = this,loginViewModel)
                else LoginScreen(context = this,loginViewModel)
            }
        }
        auth = FirebaseAuth.getInstance()
    }
    /**
     *
     * Inicializa los observadores de la pantalla.
     *
     * Esta función es opcional y se utiliza para configurar los observadores necesarios, como los LiveData del
     * ViewModel.
     */
    override fun getData() {
        with(loginViewModel){
            isLogin()
        }
    }
    /**
     *
     * Esta función recupera la información necesaria para mostrar en la Activity.
     *
     * Esta función es utilizada para obtener los datos que serán presentados en la Activity.
     * La función encapsula la lógica de recuperación de datos desde las fuentes correspondientes,
     * que pueden incluir bases de datos locales, servicios web, entre otro
     *
     */
    override fun initObservers() {
        with(loginViewModel){
            goToHome.observe(this@LoginActivity){
                it?.let {
                    if(it){
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }

                }
            }
            showDialogGoogle.observe(this@LoginActivity){
                if(it){ signInLauncher.launch(googleSignInClient.signInIntent) }
            }
        }
    }
    override fun onBackPressed() { loginViewModel.onBackPressed(this) }
}