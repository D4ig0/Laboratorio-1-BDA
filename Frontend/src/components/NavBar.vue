<script lang="ts">
</script>

<template>
    <nav class="navbar">
        <div class="links left">
            <div :class="{'div-active': $route.path === '/' }">
                <RouterLink active-class="active" class="link" to="/" >INICIO</RouterLink>
            </div>
            <div :class="{'div-active': $route.path === '/emergencias' }">
                <RouterLink active-class="active" class="link" to="/emergencias" >EMERGENCIAS</RouterLink>
            </div>
        </div>
        <div class="links right">
            <div :class="{'div-active': $route.path === '/login' }">
                <RouterLink active-class="active" class="link" to="/login" >INICIAR SESIÓN</RouterLink>
            </div>
            <div :class="{'div-active': $route.path === '/register' }">
                <RouterLink active-class="active" class="link" to="/register" >REGISTRARSE</RouterLink>
            </div>
        </div>
    </nav>
</template>

<style scoped>
 body {
    overflow: hidden;
    background: #D3CDBD;
    color: #363225;
    font-family: "Open Sans";
    font-weight: 700;
    margin: 0;
  }

  .navbar {
    display:flex;
    justify-content: space-between;
    margin: 0 75px;
  }

  .links {
    box-sizing: border-box;
    display: flex;
    gap: 25px;
  }

  .links div {

    pointer-events: none; 
    display: block;
    box-sizing: border-box;
    margin-top: -15px;
    padding: 40px 18px 20px 18px;
  }

  .links .link {
    font-weight: 700;
    font-size: 16px;
    color: #363225;
    text-decoration: none;
    pointer-events: auto; 
  }

  .links .div-active{
    background: #FF5C39;
    border-radius: 5px;
  }

  .links .active {
    display: block;
    color: white;
  }

  .links.left .div-active {
    transform: rotate(-5deg);
  }

  .links.left .active {
    transform: rotate(5deg);
  }

  .links.right .div-active {
    transform: rotate(5deg);
  }

  .links.right .active {
    transform: rotate(-5deg);
  }
</style>