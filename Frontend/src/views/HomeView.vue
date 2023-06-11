<script setup lang="ts">
</script>

<template>
  <main class="home">
    <div class="top-content">
      <div class="left">
        <div class="tittle">
          <h1 id="t1">VOLUNTARIADO</h1>
          <h1 id="t2">PARA EMERGENCIAS</h1>
        </div>
        <div class="sub-tittle">
          <p>
            Únete al programa de voluntariado más <br />
            grande de Chile. ¡Juntos somos más!
          </p>
        </div>
      </div>
      <img id="img1" src="../assets/voluntariado-image-01.png" alt="imagen voluntariado">
    </div>
    <img id="img2" src="../assets/voluntariado-image-02.png" alt="imagen voluntariado">
  </main>
</template>

<style>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 8rem 8rem 0 8rem;
}

.home .top-content {
  display: flex;
  gap: 60px;
}

.home .top-content .left {
  width: 600px;
}

.left .tittle {
  font-family: "Open Sans";
  color: #363225;
}

.left .tittle #t1 {
  font-weight: 800;
  font-size: 46px;
}

.left .tittle #t2 {
  text-align: end;
  font-weight: 800;
  font-size: 46px;
  -webkit-text-fill-color: transparent;
  -webkit-text-stroke: 2px #363225;
}

.left .sub-tittle {
  display: grid;
  justify-items: end;
}

.left .sub-tittle p {
  font-family: "Open Sans";
  font-weight: 600;
  font-size: 18px;
  text-align: center;
  color: #000000;
}

.home .top-content #img1 {
  width: 600px;
  height: 320px;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.home #img2 {
  position: relative;
  bottom: 30px;
  width: 600px;
  height: 320px;
  border-radius: 15px;
  transform: rotate(7.58deg);
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}
</style>