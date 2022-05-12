package com.app.listaActividades.Views.Auth.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.listaActividades.R
import com.app.listaActividades.util.Screens


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    LazyColumn(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item {
            Box {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(241.dp)
                        .background(MaterialTheme.colors.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo" ,
                        modifier = Modifier.width(150.dp).height(150.dp)
                    )

                }

            }

            Column(
                Modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .fillMaxSize()
            ) {

                Column(
                    Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Bienvenido Nuevamente",
                        Modifier
                            .padding(top = 16.dp, bottom = 16.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                // The text will remain intact when there is configuration changes
                var email by rememberSaveable { viewModel.gmail }
                var password by rememberSaveable { viewModel.password }


                Column(
                    Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = email,
                        onValueChange = { typedEmail ->
                            email = typedEmail
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .padding(bottom = 14.dp),
                        singleLine = true,
                        label = { Text(text = "Email") },
                        placeholder = { Text("john@email.com") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Email"
                            )
                        },
                    )

                    // Password visibility will remain intact when there is configuration changes
                    var passwordVisibility by remember {
                        mutableStateOf(false)
                    }

                    // Icon button for visibility of password
                    val passwordTrailingIcon = if (passwordVisibility)
                        painterResource(id = R.drawable.ic_twotone_visibility_on)
                    else
                        painterResource(id = R.drawable.ic_twotone_visibility_off)

                    OutlinedTextField(
                        value = password,
                        onValueChange = { typedPassword ->
                            password = typedPassword
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Contraseña") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisibility = !passwordVisibility
                                }
                            ) {
                                Icon(
                                    painter = passwordTrailingIcon,
                                    contentDescription = "Password Visibility"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else
                            PasswordVisualTransformation()
                    )
                    //Login Button

                    Spacer(modifier = Modifier.height(36.dp))
                    Button(
                        onClick = {
                            viewModel.loguear(email, password, navController)
                        },
                        Modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .height(64.dp)
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            "Loguearse",
                            fontSize = 17.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(26.dp))

                    ClickableText(text = AnnotatedString(
                        "¿No tenes cuenta? , Registrate!",
                        spanStyle = SpanStyle(
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp
                        )
                    ), onClick = {
                        navController.navigate(Screens.RegisterScreen.route)
                    } )


                }


            }

            if (viewModel.state.value.isLoading) {
                CircularProgressIndicator()
            }

            if (viewModel.user != null && !viewModel.state.value.isSuccess) {
                LaunchedEffect(true){
                    viewModel.goToAdminOrEmpleado(navController ,viewModel.user.uid )
                }
            }
            if(viewModel.state.value.isError != null) {
                Snackbar(
                    modifier = Modifier.padding(top = 90.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    actionOnNewLine = false,
                    action = {
                        TextButton(onClick = {
                            viewModel.dismiss()
                        }) {
                            Text(text = "Okey")
                        }
                    }
                ) {
                    Text(text =viewModel.state.value.isError!! )
                }
            }
        }

    }



}