<template>
  <div class="register">
    <div class="header">
      <h2 class="titulo">Registro de voluntario</h2>
      <h3 class="subtitulo">
        Ingrese sus datos para registrarse en la plataforma
      </h3>
    </div>
    <ErrorMessage v-show="errorMessage" :message="errorMessage" />
    <form @submit.prevent="registerUser" class="form">
      <InputField
        fieldName="Nombre de usuario"
        @inputData="setUserName"
        inputType="text"
      />
      <InputField fieldName="Correo" inputType="email" @inputData="setCorreo" />
      <InputPlace @placeData="setPlaceData"/>
      <PasswordField @passwordData="setPassword" />
      <button type="submit">Registrarse</button>
    </form>
    <div class="login">
      <p class="texto">¿Ya tienes una cuenta?</p>
      <router-link to="login" class="enlace">Inicia sesión</router-link>
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
import InputPlace from "@/components/InputPlace.vue"

export default {
  components: { PasswordField, InputField, ErrorMessage, InputPlace },
  name: "RegisterPage",
  data() {
    return {
      username: "",
      email: "",
      latitud: 0,
      longitud: 0,
      password: "",
      errorMessage: "",
      valid: true,
    };
  },
  methods: {
    registerUser() {
      axios
        .post(
          "/api/voluntarios",
          {
            nombre: this.username,
            correo: this.email,
            password: this.password,
            longitud: this.longitud,
            latitud: this.latitud
          },
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        )
        .then((response: AxiosResponse<any, any>) => {
          this.setErrorMessageByResponse(response);
          router.replace({ path: "/login" });
          alert("¡Usuario creado exitosamente!");
        })
        .catch((error: AxiosError | any) => {
          this.setErrorMessageByResponse(error.response);
        });
    },

    setErrorMessageByResponse(response: any): void {
      if (response.status == 200) {
        this.errorMessage = "";
      } else if (response.data == "") {
        this.errorMessage = "Ocurrió un error al registrarse";
      } else {
        this.errorMessage = response.data;
      }
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

    setPlaceData(latitude: number, longitude: number) {
      this.latitud = latitude
      this.longitud = longitude
    }
  },
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

.register .header .titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;

  color: #363225;
}

.register .header h3 {
  font-size: 1rem;
  font-weight: 500;
  color: #363225;
}

.form {
  display: grid;
  gap: 2.5rem;
}

.form button {
  background-color: #ff5c39;
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
  background-color: #ec411a;
  cursor: pointer;
}

.login {
  display: flex;
  justify-content: center;
  padding: 0.5rem;
}

.login .texto {
  font-weight: 500;
  color: #363225;
  margin-right: 0.8rem;
}

.login .enlace {
  font-weight: 700;
  color: #ff5c39;
  text-decoration: none;
}

.login .enlace:hover {
  color: #ec411a;
}
</style>
