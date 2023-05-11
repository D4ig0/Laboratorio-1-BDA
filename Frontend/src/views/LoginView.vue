<template>
  <div class="login">
    <div class="posicion">
      <h2 class="titulo">Inicia Sesión</h2>
      <h3 class="subtitulo">Ingrese sus datos de voluntario</h3>
    </div>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Usuario<br></label>
        <input type="text" >
      </div>
      <div class="form-group">
        <label for="password">Contraseña<br></label>
        <input type="password">
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
    <div class="register">
      <p>¿No tienes cuenta?</p>
      <router-link to="register" class="enlace">Registrate</router-link>
    </div>
  </div>
</template>




<style scoped>
.titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
  margin-right: 6.5rem;
}

.subtitulo
{
  margin-bottom: 2rem;
}
.posicion{
 margin-right: 9rem;
}
label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 5rem;
  text-align: left; 
  height: 45rem;
  font-family: "Open Sans", sans-serif;
}

.form-group {
  margin-bottom: 3rem;
}

input {
  border-radius: 4px;
  border: 1px solid #363225;
  background-color: transparent;
  padding: 10px 20px;
  color: #363225;
  width: 400px;
  outline: none; 
}
button {
  display: inline-block;
  background-color: #FF5C39;
  color: #fff;
  padding: 0.5em 1em;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  width: 400px;
  border: none;
  border-radius: 4px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);
  transition: background-color 0.2s ease-in-out;
}

button:hover {
  background-color: #c23414;
  cursor: pointer;
}

.register{
display: flex;
align-items: center;
padding: 0.5rem;
color: #363225;
}

.enlace{
  color: #FF5C39;
  text-decoration: none;
  padding: 0.5rem;
  
  text-decoration: none;
}
.enlace:hover{
  color: #c23414;
}



</style>


<script lang="ts">
import { defineComponent, ref } from 'vue';

interface LoginData {
  username: string;
  password: string;
  errorMessage: string;
}

export default defineComponent({
  name: 'Login',
  setup() {
    const data = ref<LoginData>({
      username: '',
      password: '',
      errorMessage: ''
    });

    const login = () => {
      if (data.value.username === 'miusuario' && data.value.password === 'micontraseña') {
        alert('Bienvenido!');
      } else {
        data.value.errorMessage = 'Nombre de usuario o contraseña incorrectos.';
      }
    };

    return {
      ...data,
      login
    };
  }
});
</script>

<style>





</style>