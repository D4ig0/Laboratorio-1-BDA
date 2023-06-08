<template>
    <div :class="{ 'link-container': true, 'div-active': $route.path === path }" :style="rotateValues">
        <RouterLink active-class="active" class="link" :to="{path}">
            {{ text }}
        </RouterLink>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
    name: "LinkContainer",
    props: {
        text: {type: String, required: true},
        path: {type: String, required: true},
        direction: {type: String, required: true}
    },
    computed: {
        rotateValues(){
            const deg = (this.direction == "left") ? -5 : 5
            return {
                '--deg-div-rotate': deg + 'deg',
                '--deg-text-rotate': deg*-1 + 'deg'
            }
        }
    }
})
</script>

<style>
.link-container {
  pointer-events: none;
  display: block;
  box-sizing: border-box;
  margin-top: -15px;
  padding: 40px 18px 20px 18px;
}

.link-container .link {
  font-family: "Open Sans";
  font-weight: 700;
  font-size: 16px;
  color: #363225;
  text-decoration: none;
  pointer-events: auto;
}

.div-active {
  background: #ff5c39;
  border-radius: 5px;
  transform: rotate(var(--deg-div-rotate));
}

.link-container .active {
  display: block;
  color: white;
  transform: rotate(var(--deg-text-rotate));
}
</style>