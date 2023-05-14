<template>
  <div class="login">
    <div class="posicion">
      <h2 class="titulo">Inicia Sesión</h2>
      <h3 >Ingrese sus datos de voluntario</h3>
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
      <p class="texto"> ¿No tienes cuenta?</p>
      <router-link to="register" class="enlace">Registrate</router-link>
    </div>
  </div>
  
</template>




<style scoped>
.titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
}

.posicion{
  justify-content: start;

}
label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}
.login {
  display: grid;
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
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: transparent;
  padding: 0.5rem 1rem;
  color: #363225;
  width: 25rem;
  outline: none; 
}
button {
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
  cursor:pointer;
}

.register{
display:grid;
grid-template-areas: 'texto enlace' ;
justify-content:center;
padding: 0.5rem;
color: #363225;
}
.texto{
  margin-right: 0.5rem;
  color: #363225;
}

.enlace{
  color: #FF5C39;
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