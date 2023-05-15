<template>
  <div class="register">
    <div class="posicion">
      <h2 class="titulo">Registro de voluntario</h2>
      <h3 class="subtitulo">Ingrese sus datos para registrarse en la plataforma</h3>
    </div>
    <form @submit.prevent="crearUsuario">
      <div class="form-group">
        <label for="name">Nombre de usuario<br></label>
        <input required type="text" v-model="username">
      </div>
      <div class="form-group">
        <label for="correo">Correo<br></label>
        <input required type="email" v-model="email">
      </div>
      <div class="form-group">
        <label for="password">Contraseña<br></label>
        <input required type="password" v-model="password">
      </div>
      <button  type="submit">Registrarse</button>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </form>
    <div class="login">
      <p class="texto">¿Ya tienes una cuenta?</p>
      <router-link to="/login" class="enlace">Inicia sesión</router-link>
    </div>
  </div>
</template>
  


<script lang="ts">
import axios from "axios";
export default {
  name: "RegisterPage",
  data() {
    return {
      username: "",
      email: "",
      rut: "",
      password: "",
      confirmPassword: "",
      errorMessage: null,
      valid: true,
    };
  },
  methods: {
    crearUsuario() {
      axios
        .post("/api/voluntarios", {
          nombre: this.username,
          correo: this.email,
          password: this.password,
        }, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }}
        )
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
          this.errorMessage = error.response.data.message;
        });
    }
  },
};
</script>
  
<style scoped>
.titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
}

.posicion {
  justify-content: start;

}

.register {
  display: grid;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  padding: 5rem;
  height: 45rem;
  font-family: "Open Sans", sans-serif;
}

.form-group {
  margin-bottom: 2rem;
}

label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}

input {
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: transparent;
  padding: 0.5rem 1rem;
  color: #363225;
  width: 25rem;
  outline: none;
}

button {
  display: inline-block;
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

button:hover {
  background-color: #c23414;
  cursor: pointer;
}

.enlace {
  grid-area: enlace;
  color: #FF5C39;
  text-decoration: none;
}

.texto {
  grid-area: texto;
  color: #363225;
  margin-right: 0.5rem;
}

.enlace:hover {
  color: #c23414;
}

.login {
  margin-top: 0.5rem;
  grid-template-areas: 'texto enlace';
  display: grid;
  justify-content: center;

}
</style>
```
  