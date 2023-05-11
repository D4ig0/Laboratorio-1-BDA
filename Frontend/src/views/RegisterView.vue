<template>
    <div class="register">
        <div class="posicion">
      <h2 class="titulo">Registro de voluntario</h2>
      <h3 class="subtitulo">Ingrese sus datos para registrarse en la plataforma</h3>
      </div>
      <form @submit.prevent="register">
        <div class="form-group">
          <label for="name">Nombre<br></label>
          <input type="text" v-model="name">
        </div>
        <div class="form-group">
          <label for="correo">Correo<br></label>
          <input type="email" v-model="correo">
        </div>
        <div class="form-group">
          <label for="password">Contraseña<br></label>
          <input type="password" v-model="password">
        </div>
        <button type="submit">Registrarse</button>
      </form>
      <div class="login">
        <p>¿Ya tienes una cuenta?</p>
        <router-link to="/login" class="enlace">Inicia sesión</router-link>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  import { useRouter } from 'vue-router';
  
  export default defineComponent({
    name: 'Register',
    data() {
      return {
        name: '',
        correo: '',
        password: '',
      };
    },
    methods: {
      async register() {
        const router = useRouter();
        await router.push('/dashboard');
      },
    },
  });
  </script>
  
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

  
  .register {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: left;
    height: 45rem;
    font-family: "Open Sans", sans-serif;
  }
  
  .form-group {
    margin-bottom: 3rem;
  }
  label {
    font-size: 0.9rem;
    font-weight: bold;
    color: #363225;
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
  
  .enlace {
    color: #FF5C39;
    text-decoration: none;
    padding: 0.5rem;
  }
  
  .enlace:hover {
    color: #c23414;
  }
  .login{
    margin-top: 0.5rem;
    display: flex;
    flex-direction: row;
    align-items: center;


  }
  
  
</style>
```
  