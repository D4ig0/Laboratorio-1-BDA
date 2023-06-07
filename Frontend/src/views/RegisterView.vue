<template>
  <div class="register">
    <div class="header">
      <h2 class="titulo">Registro de voluntario</h2>
      <h3 class="subtitulo">Ingrese sus datos para registrarse en la plataforma</h3>
    </div>
    <ErrorMessage v-show="errorMessage" :message="errorMessage" />
    <form @submit.prevent="registerUser" class="form">

      <InputField fieldName="Nombre de usuario" @inputData="setUserName" inputType="text" />
      <InputField fieldName="Correo" inputType="email" @inputData="setCorreo" />
      <PasswordField @passwordData="setPassword" />

      <button type="submit">Registrarse</button>
    </form>
    <div class="login">
      <p class="texto">¿Ya tienes una cuenta?</p>
      <router-link to="/login" class="enlace">Inicia sesión</router-link>
    </div>
  </div>
</template>
  
<script lang="ts">
import axios from "axios";

import type { AxiosResponse, AxiosError } from "axios";
import InputField from "@/components/InputField.vue";
import router from "@/router";
import PasswordField from "@/components/PasswordField.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";


export default {
  components: { PasswordField, InputField, ErrorMessage },
  name: "RegisterPage",
  data() {
    return {
      username: "",
      email: "",
      password: "",
      errorMessage: "",
      valid: true,
    };
  },
  methods: {
    registerUser() {
      axios
        .post("/api/voluntarios", {
          nombre: this.username,
          correo: this.email,
          password: this.password,
        }, {
          headers: { "Content-Type": "multipart/form-data" }
        })

        .then((response: AxiosResponse<any, any>) => {
          this.setErrorMessageByStatus(response.data);
          router.replace({ path: "/login" });
          alert("¡Usuario creado exitosamente!");
        })

        .catch((error: AxiosError | any) => {
          this.setErrorMessageByStatus(error.response.data);
        })
    },

    setErrorMessageByStatus(errorMessage: string): void {
      this.errorMessage = errorMessage || "";
    },

    setUserName(username: string) {
      this.username = username;
    },
    setPassword(password: string) {
      this.password = password;
    },

    setCorreo(correo: string) {
      this.email = correo;
    },

  }
};
</script>
  
<style scoped>
.register {
  display: grid;
  align-items: center;
  justify-content: center;
  text-align: left;
  gap: 2rem;
  padding: 5rem;
  height: 45rem;
  font-family: "Open Sans", sans-serif;
}

.header .titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;

  color: #363225;
}

.header h3 {
  font-size: 1rem;
  font-weight: 500;
  color: #363225;
}

.form {
  display: grid;
  gap: 2.5rem;
}


.form button {
  background-color: #FF5C39;
  color: #fff;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  width: 25rem;
  border: none;
  border-radius: 0.2rem;
  box-shadow: 0rem 0.1rem 0.1rem rgba(0, 0, 0, 0.25);
  transition: background-color 0.2s ease-in-out;
}

.form button:hover {
  background-color: #c23414;
  cursor: pointer;
}

.register .enlace {
  grid-area: "texto enlace";
  color: #FF5C39;
  text-decoration: none;
  font-weight: 700;
}

.register .texto {
  font-weight: 500;
  color: #363225;
  margin-right: 0.5rem;
}

.register.enlace:hover {
  color: #c23414;
}

.login {
  margin-top: 0.5rem;
  grid-template-areas: 'texto enlace';
  display: grid;
  padding: 0.5rem;
  justify-content: center;

}
</style>
```
  