<template>
  <div class="center">
    <h2 class="titulo">Voluntarios Cercanos</h2>
    <div class="input-container">
      <InputField
        @inputData="setRadio"
        fieldName="Ingrese el radio en metros"
        inputType="text"
      />
      <span class="material-symbols-rounded" @click="getVoluntariosRadio">
        input
      </span>
    </div>
    <VoluntariosTable :dataSet="tableDataSet" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import router from "@/router";
import { useAuthStore } from "@/stores/auth";
import ErrorMessage from "@/components/ErrorMessage.vue";
import PasswordField from "@/components/PasswordField.vue";
import InputField from "@/components/InputField.vue";
import VoluntariosTable from "@/components/VoluntariosTable.vue";
import axios from "axios";
import type Voluntario from "@/models/Voluntario";
export default defineComponent({
  components: { ErrorMessage, PasswordField, InputField, VoluntariosTable },
  name: "VoluntariosRadio",
  data() {
    return {
      radio: 0,
      tableDataSet: [] as Array<Voluntario>,
    };
  },
  methods: {
    getVoluntariosRadio() {
      const authStore = useAuthStore();
      const token = authStore.token;
      axios
        .get(
          `/api/voluntarios/emergencia/${this.$route.params.id}/${this.radio}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
        .then((response) => {
          this.tableDataSet = this.jsonArrayToVoluntarios(response.data);
        });
    },
    
    setRadio(radio: number) {
      this.radio = radio;
    },

    jsonArrayToVoluntarios(json: Array<any>): Array<Voluntario> {
      const voluntarios: Array<Voluntario> = [];
      for (let i = 0; i < json.length; i++) {
        const voluntario: Voluntario = {
          id: json[i].idVoluntario,
          nombre: json[i].nombre,
          correo: json[i].correo,
        };
        voluntarios.push(voluntario);
      }

      voluntarios.sort((a, b) => a.id - b.id);

      return voluntarios;
    },
  },
  beforeCreate() {
    const authStore = useAuthStore();
    const token = authStore.token;
    axios
      .get(`/api/voluntarios/${this.$route.params.id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {})
      .catch((error) => {
        router.replace("/emergencias");
      });
  },
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200");

span.material-symbols-rounded:hover {
  color: #ec411a;
}

span.material-symbols-rounded {
  font-family: "Material Symbols Rounded";
  font-weight: 600;
  font-size: 24px;
  cursor: pointer;
  justify-items: center;
  align-items: center;
}

.center {
  display: grid;
  justify-items: center;
  margin-top: 3rem;
  gap: 1rem;
}

.titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
  color: #363225;
}

.material-symbols-rounded {
  position: absolute;
  top: 2.5rem;
  right: 0.4rem;
  padding-left: 2px;
  transform: translateY(-0.6rem);
}

.input-container {
  position: relative;
  display: inline-block;
}
</style>