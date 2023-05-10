<script setup lang="ts">
</script>

<template>
    <div class="navbar">
        <div class="links">
            <p>HOME</p>
            <p>EMERGENCIAS</p>
        </div>
        <div class="links">
            <RouterLink to="/login" >INICIAR SESION</RouterLink>
            <p>REGISTRARSE</p>
        </div>
    </div>
</template>

<style scoped>
    .navbar {
        display: flex;
        justify-content: space-between;
        padding: 0 2rem;
        height: 5rem;
    }

    .links {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 20rem;
    }

    .links p {
        margin: 0 1rem;
        cursor: pointer;
        color: #363225;
    }

    .links p:hover {
        color: #ff0000;
    }
</style>