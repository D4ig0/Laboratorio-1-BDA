<template>
  <main class="login">
    <div class="header">
      <h2 class="titulo">Inicia Sesión</h2>
      <h3>Ingrese sus datos de voluntario</h3>
    </div>
    <ErrorMessage v-show="errorMessage" :message="errorMessage" />
    <form @submit.prevent="login" class="form">
      <div class="form-group">
        <label for="username">Correo<br /></label>
        <input v-model="correo" type="email" required />
      </div>
      <div class="form-group">
        <label for="password">Contraseña<br /></label>
        <input v-model="password" type="password" required />
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
    <div class="register">
      <p class="texto">¿No tienes cuenta?</p>
      <router-link to="register" class="enlace">Registrate</router-link>
    </div>
  </main>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import type { AxiosResponse, AxiosError } from "axios";
import ErrorMessage from "@/components/ErrorMessage.vue";
import router from "@/router";
import { useAuthStore } from "@/stores/auth";
export default defineComponent({
  components: { ErrorMessage },
  name: "Login",
  data() {
    return {
      correo: "",
      password: "",
      errorMessage: "",
    };
  },
  methods: {
    async login(): Promise<void> {
      const authStore = useAuthStore();

      authStore
        .login(this.correo, this.password)
        .then((response: AxiosResponse<any, any>) => {
          this.setErrorMessageByStatus(response.status);
          router.replace({ path: '/' })
        })
        .catch((error: AxiosError | any) => {
          this.setErrorMessageByStatus(error.response.status);
        });
    },

    setErrorMessageByStatus(status: number): void {
      const statusMessage: { [key: number]: string } = {
        200: "",
        403: "Correo o contraseña incorrecta",
        500: "Ocurrió un error al iniciar sesión",
      };
      this.errorMessage = statusMessage[status] ?? "";
    },
  },
});
</script>

<style scoped>
.login {
  display: grid;
  align-items: center;
  justify-content: center;
  margin-top: 8rem;
  text-align: left;
  gap: 2rem;
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
  gap: 3rem;
}

.form-group label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}

.form-group input {
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: transparent;
  padding: 0.5rem;
  color: #363225;
  width: 25rem;
  outline: none;
  font-family: "Open Sans";
  font-weight: 500;
  color: #363225;
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

.register {
  display: grid;
  grid-template-areas: "texto enlace";
  justify-content: center;
  padding: 0.5rem;
  color: #363225;
}

.register .texto {
  font-weight: 500;
  margin-right: 0.5rem;
  color: #363225;
}

.register .enlace {
  font-weight: 700;
  color: #ff5c39;
  text-decoration: none;
}

.register .enlace:hover {
  color: #ec411a;
}
</style>