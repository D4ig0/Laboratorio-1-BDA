<template>
    <div class="error">
        {{ message }}
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: "ErrorMessage",
    props: {
        message: {type: String, required: true},
    }
})
</script>


<style>
.error {
    display: flex;
    background-color: #FFB7B7;
    height: 50px;
    align-items: center;
    padding: 0 15px;
    border-radius: 0.3rem;
    border: 1px solid #FF0000;
    color: #FF0000;
    font-family: "Open Sans";
    font-weight: 500;
}
</style>
